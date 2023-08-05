package mff.study.belajar_spring_restfullapi.serviceimpl;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import mff.study.belajar_spring_restfullapi.entity.Contact;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateContactRequest;
import mff.study.belajar_spring_restfullapi.model.SearchContactRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateContactRequest;
import mff.study.belajar_spring_restfullapi.repository.ContactRepository;
import mff.study.belajar_spring_restfullapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ContactRepository contactRepository;
    @Override
    @Transactional
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);
        return  toContactResponse(contact);

    }

    private ContactResponse toContactResponse (Contact contact){

        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    @Override
    public ContactResponse get(User user , String id) {

       Contact contact = contactRepository.findFirstByUserAndId(user , id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "contact is not found"));

        return toContactResponse(contact);
    }

    @Override
    @Transactional
    public ContactResponse update(User user, UpdateContactRequest request) {

        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user , request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "contact not found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());

        contactRepository.save(contact);

        return toContactResponse(contact);

    }

    @Override
    public Page<ContactResponse> search(User user, SearchContactRequest request) {

        Specification<Contact> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("user"), user));
            if(Objects.nonNull(request.getName())){
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("firstName"), "%"+ request.getName() +"%"),
                        criteriaBuilder.like(root.get("lastName"), "%"+ request.getName() +"%")
                ));
            }
            if(Objects.nonNull(request.getEmail())){
                predicates.add(criteriaBuilder.like(root.get("email"), "%"+ request.getEmail() +"%"));
            }
            if(Objects.nonNull(request.getPhone())){
                predicates.add(criteriaBuilder.like(root.get("phone"), "%"+ request.getPhone() +"%"));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

         Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
         Page<Contact> contacts = contactRepository.findAll(specification , pageable);
         List<ContactResponse> responses = contacts.getContent().stream().map(this::toContactResponse).toList();
         return new PageImpl<>(responses , pageable , contacts.getTotalElements());

    }
}

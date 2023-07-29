package mff.study.belajar_spring_restfullapi.serviceimpl;

import jakarta.transaction.Transactional;
import mff.study.belajar_spring_restfullapi.entity.Contact;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateContactRequest;
import mff.study.belajar_spring_restfullapi.repository.ContactRepository;
import mff.study.belajar_spring_restfullapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}

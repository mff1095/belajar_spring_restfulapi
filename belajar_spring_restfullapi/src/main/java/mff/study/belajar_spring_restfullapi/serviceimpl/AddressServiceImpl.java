package mff.study.belajar_spring_restfullapi.serviceimpl;

import mff.study.belajar_spring_restfullapi.entity.Address;
import mff.study.belajar_spring_restfullapi.entity.Contact;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateAddressRequest;
import mff.study.belajar_spring_restfullapi.model.AddressResponse;
import mff.study.belajar_spring_restfullapi.model.UpdateAddressRequest;
import mff.study.belajar_spring_restfullapi.repository.AddressRepository;
import mff.study.belajar_spring_restfullapi.repository.ContactRepository;
import mff.study.belajar_spring_restfullapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactRepository contactRepository;

    private AddressResponse toAddressResponse (Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .build();
    }

    @Override
    public AddressResponse create(User user, CreateAddressRequest request) {

        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user , request.getContactId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "contact not found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponse get(User user, String contactId, String addressId) {

        Contact contact = contactRepository.findFirstByUserAndId(user , contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "contact is not found"));

        Address address = addressRepository.findFirstByContactAndId(contact , addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "address is not found"));

        return toAddressResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse update(User user, UpdateAddressRequest request) {
        Contact contact = contactRepository.findFirstByUserAndId(user , request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "contact is not found"));

        Address address = addressRepository.findFirstByContactAndId(contact , request.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "address is not found"));

        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        addressRepository.save(address);

        return toAddressResponse(address);
    }
}

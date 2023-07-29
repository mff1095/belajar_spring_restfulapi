package mff.study.belajar_spring_restfullapi.controller;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateContactRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateContactRequest;
import mff.study.belajar_spring_restfullapi.model.WebResponse;
import mff.study.belajar_spring_restfullapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(
            path = "/api/contacts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create (User user , @RequestBody CreateContactRequest request){

        ContactResponse response = contactService.create(user , request);

        return WebResponse.<ContactResponse>builder().data(response).build();


    }

    @GetMapping(
            path = "/api/contact/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> get (User user , @PathVariable("contactId") String contactId){
        ContactResponse response = contactService.get(user , contactId);

        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/contact/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> update (User user ,
                                                @RequestBody UpdateContactRequest request,
                                                @PathVariable("contactId") String contactId){
        request.setId(contactId);

        ContactResponse response = contactService.update(user , request);

        return WebResponse.<ContactResponse>builder().data(response).build();
    }

}

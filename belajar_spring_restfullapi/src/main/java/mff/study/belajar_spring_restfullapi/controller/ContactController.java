package mff.study.belajar_spring_restfullapi.controller;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.*;
import mff.study.belajar_spring_restfullapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

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
    @GetMapping(
            path = "/api/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> search (User user ,
                                                      @RequestParam(name = "name" , required = false)String name,
                                                      @RequestParam(name = "email" , required = false)String email,
                                                      @RequestParam(name = "phone" , required = false)String phone,
                                                      @RequestParam(name = "page" , required = false , defaultValue = "0")Integer page,
                                                      @RequestParam(name = "size" , required = false, defaultValue = "5")Integer size
                                                      ) {

        SearchContactRequest request = SearchContactRequest.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .page(page)
                .size(size)
                .build();

        Page<ContactResponse> responses = contactService.search(user , request);

        return WebResponse.<List<ContactResponse>>builder()
                .data(responses.getContent())
                .paging(PagingResponse.builder()
                        .page(responses.getTotalPages())
                        .size(responses.getSize())
                        .current(responses.getNumber())
                        .build())
                .build();

    }

}

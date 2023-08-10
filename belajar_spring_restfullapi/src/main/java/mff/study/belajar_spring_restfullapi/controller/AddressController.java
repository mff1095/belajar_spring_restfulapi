package mff.study.belajar_spring_restfullapi.controller;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.*;
import mff.study.belajar_spring_restfullapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contact/{idContact}/address",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create (User user,
                                                @RequestBody CreateAddressRequest request,
                                                @PathVariable("idContact")String idContact ){
        request.setContactId(idContact);

        AddressResponse result = addressService.create(user , request);
        return WebResponse.<AddressResponse>builder().data(result).build();

    }
    @GetMapping(
            path = "/api/contact/{idContact}/address/{idAddress}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get (User user ,
                                             @PathVariable("idContact")String idContact,
                                             @PathVariable("idAddress")String idAddress){

        AddressResponse response = addressService.get(user , idContact , idAddress);

        return WebResponse.<AddressResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/contact/{idContact}/address/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create (User user,
                                                @RequestBody UpdateAddressRequest request,
                                                @PathVariable("idContact")String idContact,
                                                @PathVariable("addressId")String addressId){
        request.setContactId(idContact);
        request.setAddressId(addressId);

        AddressResponse result = addressService.update(user , request);
        return WebResponse.<AddressResponse>builder().data(result).build();

    }

    @DeleteMapping(
            path = "/api/contact/{contactId}/address/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> update (User user ,
                                       @PathVariable ("contactId")String contactId,
                                       @PathVariable("addressId")String addressId){

        addressService.remove(user , contactId , addressId);

        return WebResponse.<String>builder().data("Ok").build();

    }

    @GetMapping(
            path = "/api/contact/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> getList (User user ,
                                                 @PathVariable("contactId")String contactId){
        List<AddressResponse> response = addressService.listAddress(user , contactId);

        return WebResponse.<List<AddressResponse>>builder().data(response).build();
    }

}

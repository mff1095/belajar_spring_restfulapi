package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.CreateAddressRequest;
import mff.study.belajar_spring_restfullapi.model.AddressResponse;
import mff.study.belajar_spring_restfullapi.model.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressResponse create (User user , CreateAddressRequest request);

    AddressResponse get (User user , String contactId , String addressId);

    AddressResponse update (User user , UpdateAddressRequest request);

    void remove (User user , String contactId , String AddressId);

    List<AddressResponse> listAddress(User user , String contactId);

}

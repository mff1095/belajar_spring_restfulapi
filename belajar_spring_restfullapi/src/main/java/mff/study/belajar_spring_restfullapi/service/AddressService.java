package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.CreateAddressRequest;
import mff.study.belajar_spring_restfullapi.model.AddressResponse;

public interface AddressService {

    AddressResponse create (User user , CreateAddressRequest request);

    AddressResponse get (User user , String contactId , String addressId);

}
package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateContactRequest;

public interface ContactService {

    ContactResponse create (User user , CreateContactRequest request);

    ContactResponse get (User user , String id);



}

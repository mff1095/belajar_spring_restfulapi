package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import mff.study.belajar_spring_restfullapi.model.CreateContactRequest;
import mff.study.belajar_spring_restfullapi.model.SearchContactRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateContactRequest;
import org.springframework.data.domain.Page;

public interface ContactService {

    ContactResponse create (User user , CreateContactRequest request);

    ContactResponse get (User user , String id);

    ContactResponse update (User user , UpdateContactRequest request);

    Page<ContactResponse> search (User user , SearchContactRequest request);

}

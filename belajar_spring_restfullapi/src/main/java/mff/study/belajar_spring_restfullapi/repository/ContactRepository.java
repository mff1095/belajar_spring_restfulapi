package mff.study.belajar_spring_restfullapi.repository;

import mff.study.belajar_spring_restfullapi.entity.Contact;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.ContactResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository <Contact , String> , JpaSpecificationExecutor<Contact> {

    Optional<Contact> findFirstByUserAndId (User user , String id);

}

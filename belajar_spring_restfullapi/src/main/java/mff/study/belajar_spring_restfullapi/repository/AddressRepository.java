package mff.study.belajar_spring_restfullapi.repository;

import mff.study.belajar_spring_restfullapi.entity.Address;
import mff.study.belajar_spring_restfullapi.entity.Contact;
import mff.study.belajar_spring_restfullapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address , String> {

    Optional<Address> findFirstByContactAndId (Contact contact , String addressId);

}

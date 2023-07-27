package mff.study.belajar_spring_restfullapi.repository;

import mff.study.belajar_spring_restfullapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

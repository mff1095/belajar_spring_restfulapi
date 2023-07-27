package mff.study.belajar_spring_restfullapi.serviceimpl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.RegisterUserRequest;
import mff.study.belajar_spring_restfullapi.repository.UserRepository;
import mff.study.belajar_spring_restfullapi.security.BCrypt;
import mff.study.belajar_spring_restfullapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Override
    @Transactional
    public void register(RegisterUserRequest request) {

            Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request) ;
            if(constraintViolations.size() != 0){
                throw new ConstraintViolationException(constraintViolations);
            }

            if(userRepository.existsById(request.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Already Username Register");
            }

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
            user.setName(request.getName());

            userRepository.save(user);
    }
}
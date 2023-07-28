package mff.study.belajar_spring_restfullapi.serviceimpl;

import jakarta.transaction.Transactional;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.GetUserResponse;
import mff.study.belajar_spring_restfullapi.model.RegisterUserRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateUserRequest;
import mff.study.belajar_spring_restfullapi.repository.UserRepository;
import mff.study.belajar_spring_restfullapi.security.BCrypt;
import mff.study.belajar_spring_restfullapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public void register(RegisterUserRequest request) {

        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already Username Register");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }

    @Override
    public GetUserResponse getUser(User user) {
        return GetUserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Override
    @Transactional
    public GetUserResponse updateUser(User user, UpdateUserRequest request) {

        validationService.validate(request);

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
        userRepository.save(user);

        return GetUserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

}

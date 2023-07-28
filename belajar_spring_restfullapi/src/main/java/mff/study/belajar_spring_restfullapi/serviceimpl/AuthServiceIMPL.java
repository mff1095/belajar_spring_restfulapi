package mff.study.belajar_spring_restfullapi.serviceimpl;

import jakarta.transaction.Transactional;
import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.LoginUserRequest;
import mff.study.belajar_spring_restfullapi.model.TokenResponse;
import mff.study.belajar_spring_restfullapi.repository.UserRepository;
import mff.study.belajar_spring_restfullapi.security.BCrypt;
import mff.study.belajar_spring_restfullapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthServiceIMPL implements AuthService {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public TokenResponse login(LoginUserRequest request) {
       validationService.validate(request);

       User user = userRepository.findById(request.getUsername())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password wrong"));

       if(BCrypt.checkpw(request.getPassword() , user.getPassword() )){
           user.setToken(UUID.randomUUID().toString());
           user.setTokenExpiredAt(next30days());
           userRepository.save(user);

           return TokenResponse.builder()
                   .token(user.getToken())
                   .expiredAt(user.getTokenExpiredAt())
                   .build();
       } else {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "username or password wrong");
       }
}

    @Override
    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

    private Long next30days(){
        return System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30);
    }
}

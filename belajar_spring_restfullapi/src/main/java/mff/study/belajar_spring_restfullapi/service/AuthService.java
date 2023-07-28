package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.LoginUserRequest;
import mff.study.belajar_spring_restfullapi.model.TokenResponse;

public interface AuthService {

    public TokenResponse login (LoginUserRequest request);

    void logout (User user);

}

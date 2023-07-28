package mff.study.belajar_spring_restfullapi.service;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.GetUserResponse;
import mff.study.belajar_spring_restfullapi.model.RegisterUserRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateUserRequest;

public interface UserService {

      void register (RegisterUserRequest request);

      GetUserResponse getUser(User user);

      GetUserResponse updateUser(User user , UpdateUserRequest request);

}

package mff.study.belajar_spring_restfullapi.controller;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.GetUserResponse;
import mff.study.belajar_spring_restfullapi.model.RegisterUserRequest;
import mff.study.belajar_spring_restfullapi.model.UpdateUserRequest;
import mff.study.belajar_spring_restfullapi.model.WebResponse;
import mff.study.belajar_spring_restfullapi.service.AuthService;
import mff.study.belajar_spring_restfullapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        userService.register(request);

        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<GetUserResponse> getUser (User user){
        GetUserResponse getUserResponse = userService.getUser(user);
        return  WebResponse.<GetUserResponse>builder().data(getUserResponse).build();

    }

    @PatchMapping(
            path = "api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<GetUserResponse> updateUser (User user , @RequestBody UpdateUserRequest request){
        GetUserResponse response = userService.updateUser(user , request);
        return WebResponse.<GetUserResponse>builder()
                .data(response)
                .build();
    }

    @DeleteMapping(
            path = "/api/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<String> logout (User user){

        authService.logout(user);

        return WebResponse.<String>builder()
                .data("OK")
                .build();
    }

}

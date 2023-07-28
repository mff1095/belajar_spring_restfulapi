package mff.study.belajar_spring_restfullapi.controller;

import mff.study.belajar_spring_restfullapi.entity.User;
import mff.study.belajar_spring_restfullapi.model.GetUserResponse;
import mff.study.belajar_spring_restfullapi.model.RegisterUserRequest;
import mff.study.belajar_spring_restfullapi.model.WebResponse;
import mff.study.belajar_spring_restfullapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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

}

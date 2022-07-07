package SOA_WEB_APP.controller;


import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.security.payload.SuccessfulResponse;
import SOA_WEB_APP.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public SuccessfulResponse addUser(@RequestBody User user) {
        userService.addUser(user);
        return new SuccessfulResponse("You have been successfully registered!");
    }
}

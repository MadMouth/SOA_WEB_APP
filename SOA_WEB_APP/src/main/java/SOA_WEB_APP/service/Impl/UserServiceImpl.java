package SOA_WEB_APP.service.Impl;

import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.exception.AlreadyExistsException;
import SOA_WEB_APP.exception.DoesNotExist;
import SOA_WEB_APP.repository.UserRepository;
import SOA_WEB_APP.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean addUser(User user) {
        userRepository.findByUserName(user.getName()).ifPresent(userFromDB ->
        {
            throw new AlreadyExistsException("User with name " + userFromDB.getName() + " is already exists!");
        });
        boolean isSuccessful = userRepository.addUser(user);
        if (isSuccessful) {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        }
        return isSuccessful;
    }

    @Override
    public boolean authorization(User user) {
        User userFromDB = userRepository.findByUserName(user.getName())
                .orElseThrow(() -> new DoesNotExist("User with this name doesn't exists!"));
        return user.equals(userFromDB);
    }

    @Override
    public UserDetails getUserPrincipal() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}

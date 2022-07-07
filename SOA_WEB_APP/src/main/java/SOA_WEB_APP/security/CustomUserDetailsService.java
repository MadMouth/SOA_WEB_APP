package SOA_WEB_APP.security;

import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.exception.DoesNotExist;
import SOA_WEB_APP.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public CustomUserDetailsService(UserRepository userRepository,
                                    LoginAttemptService loginAttemptService,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
        this.request = request;
        this.response = response;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            new ObjectMapper().writeValue(response.getOutputStream(),
                    "The authentication limit has been exceeded. Your IP is PERMANENTLY blocked. Have a nice day! :)");
        }
        User user = userRepository.findByUserName(username).orElseThrow(() -> new DoesNotExist("User doesn't exists!"));
        return new CustomUserDetails(user.getId(), user.getName(), user.getPassword());
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}

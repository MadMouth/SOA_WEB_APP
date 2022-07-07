package SOA_WEB_APP.service;

import SOA_WEB_APP.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public boolean addUser(User user);

    public UserDetails getUserPrincipal();

    public boolean authorization(User user);
}

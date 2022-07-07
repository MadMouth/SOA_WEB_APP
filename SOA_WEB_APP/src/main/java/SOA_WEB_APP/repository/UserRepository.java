package SOA_WEB_APP.repository;

import SOA_WEB_APP.entity.User;

import java.util.Optional;

public interface UserRepository {

    public boolean addUser(User user);

    public Optional<User> findByUserName(String username);
}

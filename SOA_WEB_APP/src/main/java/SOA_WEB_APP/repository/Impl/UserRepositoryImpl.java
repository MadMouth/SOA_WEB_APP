package SOA_WEB_APP.repository.Impl;

import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().save(user)).isPresent();
    }

    @Override
    public Optional<User> findByUserName(String name) {
        List list = sessionFactory.getCurrentSession()
                .createQuery("from User where name = :param")
                .setParameter("param", name).list();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable((User) list.get(0));
    }

}

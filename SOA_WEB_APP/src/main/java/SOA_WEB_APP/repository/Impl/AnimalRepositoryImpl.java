package SOA_WEB_APP.repository.Impl;

import SOA_WEB_APP.entity.Animal;
import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.exception.AlreadyExistsException;
import SOA_WEB_APP.exception.DoesNotExist;
import SOA_WEB_APP.exception.NoRightsException;
import SOA_WEB_APP.repository.AnimalRepository;
import SOA_WEB_APP.repository.UserRepository;
import SOA_WEB_APP.security.CustomUserDetails;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AnimalRepositoryImpl implements AnimalRepository {

    private final SessionFactory sessionFactory;
    private final UserRepository userRepository;

    public AnimalRepositoryImpl(SessionFactory sessionFactory, UserRepository userRepository) {
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }

    @Override
    public void addAnimal(Animal animal) {
        findByName(animal.getName())
                .ifPresent(animalFromDB -> {
                    throw new AlreadyExistsException("Animal with this name is already exists!");
                });
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User(principal.getId(), principal.getUsername(), principal.getPassword());
        animal.setUser(user);
        sessionFactory.getCurrentSession().save(animal);
    }

    @Override
    public void editAnimal(Animal animal) {
        sessionFactory.getCurrentSession().saveOrUpdate(animal);
    }

    @Override
    public void removeAnimal(Long id) {
        Animal animalById = getAnimalById(id);
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (animalById.getUser().getName() == principal.getName()) {
            sessionFactory.getCurrentSession().remove(animalById);
        } else {
            throw new NoRightsException("You do not have the right to remove an animal that is not yours!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<Animal>> getAnimalsByUser(User user) {

        User userFromDB = userRepository.findByUserName(user.getName()).get();
        List list = sessionFactory.getCurrentSession()
                .createQuery("from Animal where user=:param")
                .setParameter("param", userFromDB).list();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<Animal>) list);
    }

    @Override
    public Animal getAnimalById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(
                Animal.class, id)).orElseThrow(() -> new DoesNotExist("Animal with id " + id + " doesn't exist!"));
    }

    @Override
    public Optional<Animal> findByName(String name) {
        List list = sessionFactory.getCurrentSession()
                .createQuery("from Animal where name = :param")
                .setParameter("param", name).list();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable((Animal) list.get(0));
    }

}

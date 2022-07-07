package SOA_WEB_APP.repository;

import SOA_WEB_APP.entity.Animal;
import SOA_WEB_APP.entity.User;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {

    public void addAnimal(Animal animal);

    public void editAnimal(Animal animal);

    public void removeAnimal(Long id);

    public Optional<List<Animal>> getAnimalsByUser(User user);

    public Animal getAnimalById(Long id);

    public Optional<Animal> findByName(String name);

}

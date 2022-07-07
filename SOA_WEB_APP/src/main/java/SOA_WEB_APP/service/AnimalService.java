package SOA_WEB_APP.service;

import SOA_WEB_APP.entity.Animal;
import SOA_WEB_APP.entity.User;

import java.util.List;

public interface AnimalService {

    public void addAnimal(Animal animal);

    public void editAnimal(Animal animal);

    public void removeAnimal(Long id);

    public List<Animal> getAnimalsByUser(User user);

    public Animal getAnimalById(Long id);
}

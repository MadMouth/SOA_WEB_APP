package SOA_WEB_APP.service.Impl;

import SOA_WEB_APP.entity.Animal;
import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.repository.AnimalRepository;
import SOA_WEB_APP.service.AnimalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void addAnimal(Animal animal) {
        animalRepository.addAnimal(animal);
    }

    @Override
    public void editAnimal(Animal animal) {
        animalRepository.editAnimal(animal);
    }

    @Override
    public void removeAnimal(Long id) {
        animalRepository.removeAnimal(id);
    }

    @Override
    public List<Animal> getAnimalsByUser(User user) {
        List<Animal> listOfAnimals = new ArrayList<>();
        animalRepository.getAnimalsByUser(user).ifPresent(listOfAnimals::addAll);
        return listOfAnimals;
    }

    @Override
    public Animal getAnimalById(Long id) {
        return animalRepository.getAnimalById(id);

    }
}

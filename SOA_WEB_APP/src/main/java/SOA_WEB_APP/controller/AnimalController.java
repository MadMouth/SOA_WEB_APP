package SOA_WEB_APP.controller;

import SOA_WEB_APP.entity.Animal;
import SOA_WEB_APP.entity.User;
import SOA_WEB_APP.security.payload.SuccessfulResponse;
import SOA_WEB_APP.service.AnimalService;
import SOA_WEB_APP.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/animal")
public class AnimalController {

    private final AnimalService animalService;
    private final UserService userService;

    public AnimalController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping
    public SuccessfulResponse createAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
        return new SuccessfulResponse("The animal was successfully added!");
    }

    @PutMapping
    public SuccessfulResponse editAnimal(@RequestBody Animal animal) {
        animalService.editAnimal(animal);
        return new SuccessfulResponse("The animal was successfully edited!");

    }

    @DeleteMapping("/{id}")
    private SuccessfulResponse removeAnimal(@PathVariable Long id) {
        animalService.removeAnimal(id);
        return new SuccessfulResponse("The animal was successfully deleted!");
    }

    @GetMapping
    private List<Animal> listOfAnimals() {
        UserDetails userPrincipal = userService.getUserPrincipal();
        User user = new User(null, userPrincipal.getUsername(), userPrincipal.getPassword());
        return animalService.getAnimalsByUser(user);
    }

    @GetMapping("/{id}")
    private Animal AnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }
}

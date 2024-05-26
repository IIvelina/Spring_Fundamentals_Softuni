package bg.softuni.intro1.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooService {

//    private final Animal animal;
//
//    public ZooService(Animal animal) {
//        this.animal = animal;
//    }
//
//    public void doWork(){
//        animal.makeNoise();
//    }


/*
    private final List<Animal> animals;

    public ZooService(List<Animal>animals){
        this.animals = animals;
    }

    public void doWork(){
        animals.stream().forEach(
                Animal::makeNoise
        );
    }
 */

    private final Animal animal;

    public ZooService(@Qualifier("mySuperDog") Animal animal) {
        this.animal = animal;
    }

    public void doWork(){
        animal.makeNoise();
    }
}

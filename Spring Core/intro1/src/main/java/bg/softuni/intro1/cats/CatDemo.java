package bg.softuni.intro1.cats;

import bg.softuni.intro1.cats.model.dto.CreateOwnerDTO;
import bg.softuni.intro1.cats.service.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatDemo implements CommandLineRunner {

    private final OwnerService ownerService;

    public CatDemo(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {
        CreateOwnerDTO createOwnerDTO = new CreateOwnerDTO();
        createOwnerDTO.setOwnerName("Pesho");
        createOwnerDTO.setCatNames(List.of("Gosho", "Chinchila"));

        ownerService.createOwner(createOwnerDTO);
    }
}

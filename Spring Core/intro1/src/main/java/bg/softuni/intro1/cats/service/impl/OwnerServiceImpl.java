package bg.softuni.intro1.cats.service.impl;

import bg.softuni.intro1.cats.model.dto.CreateOwnerDTO;
import bg.softuni.intro1.cats.model.entity.CatEntity;
import bg.softuni.intro1.cats.model.entity.OwnerEntity;
import bg.softuni.intro1.cats.repository.OwnerRepository;
import bg.softuni.intro1.cats.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void createOwner(CreateOwnerDTO createOwnerDTO) {

        OwnerEntity owner = new OwnerEntity();
        owner.setOwnerName(createOwnerDTO.getOwnerName());

        createOwnerDTO.getCatNames().forEach(name -> {
            CatEntity cat = new CatEntity();
            cat.setCatName(name);
            cat.setOwner(owner);
            owner.addCat(cat);
        });

        ownerRepository.save(owner);
    }
}

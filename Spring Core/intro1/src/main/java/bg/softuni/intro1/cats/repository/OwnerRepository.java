package bg.softuni.intro1.cats.repository;

import bg.softuni.intro1.cats.model.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

}

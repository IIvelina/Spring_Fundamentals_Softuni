package bg.softuni.intro1.cats.repository;

import bg.softuni.intro1.cats.model.entity.CatEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
}

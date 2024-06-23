package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.Painting;
import bg.softuni.exam.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findAllByOwnerId(Long userId);




    List<Painting> findAllByOwner_IdNot(Long ownerId);

    Optional<Painting> findByIdAndOwner_Id(Long id, Long ownerId);
}

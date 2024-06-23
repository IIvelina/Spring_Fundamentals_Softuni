package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.Painting;
import bg.softuni.exam.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByIdAndFavoritePaintings_Id(Long userId, Long paintingId);

    List<User> findAllByFavoritePaintingsContaining(Painting painting);
}

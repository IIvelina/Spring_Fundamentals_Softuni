package bg.softuni.exam.service;

import bg.softuni.exam.model.entity.User;
import bg.softuni.exam.model.serviceModel.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

   // boolean existsByIdAndFavoritePaintings_Id(Long userId, Long paintingId);
}

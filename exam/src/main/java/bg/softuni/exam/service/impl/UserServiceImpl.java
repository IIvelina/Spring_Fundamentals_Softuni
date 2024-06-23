package bg.softuni.exam.service.impl;

import bg.softuni.exam.model.entity.User;
import bg.softuni.exam.model.serviceModel.UserServiceModel;
import bg.softuni.exam.repository.UserRepository;
import bg.softuni.exam.security.CurrentUser;
import bg.softuni.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return modelMapper.map(user, UserServiceModel.class);
            }
        }
        return null;
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

//    @Override
//    public boolean existsByIdAndFavoritePaintings_Id(Long userId, Long paintingId) {
//        return userRepository.existsByIdAndFavoritePaintings_Id(userId, paintingId);
//    }


}

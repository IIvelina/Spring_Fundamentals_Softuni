package bg.softuni.exam.service.impl;

import bg.softuni.exam.model.entity.Painting;
import bg.softuni.exam.model.entity.User;
import bg.softuni.exam.model.serviceModel.PaintingServiceModel;
import bg.softuni.exam.model.view.PaintingViewModel;
import bg.softuni.exam.repository.PaintingRepository;
import bg.softuni.exam.repository.UserRepository;
import bg.softuni.exam.security.CurrentUser;
import bg.softuni.exam.service.PaintingService;
import bg.softuni.exam.service.StyleService;
import bg.softuni.exam.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final StyleService styleService;

    private final UserRepository userRepository;

    public PaintingServiceImpl(PaintingRepository paintingRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, StyleService styleService, UserRepository userRepository) {
        this.paintingRepository = paintingRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.styleService = styleService;
        this.userRepository = userRepository;
    }

    @Override
    public void addPainting(PaintingServiceModel paintingServiceModel) {
        Painting painting = modelMapper.map(paintingServiceModel, Painting.class);

        painting.setOwner(userService.findById(currentUser.getId()));

        painting.setStyle(styleService.findByStyleNameEnum(paintingServiceModel.getStyle()));

        paintingRepository.save(painting);
    }

    @Override
    public List<PaintingViewModel> findAllPaintingByUserId(Long userId) {
        return paintingRepository.findAllByOwnerId(userId)
                .stream()
                .map(p -> {
                    PaintingViewModel pvm = new PaintingViewModel();
                    pvm.setId(p.getId());
                    pvm.setName(p.getName());
                    pvm.setAuthor(p.getAuthor());
                    pvm.setImage(p.getImage());
                    pvm.setStyle(p.getStyle());
                    return pvm;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removePainting(Long paintingId, Long userId) {
        Painting painting = paintingRepository.findByIdAndOwner_Id(paintingId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid painting ID or not authorized"));

        List<User> usersWithFavoritePainting = userRepository.findAllByFavoritePaintingsContaining(painting);
        if (usersWithFavoritePainting.isEmpty()) {
            paintingRepository.delete(painting);
        }
    }

    @Override
    public List<PaintingViewModel> findAllPaintingsWithoutUser(Long userId) {
        return paintingRepository.findAllByOwner_IdNot(userId)
                .stream()
                .map(this::mapToViewModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PaintingViewModel> findAllFavoritePaintingsByUserId(Long userId) {
        return userService.findById(userId).getFavoritePaintings().stream()
                .map(this::mapToViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addToFavorites(Long paintingId, Long userId) {
        User user = userService.findById(userId);
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid painting ID: " + paintingId));

        user.getFavoritePaintings().add(painting);
        userRepository.save(user);
    }

    private PaintingViewModel mapToViewModel(Painting painting) {
        PaintingViewModel viewModel = new PaintingViewModel();
        viewModel.setId(painting.getId());
        viewModel.setName(painting.getName());
        viewModel.setAuthor(painting.getAuthor());
        viewModel.setImage(painting.getImage());
        viewModel.setStyle(painting.getStyle());
        viewModel.setOwnerUsername(painting.getOwner().getUsername());
        viewModel.setVotes(painting.getVotes());
        return viewModel;
    }

    @Override
    @Transactional
    public void removeFromFavorites(Long paintingId, Long userId) {
        User user = userService.findById(userId);
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid painting ID: " + paintingId));

        user.getFavoritePaintings().remove(painting);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void voteForPainting(Long paintingId, Long userId) {
        Optional<Painting> paintingOpt = paintingRepository.findById(paintingId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (paintingOpt.isPresent() && userOpt.isPresent()) {
            Painting painting = paintingOpt.get();
            User user = userOpt.get();

            if (!painting.getOwner().getId().equals(userId) && !painting.getVoters().contains(user)) {
                painting.getVoters().add(user);
                painting.setVotes(painting.getVotes() + 1);
                paintingRepository.save(painting);
            }
        }
    }

    public List<PaintingViewModel> findMostRatedPaintings() {
        return paintingRepository.findAll()
                .stream()
                .filter(painting -> painting.getVotes() > 0)
                .sorted((p1, p2) -> {
                    int voteComparison = Integer.compare(p2.getVotes(), p1.getVotes());
                    if (voteComparison == 0) {
                        return p1.getName().compareTo(p2.getName());
                    }
                    return voteComparison;
                })
                .limit(2)
                .map(this::mapToViewModel)
                .collect(Collectors.toList());
    }
}

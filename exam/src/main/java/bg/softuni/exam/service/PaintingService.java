package bg.softuni.exam.service;

import bg.softuni.exam.model.serviceModel.PaintingServiceModel;
import bg.softuni.exam.model.view.PaintingViewModel;

import java.util.List;

public interface PaintingService {
    void addPainting(PaintingServiceModel paintingServiceModel);

    List<PaintingViewModel> findAllPaintingByUserId(Long userId);

    void removePainting(Long paintingId, Long userId);


    List<PaintingViewModel> findAllPaintingsWithoutUser(Long userId);

    List<PaintingViewModel> findAllFavoritePaintingsByUserId(Long userId);

    void addToFavorites(Long paintingId, Long userId);

    void removeFromFavorites(Long paintingId, Long userId);

    void voteForPainting(Long paintingId, Long userId);

    List<PaintingViewModel> findMostRatedPaintings();
}

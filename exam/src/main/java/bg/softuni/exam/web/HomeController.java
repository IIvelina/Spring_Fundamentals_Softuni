package bg.softuni.exam.web;

import bg.softuni.exam.model.view.PaintingViewModel;
import bg.softuni.exam.security.CurrentUser;
import bg.softuni.exam.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    private final PaintingService paintingService;

    public HomeController(CurrentUser currentUser, PaintingService paintingService) {
        this.currentUser = currentUser;
        this.paintingService = paintingService;
    }



    @GetMapping
    public String index(Model model){
        if (currentUser.getId() == null){
            return "index";
        }

        Long userId = currentUser.getId();
        List<PaintingViewModel> currentUserPaintings = paintingService.findAllPaintingByUserId(userId);
        model.addAttribute("currentUserPaintings", currentUserPaintings);

        List<PaintingViewModel> allPaintingsWithoutCurrentUserPaintings = paintingService.findAllPaintingsWithoutUser(userId);
        model.addAttribute("allPaintingsWithoutCurrentUserPaintings", allPaintingsWithoutCurrentUserPaintings);

        List<PaintingViewModel> myFavoritePaintings = paintingService.findAllFavoritePaintingsByUserId(userId);
        model.addAttribute("myFavoritePaintings", myFavoritePaintings);

        List<PaintingViewModel> mostRatedPaintings = paintingService.findMostRatedPaintings();
        model.addAttribute("mostRatedPaintings", mostRatedPaintings);

        return "home";
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("currentUser", currentUser);
    }

    @PostMapping("/paintings/remove")
    public String removePainting(@RequestParam Long paintingId) {
        Long userId = currentUser.getId();
        paintingService.removePainting(paintingId, userId);
        return "redirect:/";
    }

    @PostMapping("/paintings/favorite")
    public String addToFavorite(@RequestParam Long paintingId) {
        Long userId = currentUser.getId();
        paintingService.addToFavorites(paintingId, userId);
        return "redirect:/";
    }

    @PostMapping("/paintings/remove-favorite")
    public String removeFromFavorite(@RequestParam Long paintingId) {
        Long userId = currentUser.getId();
        paintingService.removeFromFavorites(paintingId, userId);
        return "redirect:/";
    }

    @PostMapping("/paintings/vote")
    public String voteForPainting(@RequestParam Long paintingId) {
        Long userId = currentUser.getId();
        paintingService.voteForPainting(paintingId, userId);
        return "redirect:/";
    }
}

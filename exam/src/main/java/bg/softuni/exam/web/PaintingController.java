package bg.softuni.exam.web;

import bg.softuni.exam.model.dto.PaintingAddDTO;
import bg.softuni.exam.model.serviceModel.PaintingServiceModel;
import bg.softuni.exam.service.PaintingService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/paintings")
public class PaintingController {

    private final ModelMapper modelMapper;
    private final PaintingService paintingService;

    public PaintingController(ModelMapper modelMapper, PaintingService paintingService) {
        this.modelMapper = modelMapper;
        this.paintingService = paintingService;
    }

    @GetMapping("/add")
    public String add() {
        return "add-painting";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid PaintingAddDTO paintingAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("paintingAddDTO",
                    paintingAddDTO);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.paintingAddDTO",
                    bindingResult);

            return "redirect:add";

        }


        paintingService.addPainting(modelMapper.map(paintingAddDTO, PaintingServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public PaintingAddDTO paintingAddDTO(){
        return new PaintingAddDTO();
    }

}

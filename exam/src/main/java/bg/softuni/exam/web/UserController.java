package bg.softuni.exam.web;

import bg.softuni.exam.model.dto.UserLoginDTO;
import bg.softuni.exam.model.dto.UserRegisterDTO;
import bg.softuni.exam.model.serviceModel.UserServiceModel;
import bg.softuni.exam.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !userRegisterDTO.getPassword()
                .equals(userRegisterDTO.getConfirmPassword())){

            redirectAttributes.addFlashAttribute("userRegisterDTO",
                    userRegisterDTO);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterDTO",
                    bindingResult);

            return "redirect:register";
        }

        //save in db
        userService.registerUser(modelMapper.map(userRegisterDTO, UserServiceModel.class));

        return "redirect:login";
    }

    @ModelAttribute
    public UserRegisterDTO userRegisterDTO(){
        return new UserRegisterDTO();
    }



    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO",
                    userLoginDTO);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);

            return "redirect:login";
        }


        UserServiceModel userServiceModel = userService
                .findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if (userServiceModel == null){
            redirectAttributes.addFlashAttribute("userLoginDTO",
                    userLoginDTO);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }


        userService.loginUser(userServiceModel.getId(), userLoginDTO.getUsername());

        return "redirect:/";
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}

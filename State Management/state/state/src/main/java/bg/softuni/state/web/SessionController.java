package bg.softuni.state.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

    private static final String LANG_SESSION_ATTRIBUTE = "lang";
    private static final String DEFAULT_LANGUAGE = "en";

    @GetMapping("/session")
    public String session(HttpSession httpSession,
                          Model model){

        var sessionLanguage = httpSession.getAttribute(LANG_SESSION_ATTRIBUTE);

        model.addAttribute("lang", sessionLanguage != null ? sessionLanguage : DEFAULT_LANGUAGE);

        return "session";
    }

    @PostMapping("/session")
    public String cookies(
            HttpSession httpSession,
            @RequestParam("language") String language){

        httpSession.setAttribute("lang", language);

        return "redirect:/session";
    }

}

package bg.softuni.springMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    //http://localhost:8080/hello/44/test
    @GetMapping("/hello/{id}/test")
    public String hello(Model model,
                        @PathVariable("id") Integer id){
        model.addAttribute("num", id);
        return "helloworld";
    }

    //HelloController
    //http://localhost:8080/hello

    //1
    /*
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("num", 3);
        return "helloworld";
    }
     */

    //2
    /*
    @GetMapping("/hello")
    public String hello(ModelMap model) {
        model.put("num", 3);
        return "helloworld";
    }
     */

    //3
    /*
    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView) {
        modelAndView.addObject("num", 3);
        modelAndView.setViewName("helloworld");
        return modelAndView;
    }
     */

    /*
       @GetMapping("/hello")
    public String hello(Model model,
                        @RequestParam("num") Integer num){

        model.addAttribute("num", num);
        return "helloworld";
    }
     */
}

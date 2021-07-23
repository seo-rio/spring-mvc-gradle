package spring.demo.app.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping(value = "/")
    public String main(Model model) {
        log.debug("TEST");
        model.addAttribute("message", "hi!");
        return "main/index";
    }

}

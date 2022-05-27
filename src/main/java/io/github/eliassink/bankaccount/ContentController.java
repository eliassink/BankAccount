package io.github.eliassink.bankaccount;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class ContentController {
    @GetMapping
    public String root() {
        return "redirect:/home.html";
    }

    @GetMapping(path = "/home.html")
    public String home(@RequestParam(name = "error", required = false, defaultValue = "false") boolean error, Model model) {
        if (error)
            model.addAttribute("errorMessage","Login failed.");
        return "home";
    }
}

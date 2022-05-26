package io.github.eliassink.bankaccount;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class Controller {
    @GetMapping(path = "/")
    public String index() {
        return "Hello";
    }
}

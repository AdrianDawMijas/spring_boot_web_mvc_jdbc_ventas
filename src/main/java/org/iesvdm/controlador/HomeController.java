package org.iesvdm.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){return "index";}


    @GetMapping("/prueba-error")
    public String pruebaError() {
        throw new RuntimeException("Esto es un error de prueba.");
    }
}

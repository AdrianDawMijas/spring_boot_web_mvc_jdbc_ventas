package org.iesvdm.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleMiException(Model model,RuntimeException reException){

        model.addAttribute("traza", reException.getMessage());
        return "error";
    }
}

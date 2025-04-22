package com.tarea9.dgmt09e10.controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tarea9.dgmt09e10.domain.Formulario;
import com.tarea9.dgmt09e10.servicios.Divisas;
import com.tarea9.dgmt09e10.servicios.Servicio;

import jakarta.validation.Valid;

@Controller
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping("")
    public String getMain(Model model) {
        Formulario formulario = new Formulario();
        formulario.setOrigen("EUR");
        formulario.setDestino("USD");
        model.addAttribute("formulario", formulario);
        model.addAttribute("divisas", Divisas.divisas);
        return "indexView";
    }

    @PostMapping("/calcular/submit")
    public String postCalculo(@Valid Formulario formulario, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            return "redirect:/";
        }
        try {
            Double valor = servicio.calcular(formulario);
            redirectAttributes.addFlashAttribute("resultado", "El valor de la conversión de " + formulario.getImporte()
                    + Divisas.divisas.get(formulario.getOrigen()) + " a " + Divisas.divisas.get(formulario.getDestino())
                    + " es: " + valor);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/";
    }

}

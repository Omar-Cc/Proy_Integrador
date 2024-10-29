
package com.utp.integradorspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PacRecetasController {
    @Autowired
    @RequestMapping("/recetas")
    public String page() {
        //model.addAttribute("attribute", "value");
        return "pac.Recetas";
    }
}

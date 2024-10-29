
package com.utp.integradorspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired    
    @RequestMapping("/login")
    public String page() {
        //model.addAttribute("attribute", "value");
        return "login";
    }
}
package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrugerController {

    @PostMapping("/login")
    public Bruger bruger (@RequestBody Bruger bruger){
        return bruger;
    }


}

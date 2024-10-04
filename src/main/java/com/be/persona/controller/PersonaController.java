package com.be.persona.controller;

import com.be.persona.domain.PersonaVO;
import com.be.persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/get")
    @ResponseBody
    public List<PersonaVO> getPersonas() {
        return personaService.getAllPersonas();
    }
}
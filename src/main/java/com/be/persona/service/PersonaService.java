package com.be.persona.service;

import com.be.persona.domain.PersonaVO;
import com.be.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonaVO> getAllPersonas() {
        return personaRepository.findAll();
    }
}
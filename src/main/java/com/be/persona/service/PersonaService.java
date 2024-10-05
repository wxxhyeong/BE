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
    // 특정 페르소나 ID로 페르소나 가져오기
    public PersonaVO getPersonaById(int persona_id) {
        return personaRepository.findPersonaById(persona_id);
    }
}
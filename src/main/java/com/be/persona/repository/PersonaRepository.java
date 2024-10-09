package com.be.persona.repository;

import com.be.persona.domain.PersonaVO;
import com.be.persona.mapper.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaRepository  {

    @Autowired
    private PersonaMapper personaMapper;

    public List<PersonaVO> findAll() {
        return personaMapper.getPersonas();
    }

    // 특정 페르소나 ID로 페르소나 가져오기
    public PersonaVO findPersonaById(int persona_id) {
        return personaMapper.getPersonaById(persona_id);
    }
}
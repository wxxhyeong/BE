package com.be.persona.repository;

import com.be.persona.domain.PersonaVO;
import com.be.persona.mapper.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaRepository {

    @Autowired
    private PersonaMapper personaMapper;

    public List<PersonaVO> findAll() {
        return personaMapper.getPersonas();
    }
}
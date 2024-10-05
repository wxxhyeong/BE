package com.be.persona.controller;

import com.be.persona.domain.PersonaVO;
import com.be.persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 특정 페르소나 ID에 맞는 데이터를 가져오는 엔드포인트
    @GetMapping("/get/{persona_id}")
    public ResponseEntity<PersonaVO> getPersonaById(@PathVariable("persona_id") int persona_id) {
        PersonaVO persona = personaService.getPersonaById(persona_id);
        if (persona != null) {
            return new ResponseEntity<>(persona, HttpStatus.OK);  // 성공 시 전체 컬럼을 반환
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 데이터가 없을 경우 404 반환
        }
    }
}
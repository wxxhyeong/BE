package com.be.persona.mapper;
import com.be.persona.domain.PersonaVO;
import java.util.List;

public interface PersonaMapper {
    List<PersonaVO> getPersonas();
    // 특정 페르소나 ID로 페르소나 가져오기
    PersonaVO getPersonaById(int persona_id);
}
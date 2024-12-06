package com.be.persona.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaVO {
    private int personaId;
    private int personaPreference;
    private int stockRate;
    private int fundRate;
    private int bondRate;
    private int savingsRate;
    private String personaName;
    private String job;
    private String comments;
    private String imagePath;
}

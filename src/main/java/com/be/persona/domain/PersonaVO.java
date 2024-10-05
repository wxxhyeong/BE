package com.be.persona.domain;

public class PersonaVO {
    private int personaId;
    private int personaPreference;
    private int stockRate;
    private int fundRate;
    private int bondRate;
    private int savingsRate;
    private String personaName;
    private String job;


    // Getters and Setters
    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getPersonaPreference() {
        return personaPreference;
    }

    public void setPersonaPreference(int personaPreference) {
        this.personaPreference = personaPreference;
    }

    public int getStockRate() {
        return stockRate;
    }

    public void setStockRate(int stockRate) {
        this.stockRate = stockRate;
    }

    public int getFundRate() {
        return fundRate;
    }

    public void setFundRate(int fundRate) {
        this.fundRate = fundRate;
    }

    public int getBondRate() {
        return bondRate;
    }

    public void setBondRate(int bondRate) {
        this.bondRate = bondRate;
    }

    public int getSavingsRate() {
        return savingsRate;
    }

    public void setSavingsRate(int savingsRate) {
        this.savingsRate = savingsRate;
    }

    public String getPersonaName() {
        return personaName;
    }

    public void setPersonaName(String personaName) {
        this.personaName = personaName;
    }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}

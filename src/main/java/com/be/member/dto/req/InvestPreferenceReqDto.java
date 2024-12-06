package com.be.member.dto.req;

public class InvestPreferenceReqDto {
    private Long memberNum;
    private Integer investScore;

    // Getters and Setters
    public Long getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Long memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getInvestScore() {
        return investScore;
    }

    public void setInvestScore(Integer investScore) {
        this.investScore = investScore;
    }
    }


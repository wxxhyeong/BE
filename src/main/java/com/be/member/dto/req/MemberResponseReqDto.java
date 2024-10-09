package com.be.member.dto.req;

public class MemberResponseReqDto {
    private Long memberNum;
    private Integer investScore;
    private Integer preference;

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

    public Integer getPreference() {
        return preference;
    }

    public void setPreference(Integer preference) {
        this.preference = preference;
    }
}

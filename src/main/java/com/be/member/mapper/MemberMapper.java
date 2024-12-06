package com.be.member.mapper;


import com.be.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface MemberMapper {

    int insert(Member member);

    Member findOneByMemberID(String memberID);

    Member findOneByMemberEmail(String email);

    Member findOneByMemberNum(long memberNum);


    Member findOneByMemberNumInvest(long memberNum);


    int updateInvestScoreAndPreference(@Param("memberNum") Long memberNum,
                                       @Param("investScore") Integer investScore,
                                       @Param("preference") Integer preference);

    int updatePassword(Member member);

    // 회원 번호로 생년월일 조회
    LocalDate getBirthDateByMemberNum(long memberNum);

    Integer findPreferenceByMemberNum(@Param("memberNum") long memberNum);

}

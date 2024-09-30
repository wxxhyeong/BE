package com.be.member.mapper;


import com.be.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}

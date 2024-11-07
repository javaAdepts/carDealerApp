package com.binary.carDealer.services;

import com.binary.carDealer.entities.Member;

import java.util.List;

public interface MemberService {

    public List<Member> getAllMembers();
    public Member getMemberById(Long id);
    public Member saveMember(Member member);
    public Member updateMember(Long id, Member member);
    public void deleteMember(Long id);
    public Member getMemberByEmail(String email);

}

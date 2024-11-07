package com.binary.carDealer.services;

import com.binary.carDealer.entities.Member;
import com.binary.carDealer.enums.UserRole;
import com.binary.carDealer.exceptions.MemberNotFoundException;
import com.binary.carDealer.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            return member.get();
        }
       throw  new MemberNotFoundException("Member not found with id "+ id+ " in our system");
    }

    @Override
    public Member saveMember(Member member) {

        if(member.getPassword() != null){
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        }

        if(member.getRole() == null){
            member.setRole(UserRole.USER.toString());
        }else {
            if(member.getRole().equalsIgnoreCase(UserRole.ADMIN.toString())){
                member.setRole(UserRole.ADMIN.toString());
            }else{
                member.setRole(UserRole.USER.toString());
            }
        }

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
           memberRepository.deleteById(id);
        }
        throw  new MemberNotFoundException("Member not found with id "+ id+ " in our system");
    }

    @Override
    public Member getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
           return member.get();
        }
        throw  new MemberNotFoundException("Member not found with email "+ email+ " in our system");
    }
}

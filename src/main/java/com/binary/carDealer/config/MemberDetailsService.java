package com.binary.carDealer.config;

import com.binary.carDealer.entities.Member;
import com.binary.carDealer.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberDetailsService  implements UserDetailsService {

    @Autowired
    MemberService memberService;

    // member email will be used as username;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String memberUserEmail = null;
        String memberUserPassword = null;
        List<GrantedAuthority> authorities = null;

        Member member = memberService.getMemberByEmail(username);

        if(member != null){
            memberUserEmail = member.getEmail();
            memberUserPassword = member.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(member.getRole()));
        }

        return new User(memberUserEmail, memberUserPassword, authorities);
    }

}

package com.binary.carDealer.controllers;

import com.binary.carDealer.config.JwtUtil;
import com.binary.carDealer.config.MemberDetailsService;
import com.binary.carDealer.dtos.AuthenticationRequestDto;
import com.binary.carDealer.dtos.AuthenticationResponseDto;
import com.binary.carDealer.entities.Member;
import com.binary.carDealer.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AuthController {


    @Autowired
    MemberService memberService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberDetailsService memberDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Member> registerUser(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.saveMember(member), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody AuthenticationRequestDto requestDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
            );

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        final UserDetails userDetails = memberDetailsService.loadUserByUsername(requestDto.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponseDto(token), HttpStatus.OK);
    }

}

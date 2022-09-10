package sk.mmarcincin.chorder2.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.mmarcincin.chorder2.models.Role;
import sk.mmarcincin.chorder2.models.Member;
import sk.mmarcincin.chorder2.payload.JwtResponse;
import sk.mmarcincin.chorder2.payload.LoginRequest;
import sk.mmarcincin.chorder2.payload.MessageResponse;
import sk.mmarcincin.chorder2.payload.SignupRequest;


import sk.mmarcincin.chorder2.security.JwtUtils;
import sk.mmarcincin.chorder2.security.UserDetailsImpl;
import sk.mmarcincin.chorder2.services.MemberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (memberService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        // Create new user's account
        Member member = new Member(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        String role = signUpRequest.getRole();

        switch (role) {
            case "ROLE_ADULT":
                member.setRole(Role.ROLE_ADULT);
            case "ROLE_ADMIN":
                member.setRole(Role.ROLE_ADMIN);
            default:
                member.setRole(Role.ROLE_KID);
        }

        memberService.addMember(member);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}


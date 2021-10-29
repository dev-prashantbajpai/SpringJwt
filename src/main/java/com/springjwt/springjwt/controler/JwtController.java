package com.springjwt.springjwt.controler;

import com.springjwt.springjwt.model.JwtRequest;
import com.springjwt.springjwt.model.JwtResponse;
import com.springjwt.springjwt.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private com.springjwt.springjwt.helper.JwtUtil jwtUtil;

    @PostMapping("/token")
     public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
     {
         System.out.println(jwtRequest);
         try{
             this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
         }catch(UsernameNotFoundException | BadCredentialsException e) {
             e.printStackTrace();
             throw new Exception("Bad Credentials");
         }

         //fine area..
         UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

         String token = this.jwtUtil.generateToken(userDetails);
         System.out.println("JWT " +token);

         //{"token":"value"}

         return ResponseEntity.ok(new JwtResponse(token));

     }

}

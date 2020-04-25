package com.vemec.api.controllers;

import com.vemec.api.services.VemecUserDetailsService;
import com.vemec.api.utils.JwtUtils;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtils;

    @Autowired
    private VemecUserDetailsService vemecUserDetailsService;

    @PostMapping("/sign_in")
    public ResponseEntity login(@RequestBody Map<String, String> payload) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.get("username"), payload.get("password"))
            );
        } catch (BadCredentialsException e) {
            return Utils.mapErrors(e);
        }


        final UserDetails userDetails = vemecUserDetailsService
                .loadUserByUsername(payload.get("username"));

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        Map<String, Object> res = new HashMap<>();

        res.put("token", jwt);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sign_up")
    public ResponseEntity register(@RequestBody Map<String, String> payload) {
        try {
            return new ResponseEntity(this.vemecUserDetailsService.registerNewUser(payload), null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}

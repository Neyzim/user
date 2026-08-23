package com.neyzimho.user.controller;

import com.neyzimho.user.bussiness.UserService;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserDto userDto){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(auth.getName());
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmail(String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUserInformation(@RequestBody UserDto userDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUserData(token, userDto));

    }
}

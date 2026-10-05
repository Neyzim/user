package com.neyzimho.user.controller;

import com.neyzimho.user.bussiness.UserService;
import com.neyzimho.user.bussiness.dto.AddressDto;
import com.neyzimho.user.bussiness.dto.PhoneDto;
import com.neyzimho.user.bussiness.dto.UserDto;
import com.neyzimho.user.infrastructure.entities.AddressEntity;
import com.neyzimho.user.infrastructure.security.JwtUtil;
import com.neyzimho.user.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name= "Tasks", description = "Task related operations")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    @Operation(summary = "Save User", description = "Communicates with User Api to create a New User")
    @ApiResponse(responseCode = "200", description = "User Saved")
    @ApiResponse(responseCode = "400", description = "User already exists")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping(value = "/login")
    @Operation(summary = "Login User", description = "Communicates with User Api to authenticate User")
    @ApiResponse(responseCode = "200", description = "User authenticated")
    @ApiResponse(responseCode = "401", description = "Invalid Credentials")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public String login(@RequestBody UserDto userDto){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(auth.getName());
    }

    @GetMapping
    @Operation(summary = "Search User Data Using Email", description = "Communicates with User Api to get User Information")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User Not found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping(value = "/delete/{email}")
    @Operation(summary = "Delete User using id", description = "Communicates with User Api to delete a User")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User nou found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update User information", description = "Communicates with User Api to update a User Information")
    @ApiResponse(responseCode = "200", description = "Information updated and saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<UserDto> updateUserInformation(@RequestBody UserDto userDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUserData(token, userDto));
    }

    @PutMapping("/address")
    @Operation(summary = "update user Address", description = "Communicates with User Api to update a User Address")
    @ApiResponse(responseCode = "200", description = "Address updated and Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto,
                                                    @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updateAddress(id, addressDto));
    }

    @PutMapping("/phone")
    @Operation(summary = "update user Phone", description = "Communicates with User Api to update a User Phone")
    @ApiResponse(responseCode = "200", description = "Phone updated and Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<PhoneDto> updatePhone(@RequestBody PhoneDto phoneDto,
                                                    @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updatePhone(id, phoneDto));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.saveNewAddress(token, addressDto));
    }

    @PostMapping("/phone")
    @Operation(summary = "save user Address", description = "Communicates with User Api to save a User Address")
    @ApiResponse(responseCode = "200", description = "Address Saved")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<PhoneDto> saveNewPhone(@RequestBody PhoneDto phoneDto,
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.saveNewPhone(token, phoneDto));
    }
}

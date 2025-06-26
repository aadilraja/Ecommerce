//TODO email verification, jwt authentication, 0uth2 authentication

package application.server.Controller;

import application.server.Service.IUserService;
import application.server.Service.JwtService;
import application.server.Service.UserService;
import application.server.persistence.DTOs.AuthRequest;
import application.server.persistence.DTOs.OnRegistrationCompleteEvent;
import application.server.persistence.DTOs.UserCreateDTO;
import application.server.persistence.DTOs.UserInfoDTO;
import application.server.persistence.model.UserInfo;
import application.server.persistence.model.VerificationToken;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.server.persistence.mapper.Mapper;
import org.springframework.web.context.request.WebRequest;
import java.util.Calendar;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Mapper mp;
    IUserService userService;
    ApplicationEventPublisher eventPublisher;
    MessageSource messages;
    AuthenticationManager authenticationManager;
    JwtService jwtService;
    @Autowired
    public AuthController(Mapper mp, IUserService userService,ApplicationEventPublisher eventPublisher,MessageSource messages,AuthenticationManager authenticationManager,JwtService jwtService) {
        this.mp = mp;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.messages = messages;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }




    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserCreateDTO userCreateDTO, HttpServletRequest request,Error error)
    {

            UserInfo registered = userService.registerNewUserAccount(mp.toRegisteredUser(userCreateDTO));
            String appUrl = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort() +"/api/auth"+
                request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);


    }

    @GetMapping("/ConfirmRegistration")
    public ResponseEntity<?> ConfirmUserRegistration (WebRequest request, @RequestParam("token") String token)
    {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        String message;
        if (verificationToken == null) {
             message = messages.getMessage("auth.message.invalidToken", null, locale);
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        UserInfo user=verificationToken.getUser();
        Calendar cal=Calendar.getInstance();

        if(verificationToken.getExpiryDate().getTime()-cal.getTime().getTime()<=0)
        {
            message = messages.getMessage("auth.message.expiryDate", null, locale);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }
        user.setEnabled(true);
        userService.saveRegisteredUser(user);

        String successMsg = messages.getMessage("auth.message.verified", null, locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(successMsg);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logUser(@RequestBody AuthRequest authRequest )
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String str=jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(str);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }


    }




}








//TODO email verification, jwt authentication, 0uth2 authentication

package application.server.Controller;

import application.server.Service.IUserService;
import application.server.Service.UserService;
import application.server.persistence.DTOs.OnRegistrationCompleteEvent;
import application.server.persistence.DTOs.UserCreateDTO;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Mapper mp;
    IUserService userService;
    ApplicationEventPublisher eventPublisher;
    MessageSource messages;
    @Autowired
    public AuthController(Mapper mp, IUserService userService,ApplicationEventPublisher eventPublisher,MessageSource messages) {
        this.mp = mp;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.messages = messages;
    }




    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserCreateDTO userCreateDTO, HttpServletRequest request,Error error)
    {

            UserInfo registered = userService.registerNewUserAccount(mp.toRegisteredUser(userCreateDTO));
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(), appUrl));

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);


    }

    @GetMapping("/ConfirmRegisteration")
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
        userService.registerNewUserAccount(user);

        String successMsg = messages.getMessage("auth.message.verified", null, locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(successMsg);
    }



    }








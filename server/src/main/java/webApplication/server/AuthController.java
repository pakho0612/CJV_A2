package webApplication.server;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController

{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody UserModel user)
    {
       try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
           var response = new CustomizedResponse( "You login Successfully", Collections.singletonList(userService.getUserByEmailOptional(user.getEmail())) );
           return new ResponseEntity( response, HttpStatus.OK);
       }
       catch (BadCredentialsException ex)
       {
           var response = new CustomizedResponse( "You username/password were entered incorrectly..", null);
           return new ResponseEntity( response, HttpStatus.UNAUTHORIZED);
       }
       catch (Exception e)
       {
           var response = new CustomizedResponse( "Server Error", null);
           return new ResponseEntity( response, HttpStatus.NOT_FOUND);
       }
    }
}

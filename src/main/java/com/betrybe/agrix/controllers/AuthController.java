package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthDto;
import com.betrybe.agrix.controllers.dto.TokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  /**
   * Constructor.
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
                        TokenService tokenService, PersonService personService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * login.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authDto.username(),
            authDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    TokenDto newTken = new TokenDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(newTken);
  }
}
package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CreationPersonDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.exeption.FertilizerException;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Person controller.
 */
@Controller
@RequestMapping("persons")
public class PersonController {

  private final PersonService personService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public PersonController(
      PersonService personService,
      AuthenticationManager authenticationManager) {
    this.personService = personService;
    this.authenticationManager = authenticationManager;
  }

  /**
   * Method.
   *
   */
  @PostMapping()
  public ResponseEntity<PersonDto> createPerson(@RequestBody CreationPersonDto creationPersonDto) {
    UserDetails userDetails = personService.loadUserByUsername(creationPersonDto.username());
    if (userDetails != null) {
      throw new FertilizerException("Person already exists");
    }
    Person person = personService.create(creationPersonDto.toDto());
    PersonDto personDto = new PersonDto(person.getId(), person.getUsername(), person.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }
}
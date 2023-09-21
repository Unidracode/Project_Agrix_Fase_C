package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.NewPersonDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * comment.
 */

@Controller
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * comment.
   */

  @PostMapping()
  public ResponseEntity<PersonDto> createPerson(@RequestBody NewPersonDto newPersonDto) {
    Person person = personService.create(newPersonDto.toEntity());
    PersonDto personDto = new PersonDto(person.getId(), person.getUsername(), person.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }
}
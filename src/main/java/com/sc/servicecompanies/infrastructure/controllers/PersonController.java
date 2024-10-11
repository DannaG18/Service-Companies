package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.PersonService;
import com.sc.servicecompanies.domain.entities.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // Obtener persona por ID (documentNumber)
    @GetMapping("/{documentNumber}")
    public ResponseEntity<Person> getPersonById(
            @PathVariable("documentNumber") String documentNumber) {
        Optional<Person> person = personService.findById(documentNumber);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear nueva persona
    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    // Actualizar persona por ID (documentNumber)
    @PutMapping("/{documentNumber}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable("documentNumber") String documentNumber,
            @Valid @RequestBody Person personDetails) {

        Optional<Person> updatedPerson = personService.update(documentNumber, personDetails);
        return updatedPerson.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar persona por ID (documentNumber)
    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable("documentNumber") String documentNumber) {
        Optional<Person> deletedPerson = personService.delete(documentNumber);
        if (deletedPerson.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


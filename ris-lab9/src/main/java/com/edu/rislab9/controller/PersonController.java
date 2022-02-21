package com.edu.rislab9.controller;

import com.edu.rislab9.entity.Person;
import com.edu.rislab9.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/person")
public class PersonController {
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") String id) {
        Person read = service.read(id);
        return ResponseEntity.ok(read);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(person));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllPeople() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }


}

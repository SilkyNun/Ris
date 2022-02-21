package com.edu.rislab9.service;

import com.edu.rislab9.entity.Person;
import com.edu.rislab9.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepo personRepo;

    public Person create(Person person) {
        return personRepo.save(person);
    }

    public Person read(String id) {
        Optional<Person> person = personRepo.findById(id);
        return person.orElseThrow(IllegalArgumentException::new);
    }

    public List<Person> readAll() {
        return personRepo.findAll();
    }

    public void delete(String id) {
        personRepo.deleteById(id);
    }

    public void deleteAll() {
        personRepo.deleteAll();
    }
}

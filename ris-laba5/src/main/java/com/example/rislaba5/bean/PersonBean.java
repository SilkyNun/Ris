package com.example.rislaba5.bean;

import com.example.rislaba5.entity.Person;
import com.example.rislaba5.service.PersonService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class PersonBean implements Serializable {
    private Logger logger = Logger.getLogger("PersonBean");
    @Inject
    private PersonService personService;
    private List<Person> people;
    @Getter @Setter
    private Person personToAdd = new Person();
    @Getter @Setter
    private String searchString = "";

    public List<Person> getPeople() {
        people = personService.readAll();
        return people.stream()
                .filter(person -> person.getName().contains(searchString)
                                || person.getAddress().contains(searchString))
                .toList();
    }

    public String addPerson() {
        personService.create(personToAdd);
        personToAdd = new Person();

        return "PEOPLE";
    }

    public String removePerson(Person person) {
        personService.delete(person.getId());

        return "PEOPLE";
    }

    public String editPerson(Person person) {
        personService.update(person);

        return "PEOPLE";
    }

}

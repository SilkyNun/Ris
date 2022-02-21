package com.example.rislaba5.service;

import com.example.rislaba5.entity.Person;
import com.example.rislaba5.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PersonService {
    private final Logger log = Logger.getLogger("PersonService");
    private final EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public void create(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        log.log(Level.WARNING, person.toString());
        try {
            transaction.begin();
            entityManager.persist(person);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Person> readAll() {
        return (List<Person>) entityManager.createQuery("from Person ").getResultList();
    }

    public Person read(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void update(Person newPerson) {
        Person person = entityManager.find(Person.class, newPerson.getId());

        if (person != null) {
            person.setName(newPerson.getName());
            person.setAddress(newPerson.getAddress());
        }
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Person person = entityManager.find(Person.class, id);

        try {
            transaction.begin();
            entityManager.remove(person);
            transaction.commit();
        } catch (Exception ignored) {
            transaction.rollback();
        }
    }
}


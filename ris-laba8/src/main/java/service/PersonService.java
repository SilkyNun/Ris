package service;

import entity.Person;
import org.springframework.stereotype.Service;
import util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class PersonService {
    EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public void create(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();

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

    public void delete(Long id) {
        Person person = entityManager.find(Person.class, id);

        if (person != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();
                entityManager.remove(person);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }
}

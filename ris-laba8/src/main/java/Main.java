import converter.JsonConverter;
import converter.XmlConverter;
import entity.Person;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import service.PersonService;

import java.nio.file.Path;
import java.util.List;


public class Main {

    public static void main(String[] args) throws JAXBException {
        //get beans from Application context
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        XmlConverter xmlConverter = context.getBean(XmlConverter.class);
        JsonConverter jsonConverter = context.getBean(JsonConverter.class);
        PersonService personService = context.getBean(PersonService.class);

        //convert XML to Object
        Person person = xmlConverter.fromXML(Path.of("src/main/resources/person.xml"));
        System.out.println(person);
        System.out.println();

        //add person to DB
        personService.create(person);

        //read all people from DB
        List<Person> people = personService.readAll();
        people.forEach(System.out::println);
        System.out.println();

        //convert all people back to XML
        List<String> strings = people.stream()
                .map(xmlConverter::toXML)
                .toList();
        strings.forEach(System.out::println);
        System.out.println();

        //convert all people to JSON
        List<String> json = people.stream()
                .map(jsonConverter::toJson)
                .toList();
        json.forEach(System.out::println);
        System.out.println();

        //delete all people form DB
        people.stream()
                .map(Person::getId)
                .forEach(personService::delete);
    }
}

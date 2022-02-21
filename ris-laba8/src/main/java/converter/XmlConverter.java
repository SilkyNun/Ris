package converter;

import entity.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;

@Component
public class XmlConverter {

    private  JAXBContext context = JAXBContext.newInstance(dto.Person.class);
    @Autowired @Getter @Setter
    private PersonConverter converter;

    public XmlConverter() throws JAXBException {
    }


    public Person fromXML(Path path) {
        Person person = null;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            dto.Person personDto = (dto.Person) unmarshaller.unmarshal(path.toFile());
            person = converter.toEntityPerson(personDto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
       return person;
    }

    public  Person fromXML(String source) {
        Person person = null;
        try {
            StringReader reader = new StringReader(source);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            dto.Person personDto = (dto.Person) unmarshaller.unmarshal(reader);
            person = converter.toEntityPerson(personDto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return person;
    }

    public  String toXML(Person person) {
        String result = null;

        try {
            dto.Person dtoPerson = converter.toDtoPerson(person);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(dtoPerson, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }
}

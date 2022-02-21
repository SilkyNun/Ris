package converter;


import dto.Person;
import entity.Citizenship;
import entity.Town;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class PersonConverter {
    public entity.Person toEntityPerson(dto.Person personDto) {
        entity.Person entityPerson = new entity.Person();

        entityPerson.setName(personDto.getName());
        entityPerson.setSurname(personDto.getSurname());
        entityPerson.setPatronymic(personDto.getPatronymic());
        entityPerson.setBirthday(personDto.getBirthday().toGregorianCalendar().toZonedDateTime().toLocalDate());
        entityPerson.setMale(personDto.isMale());
        entityPerson.setPassportSeries(personDto.getPassportSeries());
        entityPerson.setPassportNumber(personDto.getPassportNumber());

        List<Town> residenceTowns = toTownEntity(personDto.getResidenceTowns());
        mapTownToPerson(entityPerson, residenceTowns);
        entityPerson.setResidenceTowns(residenceTowns);

        entityPerson.setCurrentLivingAddress(personDto.getCurrentLivingAddress());
        entityPerson.setHomeTelephone(personDto.getHomeTelephone());
        entityPerson.setMobileTelephone(personDto.getMobileTelephone());
        entityPerson.setResidenceAddress(personDto.getResidenceAddress());

        List<Citizenship> citizenshipList = toCitizenshipEntity(personDto.getCitizenshipList());
        mapCitizenshipToPerson(entityPerson, citizenshipList);
        entityPerson.setCitizenshipList(citizenshipList);

        return entityPerson;
    }

    private Town toTownEntity(Person.ResidenceTowns.ResidenceTown residenceTown) {
        Town town = new Town();
        town.setName(residenceTown.getName());
        return town;
    }

    private List<Town> toTownEntity(Person.ResidenceTowns residenceTowns) {
        return residenceTowns.getResidenceTown().stream()
                .map(this::toTownEntity)
                .toList();
    }

    private Citizenship toCitizenshipEntity(Person.CitizenshipList.Citizenship citizenship) {
        Citizenship citizenship1 = new Citizenship();
        citizenship1.setCountryName(citizenship.getCountryName());
        return citizenship1;
    }

    private List<Citizenship> toCitizenshipEntity(Person.CitizenshipList citizenshipList) {
        return citizenshipList.getCitizenship().stream()
                .map(this::toCitizenshipEntity)
                .toList();
    }

    private void mapTownToPerson(entity.Person person, List<Town> town) {
        town.forEach(town1 -> town1.setPerson(person));
    }

    private void mapCitizenshipToPerson(entity.Person person, List<Citizenship> citizenshipList) {
        citizenshipList.forEach(citizenship -> citizenship.setPerson(person));
    }

    public Person toDtoPerson(entity.Person person) {
        Person dto = new Person();

        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setBirthday(toXmlGregorianCalendar(person.getBirthday()));
        dto.setMale(person.getMale());
        dto.setPassportSeries(person.getPassportSeries());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setResidenceTowns(toResidenceTowns(person.getResidenceTowns()));
        dto.setCurrentLivingAddress(person.getCurrentLivingAddress());
        dto.setHomeTelephone(person.getHomeTelephone());
        dto.setMobileTelephone(person.getMobileTelephone());
        dto.setResidenceAddress(person.getResidenceAddress());
        dto.setCitizenshipList(toCitizenshipList(person.getCitizenshipList()));

        return dto;

    }

    private  XMLGregorianCalendar toXmlGregorianCalendar(LocalDate date) {
        GregorianCalendar gc = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private  Person.ResidenceTowns.ResidenceTown toResidenceTown(Town entityTown) {
        Person.ResidenceTowns.ResidenceTown residenceTown = new Person.ResidenceTowns.ResidenceTown();
        residenceTown.setName(entityTown.getName());
        return residenceTown;
    }

    private  Person.ResidenceTowns toResidenceTowns(List<Town> entityTowns) {
        List<Person.ResidenceTowns.ResidenceTown> residenceTowns = entityTowns.stream()
                .map(this::toResidenceTown)
                .toList();

        Person.ResidenceTowns towns = new Person.ResidenceTowns();
        towns.getResidenceTown().addAll(residenceTowns);
        return towns;
    }

    private  Person.CitizenshipList.Citizenship toCitizenshipDto(Citizenship citizenship) {
        Person.CitizenshipList.Citizenship citizenship1 = new Person.CitizenshipList.Citizenship();
        citizenship1.setCountryName(citizenship.getCountryName());
        return citizenship1;
    }

    private  Person.CitizenshipList toCitizenshipList(List<Citizenship> citizenshipList) {
        List<Person.CitizenshipList.Citizenship> citizenships = citizenshipList.stream()
                .map(this::toCitizenshipDto)
                .toList();

        Person.CitizenshipList list = new Person.CitizenshipList();
        list.getCitizenship().addAll(citizenships);

        return list;
    }
}

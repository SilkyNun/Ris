package converter;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;

@Component
public class JsonConverter {
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Getter @Setter
    private PersonConverter personConverter;

    public String toJson(Person person) {
        return gson.toJson(person);
    }

    public Person fromJson(String source) {
        return gson.fromJson(source, Person.class);
    }
}

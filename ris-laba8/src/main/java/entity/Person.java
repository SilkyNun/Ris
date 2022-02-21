package entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.LocalDateAdapter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Expose @Column(name = "name", nullable = false)
    private String name;
    @Expose @Column(name = "surname", nullable = false)
    private String surname;
    @Expose @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Expose @Column(name = "birthday")
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate birthday;
    @Expose @Column(name = "male", nullable = false)
    private Boolean male;
    @Expose @Column(name = "passport_series", nullable = false)
    private String passportSeries;
    @Expose @Column(name = "passportNumber", nullable = false)
    @Pattern(regexp = "\\d{7}")
    private String passportNumber;
    @Expose @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Town> residenceTowns;
    @Expose @Column(name = "current_living_address", nullable = false)
    private String currentLivingAddress;
    @Expose @Column(name = "home_telephone", nullable = true)
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{2}")
    private String homeTelephone;
    @Expose @Column(name = "mobileTelephone", nullable = true)
    @Pattern(regexp = "\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}")
    private String mobileTelephone;
    @Expose @Column(name = "residence_address", nullable = false)
    private String residenceAddress;
    @Expose @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Citizenship> citizenshipList;

    public Person() {
    }
}

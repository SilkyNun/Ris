package com.edu.rislab9.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private String id;
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    @NotBlank
    private String patronymic;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Boolean sex;
    @NotBlank
    private String passportSeries;
    @NotBlank
    @Pattern(regexp = "^\\d{7}$")
    private String passwordNumber;
    @Pattern(regexp = "^\\d{7}\\w\\d{3}\\w{2}\\d$")
    private String identityNumber;
    @NotNull
    @Size(min = 5)
    private List<String> townsOfLiving;
    @NotNull
    private String currentAddress;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{2}$")
    private String homeTelephone;
    @Pattern(regexp = "^\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}$")
    private String mobileTelephone;
    @NotNull
    @Size(min = 5)
    private List<String> residenceTowns;
    @NotNull
    private List<String> citizenShip;
}

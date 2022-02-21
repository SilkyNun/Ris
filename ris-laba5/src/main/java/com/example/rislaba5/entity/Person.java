package com.example.rislaba5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString(exclude = "messages")
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;
}

package entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "person")
@Entity
@Table(name = "citizenship")
public class Citizenship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Expose
    @Column(name = "country_name", nullable = false)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}

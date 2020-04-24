package org.codejudge.sb.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Driver {


    public Driver(long id, @NotNull @NotEmpty String name, @NotEmpty @NotNull String email, @NotEmpty @NotNull String phoneNumber, @NotNull @NotEmpty String licenseNumber, @NotNull @NotEmpty String carNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.carNumber = carNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("name")
    @NotNull
    @NotEmpty
    private String name;


    @JsonProperty("email")
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    @JsonProperty("phone_number")
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @JsonProperty("license_number")
    @NotNull
    @NotEmpty
    @Column(unique = true)

    private String licenseNumber;

    @JsonProperty("car_number")
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String carNumber;


    private Double latitude;

    private Double longitude;

}

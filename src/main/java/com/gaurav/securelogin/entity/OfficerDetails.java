package com.gaurav.securelogin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfficerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long officerDetailsId;
    private String firstName;
    private String lastName;

    @Max(value = 70, message = "Age cannot be more than 70 Years")
    @Min(value = 18, message = "Age cannot be less than 18 Years")
    private Long age;

    private String emailId;
    private String designation;
    private String gender;
    private String address;
    private String county;
    private String city;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "officerDetails")
    private User user;

    public OfficerDetails(String firstName, String lastName, @Max(value = 70, message = "Age cannot be more than 70 Years")
    @Min(value = 18, message = "Age cannot be less than 18 Years") Long age, String emailId, String designation,
                          String gender, String address, String county, String city, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.emailId = emailId;
        this.designation = designation;
        this.gender = gender;
        this.address = address;
        this.county = county;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public OfficerDetails(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

package com.gaurav.securelogin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USERS")
public class User {

    @Id
    private String username;
    private String password;
    private String disabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "OFFICER_ID")
    private OfficerDetails officerDetails;

    public User(String username, String password, String disabled) {
        this.username = username;
        this.password = password;
        this.disabled = disabled;
    }

    public void addAuthority(Authority tempAuthority) {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        authorities.add(tempAuthority);
    }
}

package org.spring.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ContactDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String telephone;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressDao address;

    @ManyToOne
    private UserDao user;
}

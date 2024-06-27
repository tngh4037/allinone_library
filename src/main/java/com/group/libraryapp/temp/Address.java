package com.group.libraryapp.temp;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String city;

    private String street;

    @OneToOne(mappedBy = "address")
    private Person person;

    protected Address() {
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }
}

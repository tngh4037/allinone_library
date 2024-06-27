package com.group.libraryapp.temp;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    @OneToOne
    private Address address;

    protected Person() {
    }

    public void setAddress(Address address) {
        this.address = address;
        this.address.setPerson(this);
    }
}

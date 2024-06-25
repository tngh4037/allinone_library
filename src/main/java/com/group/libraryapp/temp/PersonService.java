package com.group.libraryapp.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson() {
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());

        person.setAddress(address); // 연결 ( update person set address_id = ?, name = ? where id = ? ) ==> "(엔티티 연관관계에서) 연관관계 주인에서 객체의 값을 세팅(set)해야만, 데이터베이스에서 두 테이블이 연결된다." ==> 즉, 연관관계의 주인이 객체가 연결되는 기준이 된다.
        // address.setPerson(person); // 연결 not working // address 는 연관관계의 주인이 아니다. 따라서 여기서 객체를 연결해봤자, 테이블에는 반영되지 않는다.
    }
}

// (중요) 정리) 연관관계의 주인에서, setter를 사용해서 객체를 세팅해야만(연결해야만), 테이블에도 연결된다.
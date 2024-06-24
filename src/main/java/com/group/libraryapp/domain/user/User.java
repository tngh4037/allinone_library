package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name") // name varchar(20) | 참고) 객체 필드명과 DB 컬럼명이 같은 경우 name="name" 은 생략할 수 있다.
    private String name;

    private Integer age; // age int // DB 테이블의 컬럼과 완전히 같기 때문에, 이런 경우는 @Column 을 생략할 수 있다.

    protected User() {}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}

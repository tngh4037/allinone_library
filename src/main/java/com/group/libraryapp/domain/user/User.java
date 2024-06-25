package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name") // name varchar(20) | 참고) 객체 필드명과 DB 컬럼명이 같은 경우 name="name" 은 생략할 수 있다.
    private String name;

    private Integer age; // age int // DB 테이블의 컬럼과 완전히 같기 때문에, 이런 경우는 @Column 을 생략할 수 있다.

    @OneToMany(mappedBy = "user") // 참고) 연관관계의 주인이 아닌 쪽에 mappedBy 옵션을 달아 주어야 한다.
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }
}

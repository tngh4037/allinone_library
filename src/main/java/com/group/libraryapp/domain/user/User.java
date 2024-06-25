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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 참고) 연관관계의 주인이 아닌 쪽에 mappedBy 옵션을 달아 주어야 한다.
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

    // user 가 가지고 있는 대출 목록 리스트안에 새로운 UserLoanHistory 를 추가했다. 그러면 (cascade 옵션에 의해서) 새로운 연결관계가 맺어진 UserLoanHistory 는 트랜잭션이 종료될 때 자동으로 체크되어 등록된다.
    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        // user 가 가지고 있는 대출 목록 리스트 중에서, 파라미터로 넘어온 책 이름으로 된 대출 내역을 찾아서 반납 처리
        UserLoanHistory targetHistory = this.userLoanHistories.stream() // 참고) lazy loading
                .filter(history -> history.getBookName().equals(bookName)) // filter: 객체들 중에 조건을 충족하는 것만 필터링 한다.
                .findFirst() // 첫 번째로 해당하는 UserLoanHistory 를 찾는다. (Optional 로 반환)
                .orElseThrow(IllegalArgumentException::new); // Optional 을 제거하기 위해 없으면 예외를 던진다.

        targetHistory.doReturn(); // 그렇게 찾은 UserLoanHistory 를 반납처리 한다.
    }
}

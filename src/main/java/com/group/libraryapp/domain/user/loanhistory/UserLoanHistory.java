package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne // N:1 관계
    private User user;

    private String bookName;

    private boolean isReturn; // 참고) boolean 으로 처리하면, tinyint 에 잘 매핑된다.

    protected UserLoanHistory() {
    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}

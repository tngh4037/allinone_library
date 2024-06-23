package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    // ============================
    // ======= Based Memory =======
    // ============================
    /*
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user") // POST /user
    public void saveUser(
            @RequestBody UserCreateRequest request
    ) {
        users.add(new User(request.getName(), request.getAge()));
    }

    @GetMapping("/user") // GET /user
    public List<UserResponse> getUsers(
    ) {
        List<UserResponse> responses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            responses.add(new UserResponse(i + 1, users.get(i)));
        }
        return responses; // 참고) 객체를 JSON 형식으로 응답 바디에 담아 보낼 때, 객체에 해당 데이터를 위한 Getter 가 있어야 한다.
    }
    */

    // ============================
    // ====== Based Database ======
    // ============================

    private final JdbcTemplate jdbcTemplate; // jdbcTemplate 을 이용해 SQL을 날릴 수 있다.

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(
            @RequestBody UserCreateRequest request
    ) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge()); // 참고) jdbcTemplate.update() 는 INSERT, UPDATE, DELETE 쿼리에 사용할 수 있다. ( jdbcTemplate.update() 에서 update() 는 UPDATE SQL 을 의미하는게 아니라, 데이터의 변경이 일어난다는 것을 의미한다. )

        // 참고) jdbcTemplate.update(..) 는, 첫 번째 파라미터로 sql을 받고, 이후에 ? 를 대신할 값을 차례로 넣으면 된다.
    }

    @GetMapping("/user") // GET /user
    public List<UserResponse> getUsers(
    ) {
        String sql = "SELECT * FROM user";
        List<UserResponse> result = jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException { // mapRow: sql 수행 결과를 받아, 원하는 객체(UserResponse)로 바꾸고 반환하는 역할 수행
                long id = rs.getLong("id"); // ResultSet에 getType("필드이름")을 사용해서, 실제 값을 가져올 수 있다.
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });

        return result;
    }
}

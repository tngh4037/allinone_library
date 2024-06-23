package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

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

}
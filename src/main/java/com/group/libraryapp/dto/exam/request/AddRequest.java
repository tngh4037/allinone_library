package com.group.libraryapp.dto.exam.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AddRequest {
    private List<Integer> number;
}

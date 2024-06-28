package com.group.libraryapp.dto.exam.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
public class DayOfTheWeekResponse {

    private final String dayOfTheWeek;

    public DayOfTheWeekResponse(LocalDate date) {
        this.dayOfTheWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
    }
}

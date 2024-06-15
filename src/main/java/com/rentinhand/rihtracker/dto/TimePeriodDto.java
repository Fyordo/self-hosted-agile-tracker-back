package com.rentinhand.rihtracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TimePeriodDto {
    private int hours;
    private int minutes;
    private int seconds;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date = null;

    public TimePeriodDto(Duration duration) {
        this.hours = (int) duration.toHours();
        this.minutes = duration.toMinutesPart();
        this.seconds = duration.toSecondsPart();
    }

    public TimePeriodDto(Duration duration, LocalDate date) {
        this.hours = (int) duration.toHours();
        this.minutes = duration.toMinutesPart();
        this.seconds = duration.toSecondsPart();
        this.date = date;
    }
}
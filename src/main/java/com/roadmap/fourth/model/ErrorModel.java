package com.roadmap.fourth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorModel {
    @Getter
    private String title;
    @Getter
    private String message;
    @Getter
    private int httpStatus;
}

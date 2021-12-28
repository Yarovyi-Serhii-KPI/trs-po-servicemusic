package com.musicstreamer.servicetags.api.dto;

import java.util.List;

public class TagDto {
    private String name;

    TagDto(String name) {
        this.name = name;
    }

    public String name() { return name; }
}

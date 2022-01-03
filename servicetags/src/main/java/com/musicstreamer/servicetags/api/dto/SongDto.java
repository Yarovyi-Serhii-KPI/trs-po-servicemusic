package com.musicstreamer.servicetags.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongDto {
    private String name;
    private String author;
    private int length;
    private int score;
    private int playlist;
}

package com.musicstreamer.servicemusic.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaylistDto {
    private String name;
    private int size;
}

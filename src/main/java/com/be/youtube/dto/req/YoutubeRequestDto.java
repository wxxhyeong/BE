package com.be.youtube.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class YoutubeRequestDto {
    private String youtubeTitle;
    private String youtubeUrl;
    private String youtubeContext;
}

package com.be.youtube.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeVO {
    private int youtubeNum;
    private String youtubeTitle;
    private String youtubeUrl;
    private String youtubeContext;
    private String regDate;
}

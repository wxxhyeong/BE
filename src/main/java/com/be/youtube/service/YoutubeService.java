package com.be.youtube.service;

import com.be.youtube.domain.YoutubeVO;
import com.be.youtube.dto.req.YoutubeRequestDto;

import java.util.List;

public interface YoutubeService {
    void saveYoutubeData(YoutubeRequestDto youtubeRequestDto);
    List<YoutubeVO> getAllYoutubeDataWithoutContext();
    YoutubeRequestDto getYoutubeDataById(int youtubeNum);
}

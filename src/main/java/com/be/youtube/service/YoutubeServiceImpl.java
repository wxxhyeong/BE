package com.be.youtube.service;

import com.be.youtube.domain.YoutubeVO;
import com.be.youtube.dto.req.YoutubeRequestDto;
import com.be.youtube.mapper.YoutubeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class YoutubeServiceImpl implements YoutubeService {
    private final YoutubeMapper youtubeMapper;

    @Override
    public void saveYoutubeData(YoutubeRequestDto youtubeRequestDto) {
        try {
            youtubeMapper.insertYoutubeData(youtubeRequestDto);
            log.info("유튜브 데이터가 성공적으로 저장되었습니다: " + youtubeRequestDto);
        } catch (Exception e) {
            log.error("유튜브 데이터 저장 중 오류 발생", e);
            throw e;
        }
    }

    @Override
    public List<YoutubeVO> getAllYoutubeDataWithoutContext() {
        return youtubeMapper.selectAllYoutubeDataWithoutContext();
    }

    @Override
    public YoutubeRequestDto getYoutubeDataById(int youtubeNum) {
        return youtubeMapper.selectYoutubeDataById(youtubeNum);
    }
}

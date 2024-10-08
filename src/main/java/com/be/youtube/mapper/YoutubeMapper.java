package com.be.youtube.mapper;

import com.be.youtube.domain.YoutubeVO;
import com.be.youtube.dto.req.YoutubeRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YoutubeMapper {
    void insertYoutubeData(YoutubeRequestDto youtubeRequestDto);
    List<YoutubeVO> selectAllYoutubeDataWithoutContext();
    YoutubeRequestDto selectYoutubeDataById(@Param("youtubeNum") int youtubeNum);
}

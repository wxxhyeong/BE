package com.be.youtube.controller;

import com.be.youtube.dto.req.YoutubeRequestDto;
import com.be.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/youtube")
@RequiredArgsConstructor
@Log4j
public class YoutubeController {

    private final YoutubeService youtubeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveYoutube(@RequestBody YoutubeRequestDto youtubeRequestDto) {
        try {
            youtubeService.saveYoutubeData(youtubeRequestDto);
            return ResponseEntity.ok("유튜브 데이터가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("유튜브 데이터 저장 중 오류 발생", e);
            return ResponseEntity.status(500).body("유튜브 데이터 저장 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllYoutubeData() {
        try {
            return ResponseEntity.ok(youtubeService.getAllYoutubeDataWithoutContext());
        } catch (Exception e) {
            log.error("유튜브 데이터 조회 중 오류 발생", e);
            return ResponseEntity.status(500).body("유튜브 데이터 조회 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/{youtubeNum}")
    public ResponseEntity<?> getYoutubeDetail(@PathVariable int youtubeNum) {
        try {
            return ResponseEntity.ok(youtubeService.getYoutubeDataById(youtubeNum));
        } catch (Exception e) {
            log.error("유튜브 상세 데이터 조회 중 오류 발생", e);
            return ResponseEntity.status(500).body("유튜브 상세 데이터 조회 중 오류가 발생했습니다.");
        }
    }
}
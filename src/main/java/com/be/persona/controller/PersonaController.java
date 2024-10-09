package com.be.persona.controller;

import com.be.persona.domain.PersonaVO;
import com.be.persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/get")
    @ResponseBody
    public List<PersonaVO> getPersonas() {
        return personaService.getAllPersonas();
    }

    // 특정 페르소나 ID에 맞는 데이터를 가져오는 엔드포인트
    @GetMapping("/get/{persona_id}")
    public ResponseEntity<PersonaVO> getPersonaById(@PathVariable("persona_id") int persona_id) {
        PersonaVO persona = personaService.getPersonaById(persona_id);
        if (persona != null) {
            return new ResponseEntity<>(persona, HttpStatus.OK);  // 성공 시 전체 컬럼을 반환
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 데이터가 없을 경우 404 반환
        }
    }


    //전체 이미지
    @GetMapping("/crossfit_images")
    public ResponseEntity<List<String>> serveAllImages() {
        try {
            Path dir = Paths.get("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/crossfit_images/");
            List<String> fileUrls = Files.list(dir)
                    .filter(path -> path.toString().endsWith(".jpg"))
                    .map(path -> "http://localhost:8080/api/personas/crossfit_images/" + path.getFileName().toString())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileUrls);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 이미지 서빙용 메서드 추가
    @GetMapping("/crossfit_images/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\crossfit_images\\" + filename);
            Resource resource = new UrlResource(file.toUri());

            String contentType = Files.probeContentType(file);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
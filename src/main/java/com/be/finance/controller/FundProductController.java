package com.be.finance.controller;

import com.be.finance.service.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class FundProductController {

    private final String UPLOAD_DIR = "C:/upload";

    @Autowired
    private FundProductService fundProductService;

    @PostMapping
    public String uploadFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일을 선택해주세요.");
            return "redirect:/upload";
        }

        try {
            // 파일 저장 경로
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

            // 파일 저장
            Files.write(filePath, file.getBytes());

            // 저장된 파일 경로를 Service로 전달
            fundProductService.importFundProduct(filePath.toString());

            redirectAttributes.addFlashAttribute("message", "파일 업로드 성공!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + e.getMessage());
        }

        return "redirect:/upload";
    }
}

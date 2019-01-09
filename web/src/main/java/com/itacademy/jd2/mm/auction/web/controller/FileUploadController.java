package com.itacademy.jd2.mm.auction.web.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

    @RequestMapping(method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") final MultipartFile file,
            final RedirectAttributes redirectAttributes) throws IOException, GeneralSecurityException {
        System.out.printf("Uploaded file %s", file.getOriginalFilename());
        return "redirect:/";
    }
}

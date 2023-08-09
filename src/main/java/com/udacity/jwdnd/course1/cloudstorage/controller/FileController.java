package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(path = "/files")
public class FileController {
    private FileService filesService;
    private UserService userService;
    public FileController(FileService filesService,UserService userService) {
        this.filesService = filesService;
        this.userService = userService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, Authentication authentication) throws Exception {
        String userName = authentication.getName();
        User user = userService.getUserByUserNm(userName);

        FileModel fileModel = new FileModel();
        fileModel.setFileName(file.getOriginalFilename());
        fileModel.setContentType(file.getContentType());
        fileModel.setFileSize(String.valueOf(file.getSize()));
        fileModel.setFileData(file.getBytes());
        fileModel.setUserId(user.getUserId());

        if (!StringUtils.hasText(fileModel.getFileName())
                || !StringUtils.hasText(fileModel.getContentType())
        ) {
            model.addAttribute("error", "File upload failed");
            return "result";
        }
        filesService.save(fileModel);
        model.addAttribute("success", "File were uploaded successfully.");
        return "result";
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam Integer  fileId, Model model) {

        int cnt = 0;
        try {
            cnt = filesService.deleteFile(fileId);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (cnt != 0) {
            model.addAttribute("success", "File were deleted successfully.");
        } else {
            model.addAttribute("error", "File delete failed");
        }
        return "result";
    }

    @GetMapping("downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id){
        FileModel file = filesService.getFileById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFileName()+"\"")
                .body(new ByteArrayResource(file.getFileData()));
    }
}

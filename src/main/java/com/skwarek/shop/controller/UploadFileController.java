package com.skwarek.shop.controller;

import com.skwarek.shop.model.file.UploadFile;
import com.skwarek.shop.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping(value = "/uploadFiles")
    public ResponseEntity<List<UploadFile>> getAllUploadFiles() {
        List<UploadFile> uploadFiles = uploadFileService.findAll();

        if (!uploadFiles.isEmpty()) {
            return ResponseEntity.ok(uploadFiles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/uploadFiles/{uploadFileId}")
    public ResponseEntity<UploadFile> getUploadFileById(@PathVariable("uploadFileId") Long uploadFileId) {
        UploadFile uploadFile = uploadFileService.findById(uploadFileId);

        return ResponseEntity.ok(uploadFile);
    }

    @PostMapping(value = "/uploadFiles/create")
    public ResponseEntity<UploadFile> createUploadFile(@RequestBody UploadFile uploadFileRequest) {
        UploadFile createdUploadFile = uploadFileService.create(uploadFileRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/uploadFiles")
                .path("/{uploadFileId}").buildAndExpand(createdUploadFile.getId()).toUri();

        return ResponseEntity.created(location).body(createdUploadFile);
    }

    @PutMapping(value = "/uploadFiles/{uploadFileId}")
    public ResponseEntity<UploadFile> updateUploadFile(@PathVariable("uploadFileId") Long uploadFileId,
                                                   @RequestBody UploadFile uploadFileRequest) {
        UploadFile updatedUploadFile = uploadFileService.update(uploadFileId, uploadFileRequest);

        return ResponseEntity.ok(updatedUploadFile);
    }

    @DeleteMapping(value = "/uploadFiles/{uploadFileId}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("uploadFileId") Long uploadFileId) {
        uploadFileService.deleteById(uploadFileId);

        return ResponseEntity.noContent().build();
    }

}

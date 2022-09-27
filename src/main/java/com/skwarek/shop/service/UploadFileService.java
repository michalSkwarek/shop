package com.skwarek.shop.service;

import com.skwarek.shop.model.file.UploadFile;

import java.util.List;

public interface UploadFileService {

    List<UploadFile> findAll();

    UploadFile findById(Long uploadFileId);

    UploadFile create(UploadFile uploadFileRequest);

    UploadFile update(Long uploadFileId, UploadFile uploadFileRequest);

    void deleteById(Long uploadFileId);

}

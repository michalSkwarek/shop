package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.UploadFileNotFoundException;
import com.skwarek.shop.model.file.UploadFile;
import com.skwarek.shop.repository.UploadFileRepository;
import com.skwarek.shop.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Override
    public List<UploadFile> findAll() {
        return uploadFileRepository.findAll();
    }

    @Override
    public UploadFile findById(Long uploadFileId) {
        return uploadFileRepository.findById(uploadFileId)
                .orElseThrow(() -> new UploadFileNotFoundException("Not found upload file with id: " + uploadFileId));
    }

    @Override
    public UploadFile create(UploadFile uploadFileRequest) {
        UploadFile newUploadFile = new UploadFile();
        newUploadFile.setFileName(uploadFileRequest.getFileName());
        newUploadFile.setData(null);

        return uploadFileRepository.save(newUploadFile);
    }

    @Override
    public UploadFile update(Long uploadFileId, UploadFile uploadFileRequest) {
        Optional<UploadFile> uploadFileDb = uploadFileRepository.findById(uploadFileId);

        if (uploadFileDb.isPresent()) {
            UploadFile oldUploadFile = uploadFileDb.get();
            oldUploadFile.setFileName(uploadFileRequest.getFileName());
            oldUploadFile.setData(uploadFileRequest.getData());

            return uploadFileRepository.save(oldUploadFile);
        } else {
            throw new UploadFileNotFoundException("Not found upload file with id: " + uploadFileId);
        }
    }

    @Override
    public void deleteById(Long uploadFileId) {
        boolean isUploadFileExists = uploadFileRepository.existsById(uploadFileId);

        if (isUploadFileExists) {
            uploadFileRepository.deleteById(uploadFileId);
        } else {
            throw new UploadFileNotFoundException("Not found upload file with id: " + uploadFileId);
        }
    }

}

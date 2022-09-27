package com.skwarek.shop.repository;

import com.skwarek.shop.model.file.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

}

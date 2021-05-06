package com.demo.springelasticsearch.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadRequestDTO {
	
	@NotBlank private String memberCode;
	  @NotNull private UploadType uploadType;
	  @NotNull private String releaseType;
	  @NotNull private MultipartFile file;

	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date valueDate;

}

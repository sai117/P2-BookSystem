package com.tuny.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.javafx.beans.IDProperty;
import com.tuny.demo.util.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel(value = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@IDProperty("id")
public class BookDomain extends BaseDomain {

  private String author;

  private String image;

  private String publisher;

  private Integer stock;

  private String description;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date dateCreated;

  private String bookName;
}

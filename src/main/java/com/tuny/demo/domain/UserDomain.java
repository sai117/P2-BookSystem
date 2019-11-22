package com.tuny.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.javafx.beans.IDProperty;
import com.tuny.demo.util.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@ApiModel(value = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@IDProperty("id")
public class UserDomain extends BaseDomain {

  private String username;

  private String password;

  //1代表超级管理员，2代表普通用户
  private Integer type;
}

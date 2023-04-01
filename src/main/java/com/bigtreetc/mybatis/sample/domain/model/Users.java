package com.bigtreetc.mybatis.sample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

@Getter
@Setter
public class Users implements Serializable {
  private Long userId;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private String tel;

  private String zip;

  private String address;

  private Long uploadFileId;

  @JsonIgnore @CreatedBy private String createdBy;

  @CreatedDate private LocalDateTime createdAt;

  @JsonIgnore @LastModifiedBy private String updatedBy;

  @LastModifiedDate private LocalDateTime updatedAt;

  @Version private Long version;

  private static final long serialVersionUID = 1L;
}

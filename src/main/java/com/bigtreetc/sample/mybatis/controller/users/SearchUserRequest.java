package com.bigtreetc.sample.mybatis.controller.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchUserRequest {

  private static final long serialVersionUID = -1L;

  Long id;

  String fullName;

  String email;

  String password;
}

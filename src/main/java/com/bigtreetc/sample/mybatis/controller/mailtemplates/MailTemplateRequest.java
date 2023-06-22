package com.bigtreetc.sample.mybatis.controller.mailtemplates;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MailTemplateRequest {

  private static final long serialVersionUID = -1L;

  Long id;

  // カテゴリコード
  String categoryCode;

  // メールテンプレートコード
  @NotEmpty String templateCode;

  // メールタイトル
  @NotEmpty String subject;

  // メール本文
  @NotEmpty String templateBody;

  // 改定番号
  Integer version;
}

package com.bigtreetc.sample.mybatis.controller.staffs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true) // 定義されていないプロパティを無視してマッピングする
@JsonPropertyOrder({"担当者ID", "性", "名", "メールアドレス", "電話番号"}) // CSVのヘッダ順
@Getter
@Setter
public class StaffCsv implements Serializable {

  private static final long serialVersionUID = -1L;

  @JsonProperty("担当者ID")
  Long id;

  // ハッシュ化されたパスワード
  @JsonIgnore // CSVに出力しない
  String password;

  @JsonProperty("名")
  String firstName;

  @JsonProperty("性")
  String lastName;

  @JsonProperty("メールアドレス")
  String email;

  @JsonProperty("電話番号")
  String tel;
}

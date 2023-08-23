package com.bigtreetc.sample.mybatis.domain.model.generated;

import com.bigtreetc.sample.mybatis.base.domain.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

@Getter
@Setter
public class User implements BaseEntity, Serializable {
  private Long id;

  private String firstName;

  private String lastName;

  private String fullName;

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

  public enum Column {
    id("user_id", "id", "BIGINT", false),
    firstName("first_name", "firstName", "VARCHAR", false),
    lastName("last_name", "lastName", "VARCHAR", false),
    fullName("full_name", "fullName", "VARCHAR", false),
    email("email", "email", "VARCHAR", false),
    password("password", "password", "VARCHAR", false),
    tel("tel", "tel", "VARCHAR", false),
    zip("zip", "zip", "VARCHAR", false),
    address("address", "address", "VARCHAR", false),
    uploadFileId("upload_file_id", "uploadFileId", "BIGINT", false),
    createdBy("created_by", "createdBy", "VARCHAR", false),
    createdAt("created_at", "createdAt", "TIMESTAMP", false),
    updatedBy("updated_by", "updatedBy", "VARCHAR", false),
    updatedAt("updated_at", "updatedAt", "TIMESTAMP", false),
    version("version", "version", "BIGINT", false);

    private static final String BEGINNING_DELIMITER = "\"";

    private static final String ENDING_DELIMITER = "\"";

    private final String column;

    private final boolean isColumnNameDelimited;

    private final String javaProperty;

    private final String jdbcType;

    public String value() {
      return this.column;
    }

    public String getValue() {
      return this.column;
    }

    public String getJavaProperty() {
      return this.javaProperty;
    }

    public String getJdbcType() {
      return this.jdbcType;
    }

    Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
      this.column = column;
      this.javaProperty = javaProperty;
      this.jdbcType = jdbcType;
      this.isColumnNameDelimited = isColumnNameDelimited;
    }

    public String desc() {
      return this.getEscapedColumnName() + " DESC";
    }

    public String asc() {
      return this.getEscapedColumnName() + " ASC";
    }

    public static Column[] excludes(Column... excludes) {
      ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
      if (excludes != null && excludes.length > 0) {
        columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
      }
      return columns.toArray(new Column[] {});
    }

    public static Column[] all() {
      return Column.values();
    }

    public String getEscapedColumnName() {
      if (this.isColumnNameDelimited) {
        return new StringBuilder()
            .append(BEGINNING_DELIMITER)
            .append(this.column)
            .append(ENDING_DELIMITER)
            .toString();
      } else {
        return this.column;
      }
    }

    public String getAliasedEscapedColumnName() {
      return this.getEscapedColumnName();
    }
  }
}

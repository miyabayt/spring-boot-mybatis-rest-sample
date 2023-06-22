package com.bigtreetc.sample.mybatis.controller.codecategories;

import com.bigtreetc.sample.mybatis.base.domain.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/** コード分類マスタ登録 入力チェック */
@Component
public class CodeCategoryRequestValidator extends AbstractValidator<CodeCategoryRequest> {

  @Override
  protected void doValidate(CodeCategoryRequest request, Errors errors) {}
}

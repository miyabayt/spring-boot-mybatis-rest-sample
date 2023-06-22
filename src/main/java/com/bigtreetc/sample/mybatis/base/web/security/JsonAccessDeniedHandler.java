package com.bigtreetc.sample.mybatis.base.web.security;

import com.bigtreetc.sample.mybatis.base.util.MessageUtils;
import com.bigtreetc.sample.mybatis.base.web.BaseWebConst;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.ErrorApiResponseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/** JSON形式で認可エラーをレスポンスするためのハンドラー */
@Slf4j
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    val apiResponse = new ErrorApiResponseImpl();
    apiResponse.setMessage(MessageUtils.getMessage(BaseWebConst.ACCESS_DENIED_ERROR));
    response.setHeader(
        HttpHeaders.CONTENT_TYPE,
        new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8).toString());
    response.setStatus(HttpStatus.FORBIDDEN.value());

    try (val writer = response.getWriter()) {
      OBJECT_MAPPER.writeValue(writer, apiResponse);
    }
  }
}

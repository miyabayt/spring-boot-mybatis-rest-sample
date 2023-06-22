package com.bigtreetc.sample.mybatis.controller.uploadfiles;

import static com.bigtreetc.sample.mybatis.base.web.BaseWebConst.ACCESS_DENIED_ERROR;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bigtreetc.sample.mybatis.BaseTestContainerTest;
import com.bigtreetc.sample.mybatis.base.util.MessageUtils;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class UploadFileControllerTest extends BaseTestContainerTest {

  @Autowired WebApplicationContext wac;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
  }

  @Test
  @DisplayName("権限を持つロールで、ファイル一覧を取得できること")
  @WithMockUser(authorities = "uploadFile")
  void test1() throws Exception {
    mockMvc
        .perform(get("/api/system/files"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value("true"))
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  @DisplayName("権限を持たないロールでは、ファイル一覧を取得できないこと")
  @WithAnonymousUser
  void test2() throws Exception {
    val message = MessageUtils.getMessage(ACCESS_DENIED_ERROR);

    mockMvc
        .perform(get("/api/system/files"))
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$.success").value("false"))
        .andExpect(jsonPath("$.message").value(message));
  }
}

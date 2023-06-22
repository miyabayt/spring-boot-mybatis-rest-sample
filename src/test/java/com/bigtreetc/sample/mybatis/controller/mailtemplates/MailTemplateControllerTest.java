package com.bigtreetc.sample.mybatis.controller.mailtemplates;

import static com.bigtreetc.sample.mybatis.base.web.BaseWebConst.ACCESS_DENIED_ERROR;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
class MailTemplateControllerTest extends BaseTestContainerTest {

  @Autowired WebApplicationContext wac;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
  }

  @Test
  @DisplayName("権限を持つロールで、メールテンプレートを検索できること")
  @WithMockUser(authorities = "mailTemplate:read")
  void test1() throws Exception {
    mockMvc
        .perform(get("/api/system/mailTemplates"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value("true"))
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  @DisplayName("権限を持たないロールでは、メールテンプレートを検索できないこと")
  @WithAnonymousUser
  void test2() throws Exception {
    val message = MessageUtils.getMessage(ACCESS_DENIED_ERROR);

    mockMvc
        .perform(get("/api/system/mailTemplates"))
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$.success").value("false"))
        .andExpect(jsonPath("$.message").value(message));
  }

  @Test
  @DisplayName("権限を持つロールで、メールテンプレートCSVを出力できること")
  @WithMockUser(authorities = "mailTemplate:read")
  void test3() throws Exception {
    mockMvc
        .perform(get("/api/system/mailTemplates/export/test.csv"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/octet-stream"));
  }
}

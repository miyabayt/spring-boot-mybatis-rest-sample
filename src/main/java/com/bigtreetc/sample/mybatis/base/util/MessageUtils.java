package com.bigtreetc.sample.mybatis.base.util;

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;

@Slf4j
public class MessageUtils {

  private static MessageSource messageSource;

  public static void init(MessageSource messageSource) {
    MessageUtils.messageSource = messageSource;
  }

  /**
   * メッセージを取得します。
   *
   * @param key
   * @param args
   * @return
   */
  public static String getMessage(String key, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return MessageUtils.messageSource.getMessage(key, args, locale);
  }

  /**
   * ロケールを指定してメッセージを取得します。
   *
   * @param key
   * @param locale
   * @param args
   * @return
   */
  public static String getMessage(String key, Locale locale, Object... args) {
    return MessageUtils.messageSource.getMessage(key, args, locale);
  }

  /**
   * メッセージを取得します。
   *
   * @param resolvable
   * @return
   */
  public static String getMessage(MessageSourceResolvable resolvable) {
    Locale locale = LocaleContextHolder.getLocale();
    return MessageUtils.messageSource.getMessage(resolvable, locale);
  }
}

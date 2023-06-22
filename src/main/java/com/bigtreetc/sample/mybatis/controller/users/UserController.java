package com.bigtreetc.sample.mybatis.controller.users;

import static com.bigtreetc.sample.mybatis.base.util.ValidateUtils.isNotEmpty;
import static java.util.stream.Collectors.toList;

import com.bigtreetc.sample.mybatis.base.exception.ValidationErrorException;
import com.bigtreetc.sample.mybatis.base.web.controller.api.AbstractRestController;
import com.bigtreetc.sample.mybatis.base.web.controller.api.request.Requests;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.ApiResponse;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.CountApiResponseImpl;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.PageableApiResponseImpl;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.SimpleApiResponseImpl;
import com.bigtreetc.sample.mybatis.controller.codes.SearchCodeRequest;
import com.bigtreetc.sample.mybatis.domain.model.generated.User;
import com.bigtreetc.sample.mybatis.domain.model.generated.UserExample;
import com.bigtreetc.sample.mybatis.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ユーザ")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController extends AbstractRestController {

  @NonNull final UserRequestValidator userRequestValidator;

  @NonNull final UserService userService;

  @NonNull final PasswordEncoder passwordEncoder;

  @InitBinder
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(userRequestValidator);
  }

  /**
   * ユーザを登録します。
   *
   * @param request
   * @return
   */
  @Operation(summary = "ユーザ登録", description = "ユーザを登録します。")
  @PreAuthorize("hasAuthority('user:save')")
  @PostMapping("/user")
  public ApiResponse create(@Validated @RequestBody UserRequest request, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からモデルを作成する
    val user = modelMapper.map(request, User.class);
    val password = request.getPassword();

    // パスワードをハッシュ化する
    user.setPassword(passwordEncoder.encode(password));

    // 1件登録する
    val data = userService.create(user);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * ユーザを一括登録します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "ユーザ一括登録", description = "ユーザを一括登録します。")
  @PreAuthorize("hasAuthority('user:save')")
  @PostMapping(value = "/users")
  public ApiResponse createAll(
      @Validated @RequestBody Requests<UserRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からモデルを作成する
    val users = requests.stream().map(f -> modelMapper.map(f, User.class)).toList();

    // 一括登録する
    val created = userService.createAll(users);

    val response = new CountApiResponseImpl();
    response.success(created);

    return response;
  }

  /**
   * ユーザを検索します。
   *
   * @param request
   * @param pageable
   * @return
   */
  @PageableAsQueryParam
  @Operation(summary = "ユーザ検索", description = "ユーザを検索します。")
  @PreAuthorize("hasAuthority('user:read')")
  @GetMapping("/users")
  public ApiResponse search(
      @ModelAttribute SearchUserRequest request, @Parameter(hidden = true) Pageable pageable) {
    // 入力値からDTOを作成する
    val example = modelMapper.map(request, UserExample.class);

    // 10件で区切って取得する
    val data = userService.findAll(example, pageable);

    val response = new PageableApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * ユーザを検索します。（POST版）
   *
   * @param request
   * @param pageable
   * @return
   */
  @PageableAsQueryParam
  @Operation(summary = "ユーザ検索", description = "ユーザを検索します。")
  @PreAuthorize("hasAuthority('user:read')")
  @PostMapping("/users/search")
  public ApiResponse searchByPost(
      @RequestBody SearchUserRequest request, @Parameter(hidden = true) Pageable pageable) {
    return search(request, pageable);
  }

  /**
   * ユーザを取得します。
   *
   * @param userId
   * @return
   */
  @Operation(summary = "ユーザ取得", description = "ユーザを取得します。")
  @PreAuthorize("hasAuthority('user:read')")
  @GetMapping("/user/{userId}")
  public ApiResponse findOne(@PathVariable Long userId) {
    // 1件取得する
    val data = userService.findById(userId);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * ユーザを更新します。
   *
   * @param userId
   * @param request
   * @return
   */
  @Operation(summary = "ユーザ更新", description = "ユーザを更新します。")
  @PreAuthorize("hasAuthority('user:save')")
  @PutMapping("/user/{userId}")
  public ApiResponse update(
      @PathVariable Long userId, @Validated @RequestBody UserRequest request, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val user = modelMapper.map(request, User.class);
    val password = user.getPassword();
    if (isNotEmpty(password)) {
      val encodedPassword = passwordEncoder.encode(password);
      user.setPassword(encodedPassword);
    }

    // 1件更新する
    user.setId(userId);
    val data = userService.update(user);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * ユーザを一括更新します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "ユーザ一括更新", description = "ユーザを一括更新します。")
  @PreAuthorize("hasAuthority('user:save')")
  @PutMapping(value = "/users")
  public ApiResponse updateAll(
      @Validated @RequestBody Requests<UserRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val users = requests.stream().map(f -> modelMapper.map(f, User.class)).collect(toList());

    // 一括更新する
    val updated = userService.updateAll(users);

    val response = new CountApiResponseImpl();
    response.success(updated);

    return response;
  }

  /**
   * ユーザを削除します。
   *
   * @param userId
   * @return
   */
  @Operation(summary = "ユーザ削除", description = "ユーザを削除します。")
  @PreAuthorize("hasAuthority('user:save')")
  @DeleteMapping("/user/{userId}")
  public ApiResponse delete(@PathVariable Long userId) {
    // 1件取得する
    val data = userService.delete(userId);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * ユーザを一括削除します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "ユーザ一括削除", description = "ユーザを一括削除します。")
  @PreAuthorize("hasAuthority('user:save')")
  @DeleteMapping(value = "/users")
  public ApiResponse deleteAll(
      @Validated @RequestBody Requests<DeleteUserRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val users = requests.stream().map(f -> modelMapper.map(f, User.class)).collect(toList());

    // 一括削除する
    val deleted = userService.deleteAll(users);

    val response = new CountApiResponseImpl();
    response.success(deleted);

    return response;
  }

  /**
   * CSV出力
   *
   * @param filename
   * @param request
   * @param response
   * @return
   */
  @Operation(summary = "ユーザCSV出力", description = "CSVファイルを出力します。")
  @PreAuthorize("hasAuthority('user:read')")
  @GetMapping("/users/export/{filename:.+\\.csv}")
  public void downloadCsv(
      @PathVariable String filename,
      @ModelAttribute SearchCodeRequest request,
      HttpServletResponse response)
      throws IOException {
    // ダウンロード時のファイル名をセットする
    setContentDispositionHeader(response, filename, true);

    // 入力値から検索条件を作成する
    val example = modelMapper.map(request, UserExample.class);

    // CSV出力する
    try (val outputStream = response.getOutputStream()) {
      userService.writeToOutputStream(outputStream, example, UserCsv.class);
    }
  }

  /**
   * CSV出力（POST版）
   *
   * @param request
   * @param response
   * @return
   */
  @PageableAsQueryParam
  @Operation(summary = "ユーザCSV出力", description = "CSVファイルを出力します。")
  @PreAuthorize("hasAuthority('user:read')")
  @PostMapping("/users/export/{filename:.+\\.csv}")
  public void searchByPost(
      @PathVariable String filename,
      @RequestBody SearchCodeRequest request,
      HttpServletResponse response)
      throws IOException {
    downloadCsv(filename, request, response);
  }
}

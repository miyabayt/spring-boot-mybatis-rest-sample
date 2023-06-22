package com.bigtreetc.sample.mybatis.controller.staffs;

import static com.bigtreetc.sample.mybatis.base.util.ValidateUtils.isNotEmpty;
import static java.util.stream.Collectors.toList;

import com.bigtreetc.sample.mybatis.base.exception.ValidationErrorException;
import com.bigtreetc.sample.mybatis.base.web.controller.api.AbstractRestController;
import com.bigtreetc.sample.mybatis.base.web.controller.api.request.Requests;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.ApiResponse;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.CountApiResponseImpl;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.PageableApiResponseImpl;
import com.bigtreetc.sample.mybatis.base.web.controller.api.response.SimpleApiResponseImpl;
import com.bigtreetc.sample.mybatis.domain.model.generated.Staff;
import com.bigtreetc.sample.mybatis.domain.model.generated.StaffExample;
import com.bigtreetc.sample.mybatis.domain.service.StaffService;
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

@Tag(name = "担当者マスタ")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/system", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StaffController extends AbstractRestController {

  @NonNull final StaffRequestValidator staffRequestValidator;

  @NonNull final StaffService staffService;

  @NonNull final PasswordEncoder passwordEncoder;

  @InitBinder
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(staffRequestValidator);
  }

  /**
   * 担当者マスタを登録します。
   *
   * @param request
   * @return
   */
  @Operation(summary = "担当者マスタ登録", description = "担当者マスタを登録します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @PostMapping("/staff")
  public ApiResponse create(@Validated @RequestBody StaffRequest request, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からモデルを作成する
    val staff = modelMapper.map(request, Staff.class);
    val password = request.getPassword();

    // パスワードをハッシュ化する
    staff.setPassword(passwordEncoder.encode(password));

    // 1件登録する
    val data = staffService.create(staff);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * 担当者マスタを一括登録します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "担当者マスタ一括登録", description = "担当者マスタを一括登録します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @PostMapping(value = "/staffs")
  public ApiResponse createAll(
      @Validated @RequestBody Requests<StaffRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からモデルを作成する
    val staffs = requests.stream().map(f -> modelMapper.map(f, Staff.class)).toList();

    // 一括登録する
    val created = staffService.createAll(staffs);

    val response = new CountApiResponseImpl();
    response.success(created);

    return response;
  }

  /**
   * 担当者マスタを検索します。
   *
   * @param request
   * @param pageable
   * @return
   */
  @PageableAsQueryParam
  @Operation(summary = "担当者マスタ検索", description = "担当者マスタを検索します。")
  @PreAuthorize("hasAuthority('staff:read')")
  @GetMapping("/staffs")
  public ApiResponse search(
      @ModelAttribute SearchStaffRequest request, @Parameter(hidden = true) Pageable pageable) {
    // 入力値からDTOを作成する
    val example = modelMapper.map(request, StaffExample.class);

    // 10件で区切って取得する
    val data = staffService.findAll(example, pageable);

    val response = new PageableApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * 担当者マスタを検索します。（POST版）
   *
   * @param request
   * @param pageable
   * @return
   */
  @PageableAsQueryParam
  @Operation(summary = "担当者マスタ検索", description = "担当者マスタを検索します。")
  @PreAuthorize("hasAuthority('staff:read')")
  @PostMapping("/staffs/search")
  public ApiResponse searchByPost(
      @RequestBody SearchStaffRequest request, @Parameter(hidden = true) Pageable pageable) {
    return search(request, pageable);
  }

  /**
   * 担当者マスタを取得します。
   *
   * @param staffId
   * @return
   */
  @Operation(summary = "担当者マスタ取得", description = "担当者マスタを取得します。")
  @PreAuthorize("hasAuthority('staff:read')")
  @GetMapping("/staff/{staffId}")
  public ApiResponse findOne(@PathVariable Long staffId) {
    // 1件取得する
    val data = staffService.findById(staffId);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * 担当者マスタを更新します。
   *
   * @param staffId
   * @param request
   * @return
   */
  @Operation(summary = "担当者マスタ更新", description = "担当者マスタを更新します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @PutMapping("/staff/{staffId}")
  public ApiResponse update(
      @PathVariable Long staffId, @Validated @RequestBody StaffRequest request, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val staff = modelMapper.map(request, Staff.class);
    val password = staff.getPassword();
    if (isNotEmpty(password)) {
      val encodedPassword = passwordEncoder.encode(password);
      staff.setPassword(encodedPassword);
    }

    // 1件更新する
    staff.setId(staffId);
    val data = staffService.update(staff);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * 担当者マスタを一括更新します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "担当者マスタ一括更新", description = "担当者マスタを一括更新します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @PutMapping(value = "/staffs")
  public ApiResponse update(
      @Validated @RequestBody Requests<StaffRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val staffs = requests.stream().map(f -> modelMapper.map(f, Staff.class)).collect(toList());

    // 一括更新する
    val updated = staffService.updateAll(staffs);

    val response = new CountApiResponseImpl();
    response.success(updated);

    return response;
  }

  /**
   * 担当者マスタを削除します。
   *
   * @param staffId
   * @return
   */
  @Operation(summary = "担当者マスタ削除", description = "担当者マスタを削除します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @DeleteMapping("/staff/{staffId}")
  public ApiResponse delete(@PathVariable Long staffId) {
    // 1件取得する
    val data = staffService.delete(staffId);

    val response = new SimpleApiResponseImpl();
    response.success(data);

    return response;
  }

  /**
   * 担当者マスタを一括削除します。
   *
   * @param requests
   * @return
   */
  @Operation(summary = "担当者マスタ一括削除", description = "担当者マスタを一括削除します。")
  @PreAuthorize("hasAuthority('staff:save')")
  @DeleteMapping(value = "/staffs")
  public ApiResponse deleteAll(
      @Validated @RequestBody Requests<DeleteStaffRequest> requests, Errors errors) {
    // 入力エラーがある場合
    if (errors.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    val staffs = requests.stream().map(f -> modelMapper.map(f, Staff.class)).collect(toList());

    // 一括削除する
    val deleted = staffService.deleteAll(staffs);

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
  @Operation(summary = "担当者マスタCSV出力", description = "CSVファイルを出力します。")
  @PreAuthorize("hasAuthority('staff:read')")
  @GetMapping("/staffs/export/{filename:.+\\.csv}")
  public void downloadCsv(
      @PathVariable String filename,
      @ModelAttribute SearchStaffRequest request,
      HttpServletResponse response)
      throws IOException {
    // ダウンロード時のファイル名をセットする
    setContentDispositionHeader(response, filename, true);

    // 入力値から検索条件を作成する
    val example = modelMapper.map(request, StaffExample.class);

    // CSV出力する
    try (val outputStream = response.getOutputStream()) {
      staffService.writeToOutputStream(outputStream, example, StaffCsv.class);
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
  @Operation(summary = "担当者マスタCSV出力", description = "CSVファイルを出力します。")
  @PreAuthorize("hasAuthority('staff:read')")
  @PostMapping("/staffs/export/{filename:.+\\.csv}")
  public void searchByPost(
      @PathVariable String filename,
      @RequestBody SearchStaffRequest request,
      HttpServletResponse response)
      throws IOException {
    downloadCsv(filename, request, response);
  }
}

package com.example.memoapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// アプリケーション全体のエラーを処理するクラスであることを示す
@ControllerAdvice
public class GlobalExceptionHandler {

  // MemoNotFoundExceptionが発生した時の処理方法を定義
  @ExceptionHandler(MemoNotFoundException.class)
  public ResponseEntity<String> handleMemoNotFoundException(MemoNotFoundException ex) {
    // エラーメッセージを404ステータスコードと共に返す
    return ResponseEntity.status(404).body(ex.getMessage());
  }

}

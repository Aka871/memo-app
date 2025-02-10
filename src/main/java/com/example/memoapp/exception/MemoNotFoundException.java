package com.example.memoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemoNotFoundException extends RuntimeException {

  public MemoNotFoundException(Integer id) {
    super("メモが見つかりません (ID: " + id + ") ");
  }
}

package com.example.memoapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.memoapp.entity.Memo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Spring Bootのテスト用アノテーション
@SpringBootTest
public class MemoControllerTest {

  // MemoControllerを自動で注入。テスト対象のクラスを自動でインスタンス化
  @Autowired
  private MemoController memoController;

  // テストメソッドであることを示すアノテーション
  @Test
  void メモ一覧を取得できること() {
    List<Memo> memos = memoController.getMemos();
    assertNotNull(memos);
  }
}

package com.example.memoapp.controller;

import com.example.memoapp.entity.Memo;
import com.example.memoapp.exception.MemoNotFoundException;
import com.example.memoapp.mapper.MemoMapper;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {

  // MemoMapperを使ってデータベースへの保存や取得を行う
  private final MemoMapper memoMapper;

  // MemoControllerはMemoMapperに依存している
  // Spring Bootがこのコンストラクタを使って、MemoMapperを自動的に注入する
  // コンストラクタインジェクションの方法で、MemoMapperをこのクラスで使えるようにする
  public MemoController(MemoMapper memoMapper) {
    this.memoMapper = memoMapper;
  }

  // メモ一覧を取得
  @GetMapping("/memos")
  public List<Memo> getMemos() {
    return memoMapper.findAll();
  }

  // メモをIDで取得
  // @PathVariableアノテーションを使って、URLの一部を引数として受け取る
  @GetMapping("memos/{id}")
  public Memo getMemoById(@PathVariable Integer id) {
    Memo memo = memoMapper.findById(id);
    if (memo == null) {
      throw new MemoNotFoundException(id);
    }
    return memo;
  }

  // メモを新規登録
  // クライアントから送られてきたデータ（JSON形式）をMemo型の変数memoとして定義
  // @RequestBodyアノテーションを使ってMemoクラスで定義した形に変換
  @PostMapping("/memos")
  public void insertMemo(@RequestBody Memo memo) {
    // データベースにメモを保存
    memoMapper.insert(memo);
  }

  // /memos/{id}へのPUTリクエストを処理するメソッド
  // 例：/memos/1 にPUTリクエストが来たら、ID=1のメモを更新する
  @PutMapping("/memos/{id}")
  public void updateMemo(@PathVariable Integer id, @RequestBody Memo memo) {

    // 更新対象のメモが存在しない場合は、MemoNotFoundExceptionをスロー
    if (memoMapper.findById(id) == null) {
      throw new MemoNotFoundException(id);
    }
    // URLのパスから取得したIDをMemoオブジェクトにセット
    // 例：/memos/1 の場合、id=1がセットされる
    memo.setId(id);

    // MemoMapperのupdateメソッドを呼び出して、データベースのメモを更新
    // SQLのUPDATE文が実行される
    memoMapper.update(memo);
  }

  // メモの削除
  @DeleteMapping("/memos/{id}")
  public void deleteMemo(@PathVariable Integer id) {
    if (memoMapper.findById(id) == null) {
      throw new MemoNotFoundException(id);
    }
    memoMapper.delete(id);
  }
}

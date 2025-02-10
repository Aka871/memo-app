package com.example.memoapp.mapper;

import com.example.memoapp.entity.Memo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemoMapper {

  @Select("SELECT * FROM memos")
  public List<Memo> findAll();

  @Select("SELECT * FROM memos WHERE id = #{id}")
  public Memo findById(Integer id);

  // DBのカラムとJavaのフィールドを対応させて、データを保存する指示
  // memos (title, content)は、DBのテーブルとカラムを指定。どこに保存するか
  // #{title}, #{content}は、Memoクラスのフィールドを指定。どのフィールドの値を使うか
  @Insert("INSERT INTO memos (title, content) VALUES (#{title}, #{content})")
  public void insert(Memo memo);

  // メモの更新用メソッド
  // 指定したIDのメモのtitleとcontentを更新する
  @Update("UPDATE memos SET title = #{title}, content = #{content} WHERE id = #{id}")
  public void update(Memo memo);

  // メモの削除用メソッド
  @Delete("DELETE FROM memos WHERE id = #{id}")
  public void delete(Integer id);
}

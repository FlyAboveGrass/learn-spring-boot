package com.example.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.library.dto.request.BookInsertDTO;
import com.example.library.dto.request.BookUpdateDTO;
import com.example.library.entity.Book;
// 导入 MyBatis 注解。用于注解 Mapper 接口
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookMapper {
  @Select("SELECT * FROM book")
  List<Book> findAll();

  @Select("SELECT * FROM book WHERE book_id = #{bookId}")
  Book findById(@Param("bookId") Integer bookId);

  @Select("SELECT * FROM book WHERE isbn = #{isbn}")
  Book findByIsbn(@Param("isbn") String isbn);

  @Select("SELECT * FROM book WHERE book_id = #{bookId}")
  Book findByBookId(@Param("bookId") Integer bookId);
  
  // 使用 mybatis 的 xml 文件更新方式
  void insert(BookInsertDTO bookInsertDTO);

  // 使用 mybatis 的 xml 文件更新方式
  void update(BookUpdateDTO bookUpdateDTO);
  
  @Delete("DELETE FROM book WHERE book_id = #{bookId}")
  void delete(@Param("bookId") Integer bookId);

  @Delete("DELETE FROM book WHERE isbn = #{isbn}")
  void deleteByIsbn(@Param("isbn") String isbn);

  // 更新图书可用副本数
  @Update("UPDATE book SET available_copies = available_copies + #{change} WHERE book_id = #{bookId}")
  void updateAvailableCopies(@Param("bookId") Integer bookId, @Param("change") Integer change);
}

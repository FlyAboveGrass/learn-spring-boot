package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.BookInsertDTO;
import com.example.library.dto.request.BookUpdateDTO;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class BookService {
  @Autowired
  private BookMapper bookMapper;

  public List<Book> findAll() {
    return bookMapper.findAll();
  }

  public Book findById(Integer bookId) {
    return bookMapper.findById(bookId);
  }

  public Book findByIsbn(String isbn) {
    return bookMapper.findByIsbn(isbn);
  }

  public void insert(BookInsertDTO bookInsertDTO) {
    // 检查图书是否已经存在
    Book book = bookMapper.findByIsbn(bookInsertDTO.getIsbn());
    if (book != null) {
      throw new RuntimeException("图书已存在");
    }

    bookMapper.insert(bookInsertDTO);
  }

  public void update(BookUpdateDTO bookUpdateDTO) {
    Book book = null;
    if (bookUpdateDTO.getBookId() != null) {
      book = bookMapper.findByBookId(bookUpdateDTO.getBookId());
    } else if (bookUpdateDTO.getIsbn() != null) {
      book = bookMapper.findByIsbn(bookUpdateDTO.getIsbn());
    } else {
      throw new RuntimeException("bookId 或 isbn 不能同时为空");
    }
    log.info("book: {}", book);

    if (book == null) {
      throw new RuntimeException("图书不存在");
    }

    bookMapper.update(bookUpdateDTO);
  }

  public void delete(Integer bookId) {
    bookMapper.delete(bookId);
  }

  public void deleteByIsbn(String isbn) {
    bookMapper.deleteByIsbn(isbn);
  }
}


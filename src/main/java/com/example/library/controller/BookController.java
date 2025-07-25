package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.BookInsertDTO;
import com.example.library.dto.request.BookUpdateDTO;
import com.example.library.entity.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;



import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  // 查询所有图书
  @GetMapping("/findAll")
  public List<Book> findAll() {
    return bookService.findAll();
  }

  // 根据图书ID查询图书
  @GetMapping("/{bookId}")
  public Book findByBookId(@PathVariable Integer bookId) {
    return bookService.findById(bookId);
  }

  // 根据图书ISBN查询图书
  @GetMapping("/byIsbn/{isbn}")
  public Book findByIsbn(@PathVariable String isbn) {
    return bookService.findByIsbn(isbn);
  }

  // 插入新图书
  @PostMapping("/insert")
  public void insert(@RequestBody @Valid BookInsertDTO bookInsertDTO) {
    bookService.insert(bookInsertDTO);
  }

  // 更新图书
  @PostMapping("/update")
  public void update(@RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
    bookService.update(bookUpdateDTO);
  }

  // 删除图书 by bookId
  @PostMapping("/delete/{bookId}")
  public void delete(@PathVariable Integer bookId) {
    bookService.delete(bookId);
  }
  
  // 删除图书 by isbn
  @PostMapping("/deleteByIsbn/{isbn}")
  public void deleteByIsbn(@PathVariable String isbn) {
    bookService.deleteByIsbn(isbn);
  }
}
  
  
  

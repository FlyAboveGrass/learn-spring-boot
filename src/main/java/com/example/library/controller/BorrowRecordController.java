package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.library.dto.request.BorrowBookDTO;
import com.example.library.dto.request.ReturnBookDTO;
import com.example.library.dto.request.BorrowRecordQueryDTO;
import com.example.library.entity.BorrowRecord;
import com.example.library.service.BorrowRecordService;

import jakarta.validation.Valid;

import java.util.List;

/**
 * 借阅记录控制器
 */
@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    /**
     * 借书
     */
    @PostMapping("/borrow")
    public void borrowBook(@RequestBody @Valid BorrowBookDTO borrowBookDTO) {
        borrowRecordService.borrowBook(borrowBookDTO);
    }

    /**
     * 还书
     */
    @PostMapping("/return")
    public void returnBook(@RequestBody @Valid ReturnBookDTO returnBookDTO) {
        borrowRecordService.returnBook(returnBookDTO);
    }

    /**
     * 根据条件查询借阅记录（支持userId、bookId、status、borrowId查询）
     */
    @PostMapping("/search")
    public List<BorrowRecord> findByCondition(@RequestBody BorrowRecordQueryDTO queryDTO) {
        return borrowRecordService.findByCondition(queryDTO);
    }
}

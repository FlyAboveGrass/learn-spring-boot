package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.dto.request.BorrowBookDTO;
import com.example.library.dto.request.ReturnBookDTO;
import com.example.library.dto.request.BorrowRecordQueryDTO;
import com.example.library.dto.response.BorrowRecordVO;
import com.example.library.entity.BorrowRecord;
import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.mapper.BorrowRecordMapper;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.UserMapper;
import com.example.library.constants.BookStatus;
import com.example.library.constants.BorrowStatus;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.sql.Date;
import java.math.BigDecimal;

/**
 * 借阅记录服务类
 */
@Service
@Slf4j
public class BorrowRecordService {
    
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    
    @Autowired
    private BookMapper bookMapper;
    
    @Autowired
    private UserMapper userMapper;

    /**
     * 借书
     */
    @Transactional
    public void borrowBook(BorrowBookDTO borrowBookDTO) {
        log.info("开始处理借书请求: {}", borrowBookDTO);
        
        // 1. 验证用户是否存在
        User user = userMapper.findById(borrowBookDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 2. 验证图书是否存在
        Book book = bookMapper.findById(borrowBookDTO.getBookId());
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        
        // 3. 检查图书是否可借
        if (!BookStatus.AVAILABLE.equals(book.getStatus())) {
            throw new RuntimeException("图书当前不可借阅，状态为: " + book.getStatus());
        }
        
        // 4. 检查图书是否有可用副本
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("图书没有可用副本");
        }
        
        // 5. 检查用户当前借阅数量（假设每个用户最多借5本书）
        Integer activeBorrowCount = borrowRecordMapper.countActiveBorrowsByUserId(borrowBookDTO.getUserId());
        if (activeBorrowCount >= 5) {
            throw new RuntimeException("用户当前借阅数量已达上限（5本）");
        }
        
        // 6. 插入借阅记录（默认值已在DTO的getter方法中处理）
        borrowRecordMapper.insert(borrowBookDTO);
        
        // 7. 更新图书可用副本数
        bookMapper.updateAvailableCopies(borrowBookDTO.getBookId(), -1);
        
        log.info("借书成功: 用户ID={}, 图书ID={}, 借阅日期={}, 应还日期={}", 
                borrowBookDTO.getUserId(), borrowBookDTO.getBookId(), 
                borrowBookDTO.getBorrowDate(), borrowBookDTO.getDueDate());
    }

    /**
     * 还书
     */
    @Transactional
    public void returnBook(ReturnBookDTO returnBookDTO) {
        log.info("开始处理还书请求: {}", returnBookDTO);
        
        // 1. 验证借阅记录是否存在
        BorrowRecord borrowRecord = borrowRecordMapper.findById(returnBookDTO.getBorrowId());
        if (borrowRecord == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        
        // 2. 检查借阅记录状态
        if (BorrowStatus.RETURNED.equals(borrowRecord.getStatus())) {
            throw new RuntimeException("该借阅记录已经归还");
        }
        
        // 3. 计算罚金（如果用户没有指定罚金，使用计算出的罚金）
        BigDecimal calculatedFine = calculateFine(borrowRecord.getDueDate(), returnBookDTO.getReturnDate());
        if (returnBookDTO.getFineAmount().equals(BigDecimal.ZERO)) {
            returnBookDTO.setFineAmount(calculatedFine);
        }
        
        // 4. 更新借阅记录
        borrowRecordMapper.updateReturn(returnBookDTO);
        
        // 5. 更新图书可用副本数
        bookMapper.updateAvailableCopies(borrowRecord.getBookId(), 1);
        
        log.info("还书成功: 借阅记录ID={}, 归还日期={}, 罚金={}", 
                returnBookDTO.getBorrowId(), returnBookDTO.getReturnDate(), returnBookDTO.getFineAmount());
    }
    
    /**
     * 计算罚金
     * 如果归还日期晚于截止日期，则每天需要缴纳0.3元的罚金，最高15元
     */
    private BigDecimal calculateFine(Date dueDate, Date returnDate) {
        if (returnDate.before(dueDate) || returnDate.equals(dueDate)) {
            // 按时归还或提前归还，无罚金
            return BigDecimal.ZERO;
        }
        
        // 计算逾期天数
        long daysOverdue = (returnDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
        
        // 计算罚金：每天0.3元，最高15元
        BigDecimal dailyFine = new BigDecimal("0.3");
        BigDecimal totalFine = dailyFine.multiply(new BigDecimal(daysOverdue));
        BigDecimal maxFine = new BigDecimal("15.0");
        
        // 返回罚金，不超过最高限额
        return totalFine.min(maxFine);
    }

    /**
     * 根据条件查询借阅记录（包含用户和书籍信息）
     */
    public List<BorrowRecordVO> findByCondition(BorrowRecordQueryDTO queryDTO) {
        return borrowRecordMapper.findByCondition(queryDTO);
    }
}

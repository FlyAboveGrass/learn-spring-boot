package com.example.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.library.dto.request.BorrowBookDTO;
import com.example.library.dto.request.ReturnBookDTO;
import com.example.library.dto.request.BorrowRecordQueryDTO;
import com.example.library.dto.response.BorrowRecordVO;
import com.example.library.entity.BorrowRecord;


/**
 * 借阅记录Mapper接口
 */
@Mapper
public interface BorrowRecordMapper {
    
    /**
     * 根据借阅记录ID查询
     */
    @Select("SELECT * FROM borrow_record WHERE borrow_id = #{borrowId}")
    BorrowRecord findById(@Param("borrowId") Integer borrowId);

    /**
     * 查询用户当前借阅中的图书数量
     */
    @Select("SELECT COUNT(*) FROM borrow_record WHERE user_id = #{userId} AND status = 'ACTIVE'")
    Integer countActiveBorrowsByUserId(@Param("userId") Integer userId);

    /**
     * 插入借阅记录
     */
    void insert(BorrowBookDTO borrowBookDTO);

    /**
     * 更新借阅记录（还书）
     */
    void updateReturn(ReturnBookDTO returnBookDTO);

    /**
     * 根据条件查询借阅记录（包含用户和书籍信息）
     */
    List<BorrowRecordVO> findByCondition(BorrowRecordQueryDTO queryDTO);
}

package com.tuny.demo.dao;

import com.tuny.demo.domain.BookDomain;
import com.tuny.demo.util.BaseDao;
import com.tuny.demo.vo.book.BookFindVo;
import com.tuny.demo.vo.book.BookOutVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends BaseDao<BookDomain> {
    List<BookOutVo> bookList(BookFindVo param);
}

package com.tuny.demo.service;

import com.github.pagehelper.PageInfo;
import com.tuny.demo.dao.BookDao;
import com.tuny.demo.domain.BookDomain;
import com.tuny.demo.domain.UserDomain;
import com.tuny.demo.util.BaseService;
import com.tuny.demo.util.IDUtil;
import com.tuny.demo.util.IdWorker;
import com.tuny.demo.util.PageUtil;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.util.StringUtil;
import com.tuny.demo.util.WebUtil;
import com.tuny.demo.vo.book.BookAddVo;
import com.tuny.demo.vo.book.BookFindVo;
import com.tuny.demo.vo.book.BookOutVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class BookService extends BaseService<BookDomain> {

    @Autowired
    private BookDao dao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    private RentService rentService;
    @Override
    public BookDao getDAO() {
        return dao;
    }

    public PageInfo<BookOutVo> bookList(BookFindVo param) {
        return PageUtil.doPage(dao::bookList, param, param.getPage(), param.getLimit());
    }


    //新增图书
    public ResultJson insertBook(BookAddVo param) {

        //进行参数的校验
        ResultJson resultJson = checkBook(param);
        if (resultJson.getCode().equals(1)) {
            return resultJson;
        }
        BookDomain domain = mapper.map(param, BookDomain.class);
        domain.setDateCreated(new Date());
        domain.setId(IDUtil.getId());
        if (this.getDAO().insert(domain) > 0) {
            return ResultJson.success("添加成功");
        }

        return ResultJson.success("添加失败");
    }

    //修改图书
    public ResultJson updateBook(BookAddVo param) {
        BookDomain domain = this.getDAO().findById(param.getId());
        if (null == domain) {
            return ResultJson.success("要编辑的图书无效");
        }

        ResultJson resultJson = checkBook(param);
        if (resultJson.getCode().equals(1)) {
            return resultJson;
        }

        if (this.getDAO().update(mapper.map(param, BookDomain.class)) > 0) {
            return ResultJson.success("修改成功");
        }
        return ResultJson.error("修改成功");
    }

    private ResultJson checkBook(BookAddVo param) {
        // 权限
        UserDomain user = (UserDomain) WebUtil.getSession().getAttribute("user");
        if (user == null || !user.getType().equals(1)) {
            return ResultJson.error("没有权限进行操作");
        }
        //进行参数的校验
        if (!StringUtils.isEmpty(param.getBookName())
                && StringUtil.length(param.getBookName()) > 128 * 2) {
            return ResultJson.error("图书名称超长，最多128个中文");
        }
        if (!StringUtils.isEmpty(param.getAuthor())) {
            if (StringUtil.length(param.getAuthor()) < 2 * 2) {
                return ResultJson.error("图书作者名称不能少于2个字");
            }

            if (StringUtil.length(param.getAuthor()) > 64 * 2) {
                return ResultJson.error("图书作者名称不能超过64个字");
            }

        }

        if (!StringUtils.isEmpty(param.getPublisher())
                && StringUtil.length(param.getPublisher()) > 128 * 2) {
            return ResultJson.error("图书名称超长，最多128个中文");
        }
        if (!StringUtil.isEmpty(param.getStock()) && param.getStock() < 0) {
            return ResultJson.error("图书库存必须大于等于0");
        }
        return ResultJson.success("");
    }

    @Transactional
    public ResultJson deleteBook(List<Long> list) {
        UserDomain user = (UserDomain) WebUtil.getSession().getAttribute("user");
        if (user == null || !user.getType().equals(1)) {
            return ResultJson.error("没有权限进行操作");
        }
        if (list.size() == 0){
            return ResultJson.error("请指定要删除的图书");
        }
        for(Long id : list){
            if(this.getDAO().delete(id)>0){
                //todo 删除借阅记录
                rentService.getDAO().deleteRent(id);
                return ResultJson.success("删除成功");
            }
        }
        return ResultJson.error("删除失败");
    }
}


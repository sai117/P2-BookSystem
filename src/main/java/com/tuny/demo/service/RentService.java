package com.tuny.demo.service;

import com.github.pagehelper.PageInfo;
import com.tuny.demo.dao.RentDao;
import com.tuny.demo.domain.BookDomain;
import com.tuny.demo.domain.RentDomain;
import com.tuny.demo.domain.UserDomain;
import com.tuny.demo.util.BaseService;
import com.tuny.demo.util.IDUtil;
import com.tuny.demo.util.PageUtil;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.util.WebUtil;
import com.tuny.demo.vo.rent.RentFindVo;
import com.tuny.demo.vo.rent.RentOutVo;
import com.tuny.demo.vo.rent.RentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class RentService extends BaseService<RentDomain> {

    @Autowired
    private RentDao dao;
    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Override
    public RentDao getDAO() {
        return dao;
    }

    @Transactional
    public ResultJson rentBook(RentVo param) throws ParseException {

        //查看用户
        UserDomain user = (UserDomain) WebUtil.getSession().getAttribute("user");
        if (user == null) {
            return ResultJson.error("你没登录啊");
        }

        BookDomain bookDomain = bookService.findByPrimaryKey(param.getBookId());
        if (null == bookDomain) {
            return ResultJson.error("选中的图书不存在");
        }
        if (bookDomain.getStock() <= 0) {
            return ResultJson.error("库存不足");
        }
        //相同的书每人只能借阅一本
        RentDomain rentDomain = new RentDomain();
        rentDomain.setBookId(param.getBookId());
        rentDomain.setRentUser(user.getId());
        if (this.getDAO().find(rentDomain).size() > 0) {
            return ResultJson.error("相同的书每人只能借阅一本");
        }
        rentDomain.setId(IDUtil.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        rentDomain.setBackTime(sdf.parse(param.getBackTime()));
        rentDomain.setRentTime(sdf.parse(param.getRentTime()));
        if (this.getDAO().insert(rentDomain) > 0) {

            //图书数量-1
            bookDomain.setStock(bookDomain.getStock() - 1);
            if (bookService.getDAO().update(bookDomain) > 0) {
                return ResultJson.success("租借成功");
            }

        }
        return ResultJson.error("租借失败");
    }

    public PageInfo<RentOutVo> rentList(RentFindVo param) {
        PageInfo<RentOutVo> pageInfo = PageUtil.doPage(dao::rentList, param, param.getPage(), param.getLimit());
        if (pageInfo.getList().size() == 0) {
            return pageInfo;
        }
        pageInfo.getList().stream().forEach(u -> {
            UserDomain user = userService.findByPrimaryKey(u.getUserId());
            if (null != user) {
                u.setUserName(user.getUsername());
            }
        });
        return pageInfo;
    }
}


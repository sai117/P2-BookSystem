package com.tuny.demo.service;

import com.tuny.demo.dao.UserDao;
import com.tuny.demo.domain.UserDomain;
import com.tuny.demo.util.BaseService;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.util.WebUtil;
import com.tuny.demo.vo.LoginVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService extends BaseService<UserDomain> {

    @Autowired
    private UserDao dao;

    @Autowired
    public DozerBeanMapper mapper;

    @Override
    public UserDao getDAO() {
        return dao;
    }

    //实现登录
    public ResultJson login(LoginVo param) {
        if (StringUtils.isEmpty(param.getUserName()) || StringUtils.isEmpty(param.getPassWord())) {
            return ResultJson.error("账号或密码不能为空");
        }
        UserDomain user = this.getDAO().login(param.getUserName());
        if (null != user) {
            if (user.getUsername().equals(param.getUserName()) &&
                    user.getPassword().equals(param.getPassWord())) {
                //把查出来的user放到session中
                WebUtil.getSession().setAttribute("user", user);
                return ResultJson.success("登录成功");
            }
        }
        return ResultJson.error("账号或密码错误");
    }

}


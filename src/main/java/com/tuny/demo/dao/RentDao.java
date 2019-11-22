package com.tuny.demo.dao;

import com.tuny.demo.domain.RentDomain;
import com.tuny.demo.util.BaseDao;
import com.tuny.demo.vo.rent.RentFindVo;
import com.tuny.demo.vo.rent.RentOutVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentDao extends BaseDao<RentDomain> {
     List<RentOutVo> rentList(RentFindVo param);

    void deleteRent(Long id);
}

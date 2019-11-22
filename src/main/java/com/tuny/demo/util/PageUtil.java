package com.tuny.demo.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class PageUtil {
    /**
     * 调用PageHelper对传入的有参方法做分页调用，并返回分页后的结果
     *
     * @param queryFun  要分页的方法
     * @param param     调用queryFun方法需要传入的参数
     * @param pageIndex 当前页码
     * @param pageSize  每页行数
     * @param <T>
     * @param <P>
     * @return
     */
    public static <T, P> PageInfo<T> doPage(Function<P, List<T>> queryFun, P param, int pageIndex, int pageSize) {
        if (pageIndex == 0) {
            pageIndex = 1;
        }

        if (pageSize > 100) {
            pageSize = 100;
        }

        PageHelper.startPage(pageIndex, pageSize);
        return new PageInfo<>(queryFun.apply(param));
    }

    /**
     * 调用PageHelper对传入的无参方法做分页调用，并返回分页后的结果
     *
     * @param queryFun  要分页的方法
     * @param pageIndex 当前页码
     * @param pageSize  每页行数
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> doPage(Supplier<List<T>> queryFun, int pageIndex, int pageSize) {
        if (pageIndex == 0) {
            pageIndex = 1;
        }

        if (pageSize > 100) {
            pageSize = 100;
        }

        PageHelper.startPage(pageIndex, pageSize);

        return new PageInfo<>(queryFun.get());
    }

    /**
     * 调用PageHelper对传入的有参方法做分页调用，并返回分页后的结果
     *
     * @param queryFun 要分页的方法
     * @param param    调用queryFun方法需要传入的参数
     * @param <T>
     * @param <P>
     * @return
     */
    public static <T, P extends PaginationVo> PageInfo<T> doPage(Function<P, List<T>> queryFun, P param) {
        if (param.getPageIndex() == 0) {
            param.setPageIndex(1);
        }

        if (param.getPageSize() > 100) {
            param.setPageSize(100);
        }

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        return new PageInfo<>(queryFun.apply(param));
    }

    /**
     * 调用PageHelper对传入的无参方法做分页调用，并返回分页后的结果
     *
     * @param queryFun  要分页的方法
     * @param pageParam 分页参数
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> doPage(Supplier<List<T>> queryFun, PaginationVo pageParam) {
        if (pageParam.getPageIndex() == 0) {
            pageParam.setPageIndex(1);
        }

        if (pageParam.getPageSize() > 100) {
            pageParam.setPageSize(100);
        }

        PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize());

        return new PageInfo<>(queryFun.get());
    }

    /**
     * 调用PageHelper对传入的无参方法做分页调用，并返回分页后的结果
     *
     * @param queryFun 要分页的方法
     * @param param    基于BaseDomain的分页参数
     * @param <T>
     * @return
     */
//    public static <T, P extends BaseDomain> PageInfo<T> doPage(Function<P, List<T>> queryFun, P param) {
//        if (param.getPagination().getPageIndex() == null || param.getPagination().getPageIndex() == 0) {
//            param.getPagination().setPageIndex(1);
//        }
//        if (param.getPagination().getPageSize() == null) {
//            param.getPagination().setPageSize(15);
//        } else if (param.getPagination().getPageSize() > 100) {
//            param.getPagination().setPageSize(100);
//        }
//        if (StringUtils.isEmpty(param.getPagination().getSortBy())) {
//            PageHelper.startPage(param.getPagination().getPageIndex()
//                    , param.getPagination().getPageSize());
//        } else {
//            PageHelper.startPage(param.getPagination().getPageIndex()
//                    , param.getPagination().getPageSize()
//                    , param.getPagination().getSortBy());
//        }
//        return new PageInfo<>(queryFun.apply(param));
//    }
}

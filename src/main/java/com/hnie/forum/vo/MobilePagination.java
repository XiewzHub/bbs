package com.hnie.forum.vo;

import com.hnie.forum.domain.MobileType;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
public class MobilePagination extends Pagination {

    private MobileType mobile = new MobileType();
    private List<MobileType> mobileTypeList;

    public MobilePagination() {
        super(1,9);
    }

    public MobilePagination(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
    }

    public MobileType getMobile() {
        return mobile;
    }

    public void setMobile(MobileType mobile) {
        this.mobile = mobile;
    }

    public List<MobileType> getMobileTypeList() {
        return mobileTypeList;
    }

    public void setMobileTypeList(List<MobileType> mobileTypeList) {
        this.mobileTypeList = mobileTypeList;
    }
}

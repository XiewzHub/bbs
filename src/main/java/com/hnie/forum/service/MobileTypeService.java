package com.hnie.forum.service;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.vo.MobilePagination;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface MobileTypeService {
    public List<MobileType> findAllMobileTypes();

    public List<MobileType> findMobileTypeBySelf(MobileType mobileType);

    public MobilePagination findMobileTypeByMobilePagination(MobilePagination mobilePagination);

    /**
     * 查找所有的手机品牌
     * @return
     */
    public List<String> findMobileBrandList();

    public List<String> findMobiSeriesByMobiBrand(String mobiBrand);

    public List<String> findMobiTypeByMobiSeries(String mobiSeries);

    public List<String> findMobiTypeByBrandAndSeries(String mobiBrand, String mobiSeries);

    public Integer addMobileType(MobileType mobileType);

    public Integer modifyMobileType(MobileType mobileType);

    public Integer deleteMobiTypeByMobiId(Integer mobiId);
}

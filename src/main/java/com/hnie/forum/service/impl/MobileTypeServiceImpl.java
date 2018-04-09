package com.hnie.forum.service.impl;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.mapper.MobileTypeMapper;
import com.hnie.forum.service.MobileTypeService;
import com.hnie.forum.vo.MobilePagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service("mobileTypeService")
@Transactional
public class MobileTypeServiceImpl implements MobileTypeService {

    @Resource(name = "mobileTypeMapper")
    private MobileTypeMapper mobileTypeMapper;

    @Override
    public List<MobileType> findAllMobileTypes() {
        return this.mobileTypeMapper.findAllMobileTypes();
    }

    @Override
    public List<MobileType> findMobileTypeBySelf(MobileType mobileType) {
        List<MobileType> mobileTypeList = new ArrayList<MobileType>();
        mobileTypeList = this.mobileTypeMapper.findMobileTypeByMobileType(mobileType);
        return mobileTypeList;
    }

    @Override
    public MobilePagination findMobileTypeByMobilePagination(MobilePagination mobilePagination) {
        Integer temp = mobilePagination.getPageNo();
        mobilePagination.setPageNo((temp - 1) * mobilePagination.getPageSize());

        List<MobileType> mobileTypeList = this.mobileTypeMapper.findMobileTypeByMobilePagination(mobilePagination);
        mobilePagination.setMobileTypeList(mobileTypeList);

        Integer count = this.mobileTypeMapper.findCountByMobilePagination(mobilePagination);
        mobilePagination.setTotal(count);
        mobilePagination.setPagesAllNoByCountAndPageSize(count, mobilePagination.getPageSize());

        mobilePagination.setPageNo(temp);
        return mobilePagination;
    }

    @Override
    public List<String> findMobileBrandList() {

        return this.mobileTypeMapper.findMobileBrandList();
//        return null;
    }

    @Override
    public List<String> findMobiSeriesByMobiBrand(String mobiBrand) {
        return this.mobileTypeMapper.findMobiSeriesByMobiBrand(mobiBrand);
    }

    @Override
    public List<String> findMobiTypeByMobiSeries(String mobiSeries) {
        return this.mobileTypeMapper.findMobiTypeByMobiSeries(mobiSeries);
    }

    @Override
    public List<String> findMobiTypeByBrandAndSeries(String mobiBrand, String mobiSeries){
        MobileType mobileType = new MobileType();
        mobileType.setMobiBrand(mobiBrand);
        mobileType.setMobiSeries(mobiSeries);
        List<MobileType> mobileTypeList = this.mobileTypeMapper.findMobileTypeByMobileType(mobileType);
        List list = new ArrayList();
        for (MobileType mobiletype : mobileTypeList) {
            list.add(mobiletype.getMobiType());
        }
        return list;
    }

    /**
     * 添加手机
     * @param mobileType
     * @return
     */
    @Override
    public Integer addMobileType(MobileType mobileType) {
        return this.mobileTypeMapper.addMobileType(mobileType);
    }

    /**
     * 更新手机信息
     * @param mobileType
     * @return
     */
    @Override
    public Integer modifyMobileType(MobileType mobileType) {
        return this.mobileTypeMapper.updateMobileType(mobileType);
    }

    @Override
    public Integer deleteMobiTypeByMobiId(Integer mobiId) {
        return this.mobileTypeMapper.deleteMobiTypeByMobiId(mobiId);
    }
}

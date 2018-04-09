package com.hnie.forum.mapper;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.vo.MobilePagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface MobileTypeMapper {
    public List<MobileType> findAllMobileTypes();

    public List<MobileType> findMobileTypeByMobileType(MobileType mobileType);

    public Integer updateMobileType(MobileType mobileType);

    public List<MobileType> findMobileTypeByMobilePagination(MobilePagination mobilePagination);

    public Integer findCountByMobilePagination(MobilePagination mobilePagination);

    public List<String> findMobileBrandList();

    /**
     * 下面两个方法都是可以初入空字符串可以查出来的
     * 但是不可以为空 null
     * @param mobiBrand
     * @return
     */
    public List<String> findMobiSeriesByMobiBrand(@Param("mobiBrand") String mobiBrand);

    /**
     * 此方法暂时没什么用，最好不要用
     * @param mobiSeries
     * @return
     */
    @Deprecated
    public List<String> findMobiTypeByMobiSeries(@Param("mobiSeries") String mobiSeries);

    /**
     * 好像出问题了
     * 使用事会抛出非法参数异常
     * @param param
     * @return
     */
    @Deprecated
    public List<MobileType> findMobiTypeByBrandAndSeries(Map param);

    public Integer addMobileType(MobileType mobileType);

    public Integer deleteMobiTypeByMobiId(@Param("mobiId") Integer mobiId);
}

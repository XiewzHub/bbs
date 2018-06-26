package com.hnie.forum.web.spring.controller;

import com.hnie.forum.domain.MobileType;
import com.hnie.forum.service.MobileTypeService;
import com.hnie.forum.utils.ControllerUtils;
import com.hnie.forum.vo.MobilePagination;
import org.apache.log4j.Logger;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/mobileType")
public class MobileTypeController {
    private final String EMPTY_SERIES_VALUE = "空系列";
    private final String EMPTY_TPYE_VALUE = "空型号";
    private Logger logger = Logger.getLogger(getClass());
    @Resource(name = "mobileTypeService")
    private MobileTypeService mobileTypeService;

    @RequestMapping(value = "/list")
    public String mobileTypeList(HttpSession session, Model model, String mobileBrand) {
        MobilePagination mobilePagination = new MobilePagination();
        return this.mobileTypeList(session, model, mobilePagination, mobileBrand);

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addMobileType(MobileType mobileType, HttpSession session) {
        String path = session.getServletContext().getContextPath();
        String img = mobileType.getMobiImg().substring(path.length());
        mobileType.setMobiImg(img);
        if (mobileType.getMobiId() == null) {
            this.mobileTypeService.addMobileType(mobileType);
        } else {
            Integer num = this.mobileTypeService.modifyMobileType(mobileType);
        }
        return "redirect:/user/mobileTypeManager";
    }

    @RequestMapping(value = "{mobiId}/ajaxDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map deleteMobileType(HttpSession session, Model model, @PathVariable Integer mobiId) {
        Map result = new HashMap();
        if (ControllerUtils.isNoUser(session)) {
            result.put("result", 2);
            return result;
        }

        Integer num = this.mobileTypeService.deleteMobiTypeByMobiId(mobiId);
        result.put("result", 1);
        return result;
    }

    @RequestMapping(value = "{pageNo}/{pageSize}/list")
    public String mobileTypeList(HttpSession session, Model model, MobilePagination mobilePagination, String mobileBrand) {

        mobilePagination.getMobile().setMobiBrand(mobileBrand);
        mobilePagination = this.mobileTypeService.findMobileTypeByMobilePagination(mobilePagination);
        //查找手机所有品牌
        List<String> mobileBrandList = this.mobileTypeService.findMobileBrandList();
//        List<MobileType> mobileTypeList = this.mobileTypeService.findAllMobileTypes();

        model.addAttribute("mobileBrandList", mobileBrandList);
        model.addAttribute("mobilePagination", mobilePagination);
        //当前手机
        model.addAttribute("currentMobileBrand", mobileBrand);
        return "mobile/mobileList";
    }

    /**
     * @param mobileType
     * @return
     */
    @RequestMapping(value = "/findMobileSeries")
    @ResponseBody
    public Map findMobileSeriesToLendon(MobileType mobileType) {
        Map result = new HashMap<String, String>();
//        List<MobileType> mobileTypes = this.mobileTypeService.findMobileTypeBySelf(mobileType);
        changeMobiTypeCode(mobileType);
        List<String> mobiSeriesList = this.mobileTypeService.findMobiSeriesByMobiBrand(mobileType.getMobiBrand());
        if (mobiSeriesList.size() == 0 || mobiSeriesList.isEmpty()) {
            result.put("result", "失败");
        } else {
            result.put("result", "成功");
            StringBuffer html = new StringBuffer("<select name=\"mobiSeries\" class=\"form-control\" style=\"padding: 0px;\">");
            html.append("<option value=\"\">---请选择系列----</option>");
            //查到多条数据就在前面加一个请选择
            for (int i = 0; i < mobiSeriesList.size(); i++) {
                String mobiSeries = mobiSeriesList.get(i);
                if (mobiSeries.equals("")) {
                    mobiSeries = EMPTY_SERIES_VALUE;
                }
                //系列号全部是不为空时可以生成系列号选择框
                html.append("<option value=\"" + mobiSeries + "\">---" + mobiSeries + "</option>");
            }
            html.append("</select>");
            result.put("html", html);
        }
        return result;
    }

    /**
     * 针对ajax传入的数据中文乱码转换
     *
     * @param mobileType
     */
    private void changeMobiTypeCode(MobileType mobileType) {
        String charseName = String.valueOf(StringHttpMessageConverter.DEFAULT_CHARSET);
        // charseName = "UTF-8";
        String mobiBrand = mobileType.getMobiBrand();
        String mobiSeries = mobileType.getMobiSeries();
        String mobiType = mobileType.getMobiType();
        logger.debug("未编码前的传入数据："+mobiBrand+"-----"+mobiSeries+"----"+mobiType+" 转换的编码格式为："+charseName);
        try {
            if (StringUtils.hasText(mobiBrand))
                mobiBrand = new String(mobiBrand.getBytes(charseName));
            if (StringUtils.hasText(mobiSeries))
                mobiSeries = new String(mobiSeries.getBytes(charseName));
            if (StringUtils.hasText(mobiType))
                mobiType = new String(mobiSeries.getBytes(charseName));
        } catch (UnsupportedEncodingException e) {
            logger.error("解析出错！");
            e.printStackTrace();
        }
        mobileType.setMobiBrand(mobiBrand);
        mobileType.setMobiSeries(mobiSeries);
        mobileType.setMobiSeries(mobiType);
        logger.debug("编码后的传入数据："+mobiBrand+"-----"+mobiSeries+"----"+mobiType);
        // return mobiBrand;
    }

    /**
     * 第二级菜单选择
     * 根据手机品牌和手机系列，寻找手机型号
     *
     * @param mobileType
     * @return
     */
    @RequestMapping(value = "/findMobileType")
    @ResponseBody
    public Map findMobileTypeToLendon(MobileType mobileType) {
        changeMobiTypeCode(mobileType);
        Map result = new HashMap();
        if (mobileType.getMobiSeries() != null && mobileType.getMobiSeries().equals(EMPTY_SERIES_VALUE)) {
            mobileType.setMobiSeries("");
        }
        String mobiBrand = mobileType.getMobiBrand();
        String mobiSeries = mobileType.getMobiSeries();
        List<String> mobiTypeList = this.mobileTypeService.findMobiTypeByBrandAndSeries(mobiBrand, mobiSeries);

        if (mobiTypeList.size() == 0) {
            result.put("result", "失败");
        } else {
            result.put("result", "成功");
            StringBuffer html = new StringBuffer("<select name=\"mobiType\" class=\"form-control\" style=\"padding: 0px;\">");
            html.append("<option value=\"\">---请选择型号----</option>");
            for (int i = 0; i < mobiTypeList.size(); i++) {
                String mobiType = mobiTypeList.get(i);
                html.append("<option value=\"" + mobiType + "\">---" + mobiType + "</option>");
            }
            html.append("</select>");
            result.put("html", html);
        }
        return result;
    }

    @RequestMapping(value = "/findMobileTypeId")
    @ResponseBody
    public Map findMobileTypeIdToLendon(MobileType mobileType, HttpServletRequest request) {
        Map result = new HashMap();
        List<MobileType> mobileTypeList = this.mobileTypeService.findMobileTypeBySelf(mobileType);

        if (mobileTypeList.size() == 0) {
            result.put("result", "失败");
        } else {
            result.put("result", "成功");
            Integer mobiId = mobileTypeList.get(0).getMobiId();
//            System.out.println(mobiId);
            result.put("mobiId", mobiId);
        }

        return result;
    }

}

package com.hnie.forum.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/10.
 */
public class MobileType implements Serializable {
    private Integer mobiId;
    private String mobiBrand;
    private String mobiSeries;
    private String mobiType;
    private String mobiImg;
    private String mobiDescription;

    public Integer getMobiId() {
        return mobiId;
    }

    public void setMobiId(Integer mobiId) {
        this.mobiId = mobiId;
    }

    public String getMobiBrand() {
        return mobiBrand;
    }

    public void setMobiBrand(String mobiBrand) {
        this.mobiBrand = mobiBrand;
    }

    public String getMobiSeries() {
        return mobiSeries;
    }

    public void setMobiSeries(String mobiSeries) {
        this.mobiSeries = mobiSeries;
    }

    public String getMobiType() {
        return mobiType;
    }

    public void setMobiType(String mobiType) {
        this.mobiType = mobiType;
    }

    public String getMobiImg() {
        return mobiImg;
    }

    public void setMobiImg(String mobiImg) {
        this.mobiImg = mobiImg;
    }

    public String getMobiDescription() {
        return mobiDescription;
    }

    public void setMobiDescription(String mobiDescription) {
        this.mobiDescription = mobiDescription;
    }

    @Override
    public String toString() {
        return "MobileType{" +
                "mobiId=" + mobiId +
                ", mobiBrand='" + mobiBrand + '\'' +
                ", mobiSeries='" + mobiSeries + '\'' +
                ", mobiType='" + mobiType + '\'' +
                ", mobiImg='" + mobiImg + '\'' +
                ", mobiDescription='" + mobiDescription + '\'' +
                '}';
    }
}

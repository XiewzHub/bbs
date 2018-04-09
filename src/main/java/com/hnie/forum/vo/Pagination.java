package com.hnie.forum.vo;

/**
 * Created by Administrator on 2017/5/12.
 */
public class Pagination {
    private Integer pageNo = 1;//当前页码
    private Integer pageSize = 5;//每页条数
    private Integer total;//总条数
    private Integer pagesAllNo;//总页面数

    public Pagination() {

    }
    public Pagination(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPagesAllNo() {
        return pagesAllNo;
    }

    public void setPagesAllNo(Integer pagesAllNo) {
        this.pagesAllNo = pagesAllNo;
    }

    /**
     * 根据总条数数和每页条数
     * 计算出总页数
     * @param total
     * @param pageSize
     */
    public void setPagesAllNoByCountAndPageSize(Integer total,Integer pageSize){
        this.setTotal(total);
        this.setPagesAllNo((int)Math.ceil((float)total/(float)pageSize));
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pagesAllNo=" + pagesAllNo +
                '}';
    }
}

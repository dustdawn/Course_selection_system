package cn.njit.entity.bean;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/11/19 14:05
 *
 * 分页查询类
 */
public class PageBean {
    /**
     * 当前页数
     */
    private Integer currentPage;
    /**
     * 查询总数
     */
    private Integer totalCount;
    /**
     * 每页显示数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    private List list;
    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.totalCount = totalCount;

        this.currentPage = currentPage;
        //默认当前页为1
        if(this.currentPage == null) {
            this.currentPage = 1;
        }

        this.pageSize = pageSize;
        //默认为每页显示3个
        if(this.pageSize == null) {
            this.pageSize = 3;
        }

        //计算总页数  (totalPage - 1)*pageSize = totalCount - 1
        this.totalPage = (this.totalCount-1+this.pageSize)/this.pageSize;

        if(this.currentPage < 1) {
            this.currentPage = 1;
        }

        if(this.currentPage>totalPage) {
            this.currentPage = totalPage;
        }

    }

    /**
     * 计算分页起始索引
     * @return
     */
    public int getStart() {
        return (this.currentPage-1)*this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}

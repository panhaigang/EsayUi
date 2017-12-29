package cn.et.food.utils;

import java.util.List;

public class PageTools {
    /**
     * 
     * 
     * @param curPage   页面传入的当前页
     * @param totalCount   数据库查询的总记录数
     * @param pageCount     每页显示的条数
     */
    public PageTools(Integer curPage,Integer totalCount,Integer pageCount){
        this.curPage=curPage;
        this.total=totalCount;
        this.pageCount=pageCount==null?this.pageCount:pageCount;
        //上一页
        this.prePage=(curPage==1?1:curPage-1);
        //总页数
        this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:(totalCount/this.pageCount+1);
        //下一页
        this.nextPage=(curPage==totalPage)?totalPage:(curPage+1);
        //数据库第几页每页显示的数据   开始索引和结束索引
        this.startIndex=(curPage-1)*this.pageCount+1;
        this.endIndex=curPage*this.pageCount;
    }
    
    
    
    /**
     * 当前页(动态  由页面传递)
     */
    private Integer curPage;
    /**
     * 上一页
     * prePage=(curPage==1?1:curPage-1)
     * 
     */
    private Integer prePage;
    /**
     * 下一页
     * nextPage=(nextPage==totalPage)?totalPage:(curPage+1)
     * 
     * 
     */
    private Integer nextPage;
    //每页显示的条数
    private Integer pageCount=10;
    /**
     * 总页数
     * totalPage=(totalCount%pageCount==0?totalCount/pageCount:(totalCount/pageCount+1)
     * 
     */
    private Integer totalPage;
    //数据库的总记录数(数据库查询)
    private Integer total;
    //每页的数据放入该集合中
    private List rows;
    
    /**
     * 数据库开始索引和结束索引
     * 
     * startIndex=(curPage-1)*PageCount+1
     * endIndex=curPage*pageCount
     * 
     */
    private int startIndex;
    private int endIndex;
    
    public int getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public int getEndIndex() {
        return endIndex;
    }
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
    public Integer getCurPage() {
        return curPage;
    }
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }
    public Integer getPrePage() {
        return prePage;
    }
    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }
    public Integer getNextPage() {
        return nextPage;
    }
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotalt(Integer total) {
        this.total = total;
    }
    
    
    public static void main(String[] args) {
        int curPage=6;
        int totalCount=27;
        int pageCount=5;
        
        PageTools pt=new PageTools(curPage, totalCount, pageCount);
        System.out.println(pt.getNextPage());
        System.out.println(pt.getPrePage());
        System.out.println(pt.getTotalPage());
        System.out.println(pt.getStartIndex());
        System.out.println(pt.getEndIndex());
    }
}
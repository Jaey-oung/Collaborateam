package com.collaborateam.www.domain;

public class Pagination {
    private SearchCondition sc;
    private int totalCnt;
    private int totalPage;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public Pagination(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        paging(totalCnt, sc);
    }

    public void paging(int totalCnt, SearchCondition sc) {
        final int NAVI_SIZE = 10;

        this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize()==0 ? 0 : 1);
        this.sc.setPage(Math.min(sc.getPage(), totalPage));
        this.beginPage = (this.sc.getPage() - 1) / NAVI_SIZE * NAVI_SIZE + 1;
        this.endPage = Math.min(beginPage + NAVI_SIZE - 1, totalPage);
        this.showPrev = beginPage != 1;
        this.showNext = endPage != totalPage;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "< " : "");
        for(int i=beginPage; i<=endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? ">" : "");
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
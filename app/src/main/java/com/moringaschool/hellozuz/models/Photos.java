
package com.moringaschool.hellozuz.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Photos {

    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("pages")
    @Expose
    private Long pages;
    @SerializedName("perpage")
    @Expose
    private Long perpage;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("photo")
    @Expose
    private List<Photo> photo = null;
    @SerializedName("max_allowed_results")
    @Expose
    private Long maxAllowedResults;
    @SerializedName("max_allowed_pages")
    @Expose
    private Long maxAllowedPages;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Photos() {
    }

    /**
     * 
     * @param perpage
     * @param total
     * @param pages
     * @param maxAllowedResults
     * @param photo
     * @param maxAllowedPages
     * @param page
     */
    public Photos(Long page, Long pages, Long perpage, String total, List<Photo> photo, Long maxAllowedResults, Long maxAllowedPages) {
        super();
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
        this.maxAllowedResults = maxAllowedResults;
        this.maxAllowedPages = maxAllowedPages;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getPerpage() {
        return perpage;
    }

    public void setPerpage(Long perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public Long getMaxAllowedResults() {
        return maxAllowedResults;
    }

    public void setMaxAllowedResults(Long maxAllowedResults) {
        this.maxAllowedResults = maxAllowedResults;
    }

    public Long getMaxAllowedPages() {
        return maxAllowedPages;
    }

    public void setMaxAllowedPages(Long maxAllowedPages) {
        this.maxAllowedPages = maxAllowedPages;
    }

}

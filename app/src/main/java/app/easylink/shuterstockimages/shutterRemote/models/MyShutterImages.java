package app.easylink.shuterstockimages.shutterRemote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyShutterImages {
    @SerializedName("page") private Integer page;
    @SerializedName("per_page") private Integer perPage;
    @SerializedName("total_count") private Integer totalCount;
    @SerializedName("search_id") private String searchId;
    @SerializedName("data") private List<MyShutterImage> data;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public List<MyShutterImage> getData() {
        return data;
    }

    public void setData(List<MyShutterImage> data) {
        this.data = data;
    }
}

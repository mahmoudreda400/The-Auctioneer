package edu.mum.cs.auctioneer.models;

import java.util.List;

public class AdminStatistics {

    private List<?> postsPerMonth;
    private List<?> postsPerCategory;
    private List<?> postsPerUser;

    //-----------------------setters and getters----------------

    public List<?> getPostsPerMonth() {
        return postsPerMonth;
    }

    public void setPostsPerMonth(List<?> postsPerMonth) {
        this.postsPerMonth = postsPerMonth;
    }

    public List<?> getPostsPerCategory() {
        return postsPerCategory;
    }

    public void setPostsPerCategory(List<?> postsPerCategory) {
        this.postsPerCategory = postsPerCategory;
    }

    public List<?> getPostsPerUser() {
        return postsPerUser;
    }

    public void setPostsPerUser(List<?> postsPerUser) {
        this.postsPerUser = postsPerUser;
    }
}

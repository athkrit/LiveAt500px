package com.example.liveat500px.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class PhotoItemDao {
    @SerializedName("id")               private Integer id ;
    @SerializedName("link")             private String link ;
    @SerializedName("image_url")        private String imageUrl ;
    @SerializedName("caption")          private String caption ;
    @SerializedName("user_id")          private Integer userId ;
    @SerializedName("username")         private String userName ;
    @SerializedName("profile_picture")  private String profilePocture ;
    @SerializedName("tags")             private List<String> tag ;
    @SerializedName("created_item")     private Date createdTime ;
    @SerializedName("camera")           private String camera ;
    @SerializedName("lens")             private String lens ;
    @SerializedName("focus_length")     private String focusLength ;
    @SerializedName("iso")              private String iso ;
    @SerializedName("shutter_speed")    private String shutterSpeed ;
    @SerializedName("aperture")         private String aperture ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePocture() {
        return profilePocture;
    }

    public void setProfilePocture(String profilePocture) {
        this.profilePocture = profilePocture;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getFocusLength() {
        return focusLength;
    }

    public void setFocusLength(String focusLength) {
        this.focusLength = focusLength;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }
}

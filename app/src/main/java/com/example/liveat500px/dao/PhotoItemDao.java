package com.example.liveat500px.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class PhotoItemDao implements Parcelable {
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

    public PhotoItemDao(){

    }

    protected PhotoItemDao(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        link = in.readString();
        imageUrl = in.readString();
        caption = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        userName = in.readString();
        profilePocture = in.readString();
        tag = in.createStringArrayList();
        camera = in.readString();
        lens = in.readString();
        focusLength = in.readString();
        iso = in.readString();
        shutterSpeed = in.readString();
        aperture = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(link);
        dest.writeString(imageUrl);
        dest.writeString(caption);
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(userName);
        dest.writeString(profilePocture);
        dest.writeStringList(tag);
        dest.writeString(camera);
        dest.writeString(lens);
        dest.writeString(focusLength);
        dest.writeString(iso);
        dest.writeString(shutterSpeed);
        dest.writeString(aperture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoItemDao> CREATOR = new Creator<PhotoItemDao>() {
        @Override
        public PhotoItemDao createFromParcel(Parcel in) {
            return new PhotoItemDao(in);
        }

        @Override
        public PhotoItemDao[] newArray(int size) {
            return new PhotoItemDao[size];
        }
    };

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

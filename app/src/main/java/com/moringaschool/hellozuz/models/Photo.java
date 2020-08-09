
package com.moringaschool.hellozuz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("farm")
    @Expose
    private Long farm;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ispublic")
    @Expose
    private Long ispublic;
    @SerializedName("isfriend")
    @Expose
    private Long isfriend;
    @SerializedName("isfamily")
    @Expose
    private Long isfamily;
    @SerializedName("safe")
    @Expose
    private Long safe;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Photo() {
    }

    /**
     * 
     * @param owner
     * @param server
     * @param ispublic
     * @param isfriend
     * @param safe
     * @param farm
     * @param id
     * @param secret
     * @param title
     * @param isfamily
     */
    public Photo(String id, String owner, String secret, String server, Long farm, String title, Long ispublic, Long isfriend, Long isfamily, Long safe) {
        super();
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.safe = safe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Long getFarm() {
        return farm;
    }

    public void setFarm(Long farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIspublic() {
        return ispublic;
    }

    public void setIspublic(Long ispublic) {
        this.ispublic = ispublic;
    }

    public Long getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Long isfriend) {
        this.isfriend = isfriend;
    }

    public Long getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Long isfamily) {
        this.isfamily = isfamily;
    }

    public Long getSafe() {
        return safe;
    }

    public void setSafe(Long safe) {
        this.safe = safe;
    }

}

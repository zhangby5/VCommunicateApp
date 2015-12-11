package com.vc.app.model;

public class UserRelationVO {
    private String cuserid;

    private String cfollowid;

    private Integer ftype;

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid == null ? null : cuserid.trim();
    }

    public String getCfollowid() {
        return cfollowid;
    }

    public void setCfollowid(String cfollowid) {
        this.cfollowid = cfollowid == null ? null : cfollowid.trim();
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }
}
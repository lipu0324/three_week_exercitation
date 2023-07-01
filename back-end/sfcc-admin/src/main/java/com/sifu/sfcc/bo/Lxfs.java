package com.sifu.sfcc.bo;

public class Lxfs {
    //责任单位联系人
    private String xm_zrdwlxr;
    //责任单位名称
    private String xm_zrdwmc;
    //项目编码
    private String xm_xmbm;
    //责任单位邮箱
    private String xm_xmzrdwyx;
    //项目名称
    private String xm_xmmc;
    //项目责任单位联系人电话
    private String  xm_xmzrdwlxdh;


    public String getXm_zrdwlxr() {
        return xm_zrdwlxr;
    }

    public void setXm_zrdwlxr(String xm_zrdwlxr) {
        this.xm_zrdwlxr = xm_zrdwlxr;
    }

    public String getXm_zrdwmc() {
        return xm_zrdwmc;
    }

    public void setXm_zrdwmc(String xm_zrdwmc) {
        this.xm_zrdwmc = xm_zrdwmc;
    }

    public String getXm_xmbm() {
        return xm_xmbm;
    }

    public void setXm_xmbm(String xm_xmbm) {
        this.xm_xmbm = xm_xmbm;
    }

    public String getXm_xmzrdwyx() {
        return xm_xmzrdwyx;
    }

    public void setXm_xmzrdwyx(String xm_xmzrdwyx) {
        this.xm_xmzrdwyx = xm_xmzrdwyx;
    }

    public String getXm_xmmc() {
        return xm_xmmc;
    }

    public void setXm_xmmc(String xm_xmmc) {
        this.xm_xmmc = xm_xmmc;
    }

    public String getXm_xmzrdwlxdh() {
        return xm_xmzrdwlxdh;
    }

    public void setXm_xmzrdwlxdh(String xm_xmzrdwlxdh) {
        this.xm_xmzrdwlxdh = xm_xmzrdwlxdh;
    }

    @Override
    public String toString() {
        return "Lxfs{" +
                "xm_zrdwlxr='" + xm_zrdwlxr + '\'' +
                ", xm_zrdwmc='" + xm_zrdwmc + '\'' +
                ", xm_xmbm='" + xm_xmbm + '\'' +
                ", xm_xmzrdwyx='" + xm_xmzrdwyx + '\'' +
                ", xm_xmmc='" + xm_xmmc + '\'' +
                ", xm_xmzrdwlxdh='" + xm_xmzrdwlxdh + '\'' +
                '}';
    }
}

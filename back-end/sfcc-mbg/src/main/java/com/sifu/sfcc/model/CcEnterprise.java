package com.sifu.sfcc.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CcEnterprise implements Serializable {
    private Long id;

    @ApiModelProperty(value = "网站logo")
    private String websiteLogo;

    @ApiModelProperty(value = "企业logo")
    private String enterpriseLogo;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业标题")
    private String enterpriseTitle;

    @ApiModelProperty(value = "版权")
    private String copyright;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsiteLogo() {
        return websiteLogo;
    }

    public void setWebsiteLogo(String websiteLogo) {
        this.websiteLogo = websiteLogo;
    }

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseTitle() {
        return enterpriseTitle;
    }

    public void setEnterpriseTitle(String enterpriseTitle) {
        this.enterpriseTitle = enterpriseTitle;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", websiteLogo=").append(websiteLogo);
        sb.append(", enterpriseLogo=").append(enterpriseLogo);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", enterpriseTitle=").append(enterpriseTitle);
        sb.append(", copyright=").append(copyright);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.sifu.sfcc.search.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
@Document(indexName = "xmk_project", type = "xmkProject", shards = 1, replicas = 0)
public class XmkProject implements Serializable {

    @Id
    private Long id;

    @ApiModelProperty(value = "项目编码")
    private String sXmId;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @ApiModelProperty(value = "项目名称")
    private String sXmName;

    @ApiModelProperty(value = "所属市")
    private String sXmArea;

    @ApiModelProperty(value = "所属区县")
    private String sXmOrgan;

    @ApiModelProperty(value = "项目所属地")
    private String sXmOrganName;

    @ApiModelProperty(value = "所属行业")
    private String sXmHydm;

    @ApiModelProperty(value = "所属产业")
    private String sXmCy;

    @ApiModelProperty(value = "项目主要板块")
    private String sXmZybk;

    @ApiModelProperty(value = "投资方式")
    private String sXmTzfs;

    @ApiModelProperty(value = "项目投资金额（万元）")
    private String sXmTzje;

    @ApiModelProperty(value = "项目销售收入（万元）")
    private String sXmXssr;

    @ApiModelProperty(value = "项目标识")
    private String sXmFlag;

    @ApiModelProperty(value = "预期投资回收期（年）")
    private String sXmYqhsq;

    @ApiModelProperty(value = "利润（万元）")
    private String sXmLr;

    @ApiModelProperty(value = "税收（万元）")
    private String sXmSs;

    @ApiModelProperty(value = "项目进展情况及所处阶段")
    private String sXmScjd;

    @ApiModelProperty(value = "是否有可研报告")
    private String sXmKybg;

    @ApiModelProperty(value = "拟重点招商区域国内")
    private String sXmNzdgn;

    @ApiModelProperty(value = "拟重点招商区域国外")
    private String sXmNzdgw;

    @ApiModelProperty(value = "责任单位名称")
    private String sXmZrdwName;

    @ApiModelProperty(value = "责任单位联系人")
    private String sXmZrdwLxr;

    @ApiModelProperty(value = "项目责任单位联系电话")
    private String sXmZrdwLxdh;

    @ApiModelProperty(value = "项目责任单位邮箱")
    private String sXmZrdwEmail;

    @ApiModelProperty(value = "填报单位名称")
    private String sXmTbdwName;

    @ApiModelProperty(value = "填报单位联系人")
    private String sXmTbdwLxr;

    @ApiModelProperty(value = "填报单位联系电话")
    private String sXmTbdwLxdh;

    @ApiModelProperty(value = "填报单位邮箱")
    private String sXmTbdwEmail;

    @ApiModelProperty(value = "就业人数（人）")
    private String sXmJyrs;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @ApiModelProperty(value = "项目概况")
    private String sXmInfo;

    @ApiModelProperty(value = "重点产业")
    private String sXmZdcy;

    @ApiModelProperty(value = "重点产业链")
    private String sXmZdcylName;

    @ApiModelProperty(value = "园区")
    private String sXmYq;

    @ApiModelProperty(value = "浏览量")
    private String sXmLll;

    private Date sPublishTime;

    @ApiModelProperty(value = "项目附件超链接地址，不存在附件字段为空")
    private String sFileUrl;
    private static final long serialVersionUID = 1L;

    public String getsXmId() {
        return sXmId;
    }

    public void setsXmId(String sXmId) {
        this.sXmId = sXmId;
    }

    public String getsXmName() {
        return sXmName;
    }

    public void setsXmName(String sXmName) {
        this.sXmName = sXmName;
    }

    public String getsXmArea() {
        return sXmArea;
    }

    public void setsXmArea(String sXmArea) {
        this.sXmArea = sXmArea;
    }

    public String getsXmOrgan() {
        return sXmOrgan;
    }

    public void setsXmOrgan(String sXmOrgan) {
        this.sXmOrgan = sXmOrgan;
    }

    public String getsXmOrganName() {
        return sXmOrganName;
    }

    public void setsXmOrganName(String sXmOrganName) {
        this.sXmOrganName = sXmOrganName;
    }

    public String getsXmHydm() {
        return sXmHydm;
    }

    public void setsXmHydm(String sXmHydm) {
        this.sXmHydm = sXmHydm;
    }

    public String getsXmCy() {
        return sXmCy;
    }

    public void setsXmCy(String sXmCy) {
        this.sXmCy = sXmCy;
    }

    public String getsXmZybk() {
        return sXmZybk;
    }

    public void setsXmZybk(String sXmZybk) {
        this.sXmZybk = sXmZybk;
    }

    public String getsXmTzfs() {
        return sXmTzfs;
    }

    public void setsXmTzfs(String sXmTzfs) {
        this.sXmTzfs = sXmTzfs;
    }

    public String getsXmTzje() {
        return sXmTzje;
    }

    public void setsXmTzje(String sXmTzje) {
        this.sXmTzje = sXmTzje;
    }

    public String getsXmXssr() {
        return sXmXssr;
    }

    public void setsXmXssr(String sXmXssr) {
        this.sXmXssr = sXmXssr;
    }

    public String getsXmFlag() {
        return sXmFlag;
    }

    public void setsXmFlag(String sXmFlag) {
        this.sXmFlag = sXmFlag;
    }

    public String getsXmYqhsq() {
        return sXmYqhsq;
    }

    public void setsXmYqhsq(String sXmYqhsq) {
        this.sXmYqhsq = sXmYqhsq;
    }

    public String getsXmLr() {
        return sXmLr;
    }

    public void setsXmLr(String sXmLr) {
        this.sXmLr = sXmLr;
    }

    public String getsXmSs() {
        return sXmSs;
    }

    public void setsXmSs(String sXmSs) {
        this.sXmSs = sXmSs;
    }

    public String getsXmScjd() {
        return sXmScjd;
    }

    public void setsXmScjd(String sXmScjd) {
        this.sXmScjd = sXmScjd;
    }

    public String getsXmKybg() {
        return sXmKybg;
    }

    public void setsXmKybg(String sXmKybg) {
        this.sXmKybg = sXmKybg;
    }

    public String getsXmNzdgn() {
        return sXmNzdgn;
    }

    public void setsXmNzdgn(String sXmNzdgn) {
        this.sXmNzdgn = sXmNzdgn;
    }

    public String getsXmNzdgw() {
        return sXmNzdgw;
    }

    public void setsXmNzdgw(String sXmNzdgw) {
        this.sXmNzdgw = sXmNzdgw;
    }

    public String getsXmZrdwName() {
        return sXmZrdwName;
    }

    public void setsXmZrdwName(String sXmZrdwName) {
        this.sXmZrdwName = sXmZrdwName;
    }

    public String getsXmZrdwLxr() {
        return sXmZrdwLxr;
    }

    public void setsXmZrdwLxr(String sXmZrdwLxr) {
        this.sXmZrdwLxr = sXmZrdwLxr;
    }

    public String getsXmZrdwLxdh() {
        return sXmZrdwLxdh;
    }

    public void setsXmZrdwLxdh(String sXmZrdwLxdh) {
        this.sXmZrdwLxdh = sXmZrdwLxdh;
    }

    public String getsXmZrdwEmail() {
        return sXmZrdwEmail;
    }

    public void setsXmZrdwEmail(String sXmZrdwEmail) {
        this.sXmZrdwEmail = sXmZrdwEmail;
    }

    public String getsXmTbdwName() {
        return sXmTbdwName;
    }

    public void setsXmTbdwName(String sXmTbdwName) {
        this.sXmTbdwName = sXmTbdwName;
    }

    public String getsXmTbdwLxr() {
        return sXmTbdwLxr;
    }

    public void setsXmTbdwLxr(String sXmTbdwLxr) {
        this.sXmTbdwLxr = sXmTbdwLxr;
    }

    public String getsXmTbdwLxdh() {
        return sXmTbdwLxdh;
    }

    public void setsXmTbdwLxdh(String sXmTbdwLxdh) {
        this.sXmTbdwLxdh = sXmTbdwLxdh;
    }

    public String getsXmTbdwEmail() {
        return sXmTbdwEmail;
    }

    public void setsXmTbdwEmail(String sXmTbdwEmail) {
        this.sXmTbdwEmail = sXmTbdwEmail;
    }

    public String getsXmJyrs() {
        return sXmJyrs;
    }

    public void setsXmJyrs(String sXmJyrs) {
        this.sXmJyrs = sXmJyrs;
    }

    public String getsXmInfo() {
        return sXmInfo;
    }

    public void setsXmInfo(String sXmInfo) {
        this.sXmInfo = sXmInfo;
    }

    public String getsXmZdcy() {
        return sXmZdcy;
    }

    public void setsXmZdcy(String sXmZdcy) {
        this.sXmZdcy = sXmZdcy;
    }

    public String getsXmZdcylName() {
        return sXmZdcylName;
    }

    public void setsXmZdcylName(String sXmZdcylName) {
        this.sXmZdcylName = sXmZdcylName;
    }

    public String getsXmYq() {
        return sXmYq;
    }

    public void setsXmYq(String sXmYq) {
        this.sXmYq = sXmYq;
    }

    public String getsXmLll() {
        return sXmLll;
    }

    public void setsXmLll(String sXmLll) {
        this.sXmLll = sXmLll;
    }

    public Date getsPublishTime() {
        return sPublishTime;
    }

    public void setsPublishTime(Date sPublishTime) {
        this.sPublishTime = sPublishTime;
    }

    public String getsFileUrl() {
        return sFileUrl;
    }

    public void setsFileUrl(String sFileUrl) {
        this.sFileUrl = sFileUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sXmId=").append(sXmId);
        sb.append(", sXmName=").append(sXmName);
        sb.append(", sXmArea=").append(sXmArea);
        sb.append(", sXmOrgan=").append(sXmOrgan);
        sb.append(", sXmOrganName=").append(sXmOrganName);
        sb.append(", sXmHydm=").append(sXmHydm);
        sb.append(", sXmCy=").append(sXmCy);
        sb.append(", sXmZybk=").append(sXmZybk);
        sb.append(", sXmTzfs=").append(sXmTzfs);
        sb.append(", sXmTzje=").append(sXmTzje);
        sb.append(", sXmXssr=").append(sXmXssr);
        sb.append(", sXmFlag=").append(sXmFlag);
        sb.append(", sXmYqhsq=").append(sXmYqhsq);
        sb.append(", sXmLr=").append(sXmLr);
        sb.append(", sXmSs=").append(sXmSs);
        sb.append(", sXmScjd=").append(sXmScjd);
        sb.append(", sXmKybg=").append(sXmKybg);
        sb.append(", sXmNzdgn=").append(sXmNzdgn);
        sb.append(", sXmNzdgw=").append(sXmNzdgw);
        sb.append(", sXmZrdwName=").append(sXmZrdwName);
        sb.append(", sXmZrdwLxr=").append(sXmZrdwLxr);
        sb.append(", sXmZrdwLxdh=").append(sXmZrdwLxdh);
        sb.append(", sXmZrdwEmail=").append(sXmZrdwEmail);
        sb.append(", sXmTbdwName=").append(sXmTbdwName);
        sb.append(", sXmTbdwLxr=").append(sXmTbdwLxr);
        sb.append(", sXmTbdwLxdh=").append(sXmTbdwLxdh);
        sb.append(", sXmTbdwEmail=").append(sXmTbdwEmail);
        sb.append(", sXmJyrs=").append(sXmJyrs);
        sb.append(", sXmInfo=").append(sXmInfo);
        sb.append(", sXmZdcy=").append(sXmZdcy);
        sb.append(", sXmZdcylName=").append(sXmZdcylName);
        sb.append(", sXmYq=").append(sXmYq);
        sb.append(", sXmLll=").append(sXmLll);
        sb.append(", sPublishTime=").append(sPublishTime);
        sb.append(", sFileUrl=").append(sFileUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
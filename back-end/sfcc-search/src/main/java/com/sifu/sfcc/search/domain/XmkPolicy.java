package com.sifu.sfcc.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "xmk_policy", type = "xmkPolicy", shards = 1, replicas = 0)
public class XmkPolicy implements Serializable {
    private String sYszy;

    private String sInfoPath;

    private String sUpdateUser;

    private String sCreateUserName;

    private String sOrgan;

    private Date sCreateDate;

    private Date sUpdateDate;

    private String sBmType;

    private String sFileName;

    private String sCreateUser;

    private Date sSpDate;

    private String sXqkfq;

    private String sInfo;

    private String sBmTypeName;

    private String sFilePath;

    private String sType;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String sTitle;

    private String sUuid;

    @Id
    private Long id;

    private static final long serialVersionUID = 1L;

    public String getsYszy() {
        return sYszy;
    }

    public void setsYszy(String sYszy) {
        this.sYszy = sYszy;
    }

    public String getsInfoPath() {
        return sInfoPath;
    }

    public void setsInfoPath(String sInfoPath) {
        this.sInfoPath = sInfoPath;
    }

    public String getsUpdateUser() {
        return sUpdateUser;
    }

    public void setsUpdateUser(String sUpdateUser) {
        this.sUpdateUser = sUpdateUser;
    }

    public String getsCreateUserName() {
        return sCreateUserName;
    }

    public void setsCreateUserName(String sCreateUserName) {
        this.sCreateUserName = sCreateUserName;
    }

    public String getsOrgan() {
        return sOrgan;
    }

    public void setsOrgan(String sOrgan) {
        this.sOrgan = sOrgan;
    }

    public Date getsCreateDate() {
        return sCreateDate;
    }

    public void setsCreateDate(Date sCreateDate) {
        this.sCreateDate = sCreateDate;
    }

    public Date getsUpdateDate() {
        return sUpdateDate;
    }

    public void setsUpdateDate(Date sUpdateDate) {
        this.sUpdateDate = sUpdateDate;
    }

    public String getsBmType() {
        return sBmType;
    }

    public void setsBmType(String sBmType) {
        this.sBmType = sBmType;
    }

    public String getsFileName() {
        return sFileName;
    }

    public void setsFileName(String sFileName) {
        this.sFileName = sFileName;
    }

    public String getsCreateUser() {
        return sCreateUser;
    }

    public void setsCreateUser(String sCreateUser) {
        this.sCreateUser = sCreateUser;
    }

    public Date getsSpDate() {
        return sSpDate;
    }

    public void setsSpDate(Date sSpDate) {
        this.sSpDate = sSpDate;
    }

    public String getsXqkfq() {
        return sXqkfq;
    }

    public void setsXqkfq(String sXqkfq) {
        this.sXqkfq = sXqkfq;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    public String getsBmTypeName() {
        return sBmTypeName;
    }

    public void setsBmTypeName(String sBmTypeName) {
        this.sBmTypeName = sBmTypeName;
    }

    public String getsFilePath() {
        return sFilePath;
    }

    public void setsFilePath(String sFilePath) {
        this.sFilePath = sFilePath;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsUuid() {
        return sUuid;
    }

    public void setsUuid(String sUuid) {
        this.sUuid = sUuid;
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
        sb.append(", sYszy=").append(sYszy);
        sb.append(", sInfoPath=").append(sInfoPath);
        sb.append(", sUpdateUser=").append(sUpdateUser);
        sb.append(", sCreateUserName=").append(sCreateUserName);
        sb.append(", sOrgan=").append(sOrgan);
        sb.append(", sCreateDate=").append(sCreateDate);
        sb.append(", sUpdateDate=").append(sUpdateDate);
        sb.append(", sBmType=").append(sBmType);
        sb.append(", sFileName=").append(sFileName);
        sb.append(", sCreateUser=").append(sCreateUser);
        sb.append(", sSpDate=").append(sSpDate);
        sb.append(", sXqkfq=").append(sXqkfq);
        sb.append(", sInfo=").append(sInfo);
        sb.append(", sBmTypeName=").append(sBmTypeName);
        sb.append(", sFilePath=").append(sFilePath);
        sb.append(", sType=").append(sType);
        sb.append(", sTitle=").append(sTitle);
        sb.append(", sUuid=").append(sUuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
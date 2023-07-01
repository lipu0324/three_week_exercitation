package com.sifu.sfcc.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CcNaireQuestion implements Serializable {
    private Long id;

    private Long naireId;

    @ApiModelProperty(value = "题目")
    private String title;

    @ApiModelProperty(value = "问题类型(0:是否，1:文本)")
    private Integer type;

    @ApiModelProperty(value = "是否第一个问题0:不是，1:是")
    private Integer isFirst;

    @ApiModelProperty(value = "是的下个id")
    private Long isTrueId;

    @ApiModelProperty(value = "否的下个id")
    private Long isFalseId;

    @ApiModelProperty(value = "文本类下一个")
    private Long nextId;

    @ApiModelProperty(value = "状态(0:新创建,1:配置完成)")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public Long getIsTrueId() {
        return isTrueId;
    }

    public void setIsTrueId(Long isTrueId) {
        this.isTrueId = isTrueId;
    }

    public Long getIsFalseId() {
        return isFalseId;
    }

    public void setIsFalseId(Long isFalseId) {
        this.isFalseId = isFalseId;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", naireId=").append(naireId);
        sb.append(", title=").append(title);
        sb.append(", type=").append(type);
        sb.append(", isFirst=").append(isFirst);
        sb.append(", isTrueId=").append(isTrueId);
        sb.append(", isFalseId=").append(isFalseId);
        sb.append(", nextId=").append(nextId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
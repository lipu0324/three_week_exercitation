package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 首页参数
 * Created by commerce on 2021/12/08.
 */
@Getter
@Setter
@ToString
public class IndexParam {
    @ApiModelProperty(value = "工单总量")
    private long workOrderCount;
    @ApiModelProperty(value = "档案总量")
    private long archivesCount;
    @ApiModelProperty(value = "固定标签筛选出来的档案总量")
    private long totalCount;
    @ApiModelProperty(value = "用户总量")
    private long memberCount;
    @ApiModelProperty(value = "部门数")
    private long deptCount;
    @ApiModelProperty(value = "知识库总量")
    private long knowledgeCount;
    @ApiModelProperty(value = "在线AI数量")
    private long aiCount;
    @ApiModelProperty(value = "评价数量")
    private long scoreCount;
}

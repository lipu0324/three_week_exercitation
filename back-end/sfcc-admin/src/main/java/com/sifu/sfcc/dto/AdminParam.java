package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 用户登录参数
 * Created by commerce on 2018/4/26.
 */
@Getter
@Setter
@ToString
public class AdminParam {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "坐席")
    private String code;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
}

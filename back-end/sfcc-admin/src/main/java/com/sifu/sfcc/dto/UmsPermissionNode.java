package com.sifu.sfcc.dto;

import com.sifu.sfcc.model.UmsPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台权限节点封装
 * Created by sifu on 2018/9/30.
 */
public class UmsPermissionNode extends UmsPermission {
    @Getter
    @Setter
    private List<UmsPermissionNode> children;

    public void setChildren(List<UmsPermissionNode> children) {
    }
}

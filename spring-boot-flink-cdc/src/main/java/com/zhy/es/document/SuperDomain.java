package com.zhy.es.document;

import java.io.Serializable;

public class SuperDomain implements Serializable {
    private static final long serialVersionUID = 3410704771684031052L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 数据创建时间
     */
    private Long createAt;
    /**
     * 数据更新时间
     */
    private Long updateAt;
    /**
     * 数据创建者
     */
    private String createBy;
    /**
     * 数据更新者
     */
    private String updateBy;

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}

package com.xhg.ssm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * standard
 *
 * @author
 */
public class Standard implements Serializable {
    /**
     * 使用序列赋值
     */
    private Integer id;

    // message 直接提供错误信息
    @NotNull(message = "标准号不能为空")
    @Pattern(regexp = "GB 6657.[0-9]*-2018", message = "用户名格式不正确(例：GB 6657.xx-2018)")
    private String std_num;

    /**
     * 中文名称
     */
    @NotNull
    @Size(min = 1, max = 100, message = "中文名不能为空！")
    private String zhname;

    /**
     * 版本
     */
    @NotNull
    @Size(min = 1, max = 10, message = "版本不能为空！")
    private String version;

    /**
     * 关键字/词
     */
    @NotNull
    @Size(min = 1, max = 20, message = "关键字/词不能为空！")
    private String keys;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date release_date;

    /**
     * 实施日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "实施日期不能为空！")
    @Future(message = "填一个将来的日期")
    private Date impl_date;

    /**
     * 附件路径
     */
    private String package_path;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStd_num() {
        return std_num;
    }

    public void setStd_num(String std_num) {
        this.std_num = std_num;
    }

    public String getZhname() {
        return zhname;
    }

    public void setZhname(String zhname) {
        this.zhname = zhname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getImpl_date() {
        return impl_date;
    }

    public void setImpl_date(Date impl_date) {
        this.impl_date = impl_date;
    }

    public String getPackage_path() {
        return package_path;
    }

    public void setPackage_path(String package_path) {
        this.package_path = package_path;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Standard other = (Standard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getStd_num() == null ? other.getStd_num() == null : this.getStd_num().equals(other.getStd_num()))
                && (this.getZhname() == null ? other.getZhname() == null : this.getZhname().equals(other.getZhname()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                && (this.getKeys() == null ? other.getKeys() == null : this.getKeys().equals(other.getKeys()))
                && (this.getRelease_date() == null ? other.getRelease_date() == null : this.getRelease_date().equals(other.getRelease_date()))
                && (this.getImpl_date() == null ? other.getImpl_date() == null : this.getImpl_date().equals(other.getImpl_date()))
                && (this.getPackage_path() == null ? other.getPackage_path() == null : this.getPackage_path().equals(other.getPackage_path()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStd_num() == null) ? 0 : getStd_num().hashCode());
        result = prime * result + ((getZhname() == null) ? 0 : getZhname().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getKeys() == null) ? 0 : getKeys().hashCode());
        result = prime * result + ((getRelease_date() == null) ? 0 : getRelease_date().hashCode());
        result = prime * result + ((getImpl_date() == null) ? 0 : getImpl_date().hashCode());
        result = prime * result + ((getPackage_path() == null) ? 0 : getPackage_path().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", std_num=").append(std_num);
        sb.append(", zhname=").append(zhname);
        sb.append(", version=").append(version);
        sb.append(", keys=").append(keys);
        sb.append(", release_date=").append(release_date);
        sb.append(", impl_date=").append(impl_date);
        sb.append(", package_path=").append(package_path);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
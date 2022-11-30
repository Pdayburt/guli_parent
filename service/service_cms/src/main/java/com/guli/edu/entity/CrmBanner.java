package com.guli.edu.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 首页banner表
* @TableName crm_banner
*/
@ApiModel("CrmBanner横幅对象")
public class CrmBanner implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private String id;
    /**
    * 标题
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("标题")
    @Length(max= 20,message="编码长度不能超过20")
    private String title;
    /**
    * 图片地址
    */
    @NotBlank(message="[图片地址]不能为空")
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("图片地址")
    @Length(max= 500,message="编码长度不能超过500")
    private String imageUrl;
    /**
    * 链接地址
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("链接地址")
    @Length(max= 500,message="编码长度不能超过500")
    private String linkUrl;
    /**
    * 排序
    */
    @NotNull(message="[排序]不能为空")
    @ApiModelProperty("排序")
    private Object sort;
    /**
    * 逻辑删除 1（true）已删除， 0（false）未删除
    */
    @NotNull(message="[逻辑删除 1（true）已删除， 0（false）未删除]不能为空")
    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date gmtCreate;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    private Date gmtModified;

    /**
    * ID
    */
    public void setId(String id){
    this.id = id;
    }

    /**
    * 标题
    */
    public void setTitle(String title){
    this.title = title;
    }

    /**
    * 图片地址
    */
    public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
    }

    /**
    * 链接地址
    */
    public void setLinkUrl(String linkUrl){
    this.linkUrl = linkUrl;
    }

    /**
    * 排序
    */
    public void setSort(Object sort){
    this.sort = sort;
    }

    /**
    * 逻辑删除 1（true）已删除， 0（false）未删除
    */
    public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
    }

    /**
    * 创建时间
    */
    public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
    }

    /**
    * 更新时间
    */
    public void setGmtModified(Date gmtModified){
    this.gmtModified = gmtModified;
    }


    /**
    * ID
    */
    public String getId(){
    return this.id;
    }

    /**
    * 标题
    */
    private String getTitle(){
    return this.title;
    }

    /**
    * 图片地址
    */
    public String getImageUrl(){
    return this.imageUrl;
    }

    /**
    * 链接地址
    */
    public String getLinkUrl(){
    return this.linkUrl;
    }

    /**
    * 排序
    */
    public Object getSort(){
    return this.sort;
    }

    /**
    * 逻辑删除 1（true）已删除， 0（false）未删除
    */
    public Integer getIsDeleted(){
    return this.isDeleted;
    }

    /**
    * 创建时间
    */
    public Date getGmtCreate(){
    return this.gmtCreate;
    }

    /**
    * 更新时间
    */
    public Date getGmtModified(){
    return this.gmtModified;
    }

}

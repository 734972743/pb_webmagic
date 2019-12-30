package com.pb.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //表明是一个实体类
@Table(name="t_img")  //表名
public class Img {

	@Id()
	@GeneratedValue(strategy= GenerationType.IDENTITY)  //表示是自增方式
	private Integer id ;
	
	private String imgName;
	private String imgUrl;
	private String htmlUrl;
	private Date createDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", imgName=" + imgName + ", imgUrl=" + imgUrl + ", htmlUrl=" + htmlUrl + "]";
	}
	
	
	
	
}

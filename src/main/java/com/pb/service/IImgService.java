package com.pb.service;

import java.util.List;

import com.pb.vo.Img;

public interface IImgService {

	public void saveImg(Img img);
	
	//根据条件去查询图片
	public List<Img> findImgsBy(Img img);
	
	//删除所有的图片数据
	public void deleteAllImgs();
	
	//获取t_img表的最大数据量
	public long getMaxCountImg();
}

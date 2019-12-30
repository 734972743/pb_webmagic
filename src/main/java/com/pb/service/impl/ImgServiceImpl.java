package com.pb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pb.dao.IImgDao;
import com.pb.service.IImgService;
import com.pb.vo.Img;

@Service
public class ImgServiceImpl implements IImgService {
	
	@Autowired
	private IImgDao imgDao;

	@Override
	public void saveImg(Img img) {
		// TODO Auto-generated method stub
		
		//判断数据库中是否有已存在的数据
		Img param = new Img();
		List<Img> list = this.findImgsBy(img);
		if(list.size() ==0) {
			//如果不存在，就保存
			this.imgDao.saveAndFlush(img);
		}else {
			//如果已存在，就执行更新
		}
			
			
		
			
		
		//imgDao.save
	}

	@Override
	public List<Img> findImgsBy(Img img) {
		// TODO Auto-generated method stub
		
		//设置查询条件
		Example  example = Example.of(img);
		return imgDao.findAll(example);
	}

	@Override
	public void deleteAllImgs() {
		this.imgDao.deleteAll();
	}

	@Override
	public long getMaxCountImg() {
		// TODO Auto-generated method stub
		return imgDao.count();
	}

}

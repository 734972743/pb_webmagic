package com.pb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pb.dao.IVideoDao;
import com.pb.service.IVideoService;
import com.pb.vo.Video;

@Service
public class VideoServiceImpl implements IVideoService {
	
	@Autowired
	private IVideoDao videoDao;

	@Override
	public void saveVideo(Video video) {
		// TODO Auto-generated method stub
		//先判断是否有这个视频
		List<Video> videos = this.getVideosBy(video);
		if(videos.size() == 0) {
			videoDao.saveAndFlush(video);
		}
	}

	@Override
	public void deleteAllVideo() {
		videoDao.deleteAll();
		
	}

	@Override
	public List<Video> getVideosBy(Video video) {
		// TODO Auto-generated method stub
		Example<Video> param = Example.of(video);
		return videoDao.findAll(param);
	}

}

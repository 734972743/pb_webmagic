package com.pb.service;

import java.util.List;

import com.pb.vo.Video;

public interface IVideoService {

	public void saveVideo(Video video);
	
	public void deleteAllVideo();
	
	public List<Video> getVideosBy(Video video);
}

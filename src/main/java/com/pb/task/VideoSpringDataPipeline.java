package com.pb.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pb.service.IImgService;
import com.pb.service.IVideoService;
import com.pb.utils.HttpUtils;
import com.pb.vo.Img;
import com.pb.vo.Video;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component  //标注为组件
public class VideoSpringDataPipeline implements Pipeline {
	
	@Autowired
	private IVideoService videoService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		int size = resultItems.get("size");
		for(int i=0; i<size;i++) {
			Video video = resultItems.get("video"+i);  //获取保存
			//调用service保存数据
			
			videoService.saveVideo(video);
			
		}
		
	
		
	}



}

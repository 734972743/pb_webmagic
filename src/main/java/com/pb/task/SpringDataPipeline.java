package com.pb.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pb.service.IImgService;
import com.pb.utils.HttpUtils;
import com.pb.vo.Img;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component  //标注为组件
public class SpringDataPipeline implements Pipeline {
	
	@Autowired
	private IImgService imgService;
	
	@Autowired
	private HttpUtils httpUtils;

	@Override
	public void process(ResultItems resultItems, Task task) {
		int size = resultItems.get("size");
		for(int i=0; i<size;i++) {
			Img img = resultItems.get("img"+i);  //获取保存的img
			//调用service保存数据
			
			imgService.saveImg(img);
			
			
			//下载图片
			//F:\网路爬虫\webmagic
			this.httpUtils.downImage(img.getImgUrl(),"F:\\网路爬虫\\webmagic\\images",img.getImgName()+(i+1));
		}
		
	
		
	}



}

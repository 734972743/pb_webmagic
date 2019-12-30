package com.pb.task;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pb.service.IImgService;
import com.pb.service.IVideoService;
import com.pb.vo.Img;
import com.pb.vo.Video;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;


@Component
public class VideoProcessor implements PageProcessor{
	
	@Autowired
	private VideoSpringDataPipeline videoSpringDataPipeline;  //这个是我们自己定义的的pipeline

	@Autowired
	private IVideoService videoService;
	
	
	
	private Spider spider = null;
	
	//site为爬虫请求设置
	private Site site = Site.me()
			.setCharset("UTF-8")
			.setTimeOut(1000*10)
			.setRetryTimes(3)
			.setRetrySleepTime(1000*3);
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String baseUrl = "http://meijutw.com";
		//视频标题
		 String videoTitle = Jsoup.parse(page.getHtml().css("div.content .titln h1").nodes().get(0).toString()).text();
		
		//视频图片
		Element videoImageElement = Jsoup.parse(page.getHtml().css("div#wp1ay img").nodes().get(0).toString());
		String videoImageUrl = videoImageElement.getElementsByTag("img").get(0).attr("src");
		videoImageUrl = baseUrl + videoImageUrl;
		
		
		 String videoIntroduce = Jsoup.parse(page.getHtml().css("div#wp1ay p").nodes().get(0).toString()).text();
		 
		 List<Selectable> list = page.getHtml().css("div#yt_l1 ul li").nodes();
		 
		//视频对应的集数;
		 for(int i=0 ;i<list.size();i++) {
			 Video video = new Video();
			 
			 Element videoUrlElement = Jsoup.parse(list.get(i).css("a").nodes().get(0).toString());
			 String videoUrl = videoUrlElement.getElementsByTag("a").get(0).attr("href");
			 videoUrl = baseUrl + videoUrl;
			 
			 String videoName = Jsoup.parse(list.get(i).css("a").nodes().get(0).toString()).text();

			video.setVideoName(videoTitle + videoName);
			video.setVideoUrl(videoUrl);
			video.setVideoImageUrl(videoImageUrl);
			video.setVideoIntroduce(videoIntroduce);
			video.setVideoSource("http://meijutw.com/1587");
			
			Date date = new Date();
			video.setCreateDate(date);
			
			
			//保存数据
			page.putField("size", list.size());
			page.putField("video"+i, video);
		 } 
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	//@Scheduled 这个注解是用于定时任务的
	//initialDelay 这个是当任务启动后，等多久执行方法（单位毫秒）
	//fixedDelay 每隔多久执行这个方法（单位毫秒）
	@Scheduled(initialDelay=1000, fixedDelay=1000*100) 
	//@Scheduled(cron="0 0 0 ? * 1") //设置定时任务  每周1更新一次:  秒 分 时 日 月 周 年(可省)
	public void process() {
		//1。先删除掉旧的数据
		videoService.deleteAllVideo();
		
		//2.再爬新的数据
		if(spider == null) {
			spider = Spider.create(new VideoProcessor())
					.addUrl("http://meijutw.com/1587/")
					.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))  //BloomFilterDuplicateRemover里面的参数越大，则遗漏的可能行越低，但消耗的内存越大
					.thread(10)
					.addPipeline(videoSpringDataPipeline);  //设置数据保存方式	
		}
		
		spider.run();
		 
	}

}

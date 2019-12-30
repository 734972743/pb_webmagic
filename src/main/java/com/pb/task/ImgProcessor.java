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
import com.pb.vo.Img;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;


@Component
public class ImgProcessor implements PageProcessor{
	
	@Autowired
	private SpringDataPipeline springDataPipeline;  //这个是我们自己定义的的pipeline

	@Autowired
	private IImgService imgService;
	
	
	
	private Spider spider = null;
	
	//site为爬虫请求设置
	private Site site = Site.me()
			.setCharset("gbk")
			.setTimeOut(1000*10)
			.setRetryTimes(3)
			.setRetrySleepTime(1000*3);
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		 List<Selectable> list = page.getHtml().css("div#ks_xp img").nodes();
		 
		 String imgName =  Jsoup.parse(page.getHtml().css("div#ks_xp div.title").nodes().get(0).toString()).text();
	
		 for(int i=0 ;i<list.size();i++) {
			 Img img = new Img();
			 Document document = Jsoup.parse(list.get(i).toString());
			 Elements eles =  document.select("img");
			Element element = eles.get(0);
			 
			String url =  element.attr("src");
			img.setImgUrl(url);
			
			
		
			img.setImgName(imgName);
			String html = page.getUrl().toString();
			
			img.setHtmlUrl(html);
			
			Date date = new Date();
			img.setCreateDate(date);
			
			
			//保存数据
			page.putField("size", list.size());
			page.putField("img"+i, img);
		 }
		 
//		 long count = this.imgService.getMaxCountImg();
//		 if(count>1000) {
//			 spider.stop();  //如果爬的数量够了，就停止
//		 }
		 
		 //获取下一页的url
		 String nextUrl = page.getHtml().css("li.next").nodes().get(0).links().toString();
		 //把url 放到任务队列中
		page.addTargetRequest(nextUrl);
		 
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	//@Scheduled 这个注解是用于定时任务的
	//initialDelay 这个是当任务启动后，等多久执行方法（单位毫秒）
	//fixedDelay 每隔多久执行这个方法（单位毫秒）
	//@Scheduled(initialDelay=1000, fixedDelay=1000*100)  //设置定时任务
	public void process() {
		//先删除掉旧的数据
//		imgService.deleteAllImgs();
//		if(spider == null) {
//			spider = Spider.create(new ImgProcessor())
//					.addUrl("http://www.baidu.com")
//					.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))  //BloomFilterDuplicateRemover里面的参数越大，则遗漏的可能行越低，但消耗的内存越大
//					.thread(10)
//					.addPipeline(springDataPipeline);  //设置数据保存方式
//					
//					
//		}
		
		//spider.run();
		 
	}
	
	
	//解析页面，保存数据
	public void saveImg(Page page) {
//		Img img = new Img();
//		img.setImgName();
//		img.setImgUrl();
//		img.setHtmlUrl();;
	}

}

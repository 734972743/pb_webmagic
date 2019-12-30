package com.pb.task;

import java.net.Proxy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class ProxyTest implements PageProcessor{

	private Site site = Site.me()
			.setTimeOut(1000*10)
			.setRetryTimes(3)
			.setRetrySleepTime(1000*3);
	
	
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String html  = page.getHtml().css("div.shuru.biankuang").nodes().get(1).toString();
		System.out.println(html);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	//@Scheduled(fixedDelay=1000)
	public void process() {
		//创建一个下载器Downloader
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		//给下载器设置服务器信息
		//httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("218.75.69.50", 39212)));
		httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new us.codecraft.webmagic.proxy.Proxy("218.75.69.50", 39212)));
		
		Spider spider = Spider.create(new ProxyTest())
		.addUrl("www.baidu.com")
		.setDownloader(httpClientDownloader);
		
		//spider.run();
	}

}

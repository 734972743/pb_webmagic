package com.pb.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//定时任务
@Component
public class TaskTest {

	
	
	
	//@Scheduled(cron="0/5 * * * * *")
	public static void task() {
		Date date =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("定时任务执行"+sdf.format(date));
	}
}

package com.pb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication  //表明这个类是启动类
@EnableScheduling //开启定时任务
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("-- springboot 启动成功 --- ");
	}
}

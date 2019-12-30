package com.pb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {
	
	private boolean isFirst = true;
	
	private PoolingHttpClientConnectionManager cm ;  //HttpClient使用连接池池
	
	
	public HttpUtils() {
		this.cm = new PoolingHttpClientConnectionManager();
		
		//设置最大连接数
		this.cm.setMaxTotal(100);
		
		//设置每个主机最大连接数
		this.cm.setDefaultMaxPerRoute(10);
	}

	//根据请求地址来下载页面数据
	public String doGetHtml(String url) {
		//1.获取httpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
		
		//2.创建httpGet请求对象，设置url地址
		HttpGet httpGet = new HttpGet(url);
		
		//设置请求信息
		httpGet.setConfig(this.getConfig());
		
		//3.使用httpClient发起请求，获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);

			//4.解析响应，返回结果
			if(response.getStatusLine().getStatusCode() == 200) {
				//如果返回成功
				if(response.getEntity() != null) {  //判断响应结果不为空
					//EntityUtils.toString 解析数据
					String content = EntityUtils.toString(response.getEntity(),"utf8");
					return content;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭response
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//如果请求有问题，则返回空串
		return "";
	}

	
	
	
	/**
	 * //下载图片
	 * @param url  图片url
	 * @param fileName  //图片存放的路径
	 * @return
	 */
	public String downImage(String url,String fileName,String imgName) {
		//1.获取httpClient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
		
		//2.创建httpGet请求对象，设置url地址
		HttpGet httpGet = new HttpGet(url);
		
		//设置请求信息
		httpGet.setConfig(this.getConfig());
		
		//3.使用httpClient发起请求，获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);

			//4.解析响应，返回结果
			if(response.getStatusLine().getStatusCode() == 200) {
				//如果返回成功
				if(response.getEntity() != null) {  //判断响应结果不为空
					
					//下载图片
					//获取图片的后缀名
					String extName = url.substring(url.lastIndexOf("."));
		
					//如果没有文件名 生成图片名，并创建图片
					if(imgName.equals("")) {
						imgName = UUID.randomUUID().toString()+extName;
					}else {
						imgName = imgName + extName ;
					}
					
					//下载图片
					OutputStream os = new FileOutputStream(fileName+"/"+imgName);
					
					//清空目录
					if(this.isFirst) {  
						//用于第一次执行
						delAllFile(fileName);  
					}
					
					
					//在下载图片
					response.getEntity().writeTo(os);
					
					//返回图片名称
					return imgName;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭response
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//如果请求有问题，则返回空串
		return "";
	}
	
	
	
	private RequestConfig getConfig() {
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(10000)    //创建连接的最长时间
				.setConnectionRequestTimeout(5000)   //获取连接最长时间
				.setSocketTimeout(10000)       //数据传输的最长时间
				.build();
		
		return config;
	}
	
		/* 删除指定文件夹下所有文件
		/* param path 文件夹完整绝对路径
		 */
		public boolean delAllFile(String path) {
			this.isFirst = false;
			
			boolean flag = false;
			File file = new File(path);
			if (!file.exists()) {
				return flag;
			}
			if (!file.isDirectory()) {
				return flag;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
//					delFolder(path + "/" + tempList[i]);// 再删除空文件夹
					flag = true;
				}
			}
			return flag;
		}

	
}

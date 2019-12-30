package Jsoup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class JsoupFirstTest {

	@Test
	public void testUrl() {
		//解析URL
		try {
			Document doc = Jsoup.parse(new URL("http://www.itcast.cn/subject/about/index.html?about&jingjiaczpz-PC-1"),1000);
			String html = doc.getElementsByClass("sechd_span2").first().text(); 
			System.out.println(html);
			System.out.println("123");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testString() throws IOException {
		//利用工具来读取文件，获取字符串
		String content = FileUtils.readFileToString(new File("C:\\Users\\ASUS\\Desktop/Jsoup.html"));
		
		//解析字符串
		String str  = Jsoup.parse(content).getElementsByClass("title").first().text();
		System.out.println(str);
	}
	
	@Test
	public void testFile() throws IOException {
		Document doc = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop/Jsoup.html"),"utf8");
		String str = doc.getElementsByClass("title").first().text();
		System.out.println(str);
	}
	
	@Test
	public void testDocument()   {
		try {
			Document doc = Jsoup.parse(new URL("http://www.imooc.com/wenda/detail/583092"), 10000);
			String str = doc.getElementsByAttributeValue("class", "js-qa-wenda-title").first().text();
			System.out.println(str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSelect() {
		try {
			Document doc = Jsoup.parse(new URL("http://www.imooc.com/wenda/detail/583092"), 10000);
			//Element ele = doc.select("title").first();  //id
			//Element ele = doc.select(".detail-user-tips").first(); //class
			//Element ele = doc.select("[href]").first();   //属性
			Element ele = doc.select("[href=/wenda/143]").first();   //属性=属性值
			
		
			System.out.println(ele.text());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

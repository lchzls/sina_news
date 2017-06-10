package web.news.sina.home;

import java.util.ArrayList;
import java.util.List;

import web.news.sina.thread.SpiderThread;

public class SinaNewsSpider {
	
	private static SinaNewsSpider sinaNewsSpider = null;
	private static List<String> urls = new ArrayList<String>();
	
	static{
		urls.add("http://roll.news.sina.com.cn/interface/rollnews_ch_out_interface.php?col=90&spec=&type=&ch=01&k=&offset_page=0&offset_num=0&num=60&asc=&page=1&r=0.6614491886197036");
		urls.add("http://roll.news.sina.com.cn/interface/rollnews_ch_out_interface.php?col=91&spec=&type=&ch=01&k=&offset_page=0&offset_num=0&num=60&asc=&page=1&r=0.5813715988325224");
		urls.add("http://roll.news.sina.com.cn/interface/rollnews_ch_out_interface.php?col=92&spec=&type=&ch=01&k=&offset_page=0&offset_num=0&num=60&asc=&page=1&r=0.38744917813972135");
	}
	
	private SinaNewsSpider(){}
	
	public static SinaNewsSpider getInstance(){
		if(sinaNewsSpider == null){
			sinaNewsSpider = new SinaNewsSpider();
		}
		return sinaNewsSpider;
	}
	
	public void start(int spiderNum, int freshTime){
		for(String url:urls){
			new SpiderThread(url, spiderNum, freshTime).start();
		}
	}
	
	public static void main(String[] args) {
	//多少条  多少次刷新一次
		SinaNewsSpider.getInstance().start(30, 1);
	}
}

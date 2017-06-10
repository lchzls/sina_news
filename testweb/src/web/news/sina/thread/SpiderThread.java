package web.news.sina.thread;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.news.sina.domain.NewsBean;
import web.news.sina.model.DownLoad;
import web.news.sina.utils.CommonUtil;
import web.news.sina.utils.DBUtil;

public class SpiderThread extends Thread{
	
	private String url;
	private int spiderNum;
	private int freshTime;
	
	public SpiderThread(String url, int spiderNum, int freshTime){
		this.url = url;
		this.spiderNum = spiderNum;
		this.freshTime = freshTime;
	}
	
	public void run(){
		
		while(true){
			
			List<NewsBean> beanList = new ArrayList<NewsBean>();
			String content = DownLoad.getContent(url);
			Document document = Jsoup.parse(content);
			Element body = document.getElementsByTag("body").first();
			String text = body.text().replaceAll("var jsonData = ", "");
			try {
				JSONObject jsonObj = new JSONObject(text);
				String list = jsonObj.getString("list");
				JSONArray jsonArray = new JSONArray(list);
				if(spiderNum > jsonArray.length()){
					spiderNum = jsonArray.length();
				}
				for(int i=0; i<spiderNum; i++){
					NewsBean newsBean = new NewsBean();
					newsBean.setId(i+1);
					Object object = jsonArray.get(i);
					String record = object.toString();
					JSONObject jobj = new JSONObject(record);
					String channel = jobj.getString("channel");
					JSONObject chanobj = new JSONObject(channel);
					String column = chanobj.getString("title");
					String title = jobj.getString("title");
					newsBean.setTitle(title);
					String time = jobj.getString("time");
					
					newsBean.setFreshDate(CommonUtil.getDate(time));
					newsBean.setColumn(column);
					beanList.add(newsBean);
				}
				
				if(url.equals("http://roll.news.sina.com.cn/interface/rollnews_ch_out_interface.php?col=91&spec=&type=&ch=01&k=&offset_page=0&offset_num=0&num=60&asc=&page=1&r=0.5813715988325224")){
					System.out.println();
				}
				DBUtil.delete(beanList.get(0).getColumn());
				DBUtil.insert(beanList);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(freshTime * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

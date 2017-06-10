package web.news.sina.model;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class DownLoad {
	
	public static void main(String[] args) {
		System.out.println(getContent("http://roll.news.sina.com.cn/interface/rollnews_ch_out_interface.php?col=91&spec=&type=&ch=01&k=&offset_page=0&offset_num=0&num=60&asc=&page=1&r=0.5813715988325224"));
	}
	
	public static String getContent(String url){
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		AbstractHttpClient httpClient = new DefaultHttpClient(params);
		HttpGet getHttp = new HttpGet(url);

		HttpResponse response;

		String content = null;
		try {
			response = httpClient.execute(getHttp);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity, "gb2312");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}

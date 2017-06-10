package web.news.sina.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import web.news.sina.domain.NewsBean;

public class DBUtil {
	public static void insert(List<NewsBean> listBean){
		Connection conn = DBConn.getConnection();
		String sql = "";
		
		if(listBean.get(0).getColumn().equals("国内")){
			sql = "insert into domestic values(?, ?, ?, sysdate(), ?)";
		}else if(listBean.get(0).getColumn().equals("国际")){
			sql = "insert into national values(?, ?, ?, sysdate(), ?)";
		}else{
			sql = "insert into social values(?, ?, ?, sysdate(), ?)";
		}
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i=0; i<listBean.size(); i++){
				NewsBean newsBean = listBean.get(i);
				ps.setInt(1, newsBean.getId());
				ps.setString(2, newsBean.getColumn());
				ps.setString(3, newsBean.getTitle());
				ps.setString(4, newsBean.getFreshDate());
				ps.executeUpdate();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(String type){
		Connection conn = DBConn.getConnection();
		String sql = "";
		
		if(type.equals("国内")){
			sql = "delete from domestic where id < 10000";
		}else if(type.equals("国际")){
			sql = "delete from national where id < 10000";
		}else{
			sql = "delete from social where id < 10000";
		}
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package web.news.sina.domain;

public class NewsBean {
	int id;
	public String column;
	public String title;
	public String freshDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFreshDate() {
		return freshDate;
	}
	public void setFreshDate(String freshDate) {
		this.freshDate = freshDate;
	}
	@Override
	public String toString() {
		return "NewBean [id=" + id + ", column=" + column + ", title=" + title + ", freshDate=" + freshDate + "]";
	}
}

package dao;

import java.util.List;

import bean.ColumnBean;

public interface ColumnDAOInterface {
	public boolean insertColumn(ColumnBean column);
	public ColumnBean updateColumn(ColumnBean column);
	public ColumnBean selectByArticleNo(int article_no);
	public List<ColumnBean>selectAllColumns();
	public boolean deleteColumnByNo(int article_no);
}

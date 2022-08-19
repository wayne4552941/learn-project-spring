package dao;

import java.util.List;

import bean.ExamBean;


public interface ExamDaoInterface {
	
	public ExamBean insert(ExamBean ExamBean);
	public List<ExamBean> select(Integer subIdx,Integer eduIdx);
	public List<ExamBean> selectAll();
	public ExamBean update(Integer examId, ExamBean examBean);
	public boolean deleteOne(Integer id);
	
	
}

package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.EduBean;
import bean.ExamBean;

import dao.ExamDao;
import bean.SubBean;
import util.ExamUtil;
public class ExamService {
	
	private ExamDao examDao;
	
	public ExamService(Session session) {
		super();
		this.examDao = new ExamDao(session);
	}
	
	//增加
	public ExamBean insert(String subString,String eduString,String examName,String examDate){
		
		
		SubBean subBean = new SubBean(ExamUtil.getSubIdx(subString), subString);
		EduBean eduBean = new EduBean(ExamUtil.getEduIdx(eduString), eduString);
		
//		System.out.println(ExamUtil.getSubIdx(subString)+subString);
//		System.out.println(ExamUtil.getEduIdx(eduString)+eduString);
		
		ExamBean insBean = new ExamBean();
		
		try {
			
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);
			insBean = new ExamBean(subBean, eduBean, examName, tDate); 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return examDao.insert(insBean);
		
	}
	
	//修改
	public ExamBean update(String updateId, String subString,String eduString,String examName,String examDate){
		
		
		Integer upId = Integer.valueOf(updateId);
		SubBean subBean = new SubBean(ExamUtil.getSubIdx(subString), subString);
		EduBean eduBean = new EduBean(ExamUtil.getEduIdx(eduString), eduString);
		
//		System.out.println(ExamUtil.getSubIdx(subString)+subString);
//		System.out.println(ExamUtil.getEduIdx(eduString)+eduString);
		
		ExamBean upBean = new ExamBean();
		
		try {
			
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);
			upBean = new ExamBean(subBean, eduBean, examName, tDate); 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(upBean);
		return examDao.update(upId, upBean);
		
	}
	
	//查詢
	public List<ExamBean> select(String subString,String eduString){
		
		
		Integer subIdx = ExamUtil.getSubIdx(subString);
		Integer eduIdx = ExamUtil.getEduIdx(eduString);
		return examDao.select(subIdx,eduIdx);
		
	}
	
	
	//查詢全部
	public List<ExamBean> selectAll(){
		
		return examDao.selectAll();
		
	}
	
	
	
	//刪除
	public boolean delete(Integer id){
				
		
		return examDao.deleteOne(id);
		
	}
	
	
}

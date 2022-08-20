package service;

import java.util.List;

import org.hibernate.Session;

import bean.CourseBean;
import cartdao.impt.CourseDao;


public class CourseService implements CourseServiceInterface {

	private CourseDao cosDao;

	public CourseService() {
		this.cosDao = new CourseDao();
	}

	

	@Override
	public CourseBean insert(CourseBean cosBean) {
		CourseBean theCos = cosDao.insert(cosBean);
		return theCos;
	}

	@Override
	public CourseBean select(int course_id) {
		CourseBean theCos = cosDao.select(course_id);
		return theCos;
	}
	
	@Override
	public List<CourseBean> selectName(String course_name) {
		return cosDao.selectName(course_name);
	}

	@Override
	public List<CourseBean> selectAll() {
		return cosDao.selectAll();
	}

	@Override
	public CourseBean updateOne(int course_id, int user_id, int subject_id, int education_id, String course_name,
			String course_introduction, float course_price, String course_duration, int enrollment, String course_date,
			String lecturer_name, String lecturer_email, String course_picture) {
		CourseBean theCos = cosDao.updateOne(course_id, user_id, subject_id, education_id, course_name,
				course_introduction, course_price, course_duration, enrollment, course_date, lecturer_name,
				lecturer_email, course_picture);
		return theCos;
	}

	@Override
	public boolean deleteOne(int course_id) {
		return cosDao.deleteOne(course_id);
	}



	

}

package service;

import java.util.List;

import bean.CourseBean;

public interface CourseServiceInterface {
	public CourseBean insert(CourseBean cosBean);

	public CourseBean select(int course_id);
	
	public List<CourseBean> selectName(String course_name);

	public List<CourseBean> selectAll();

	public CourseBean updateOne(int course_id, int user_id, int subject_id, int education_id, String course_name,
			String course_introduction, float course_price, String course_duration, int enrollment, String course_date,
			String lecturer_name, String lecturer_email, String course_picture);

	public boolean deleteOne(int course_id);

}

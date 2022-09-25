package cartdao.impt;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import bean.CourseBean;
import cartdao.CourseDAOInterface;
//import util.HibernateUtil;

public class CourseDao implements CourseDAOInterface {

	@Autowired
	private SessionFactory factory;

	public CourseDao() {
		//this.factory = HibernateUtil.getFactory();
	}

	@Override
	public CourseBean insert(CourseBean cosBean) {
		Session session = factory.getCurrentSession();
		CourseBean courseBean = session.get(CourseBean.class, cosBean.getCourse_id());
		if (courseBean == null) {
			session.save(cosBean);
			return cosBean;
		}
		return null;
	}

	@Override
	public CourseBean select(int course_id) {
		Session session = factory.getCurrentSession();
		return session.get(CourseBean.class, course_id);
	}
	
	@Override
	public List<CourseBean> selectName(String course_name) {
		Session session = factory.getCurrentSession();
		Query<CourseBean> queryName = session.createQuery("from CourseBean where course_name like :name", CourseBean.class)
				.setParameter("name", "%" + course_name + "%");
		return queryName.list();
	}

	@Override
	public List<CourseBean> selectAll() {
		Session session = factory.getCurrentSession();
		Query<CourseBean> query = session.createQuery("from CourseBean", CourseBean.class);
		return query.list();

	}

	@Override
	public CourseBean updateOne(int course_id, int user_id, int subject_id, int education_id, String course_name,
			String course_introduction, float course_price, String course_duration, int enrollment, String course_date,
			String lecturer_name, String lecturer_email, String course_picture) {
		Session session = factory.getCurrentSession();
		CourseBean courseBean = session.get(CourseBean.class, course_id);

		if (courseBean != null) {
			courseBean.setUser_id(user_id);
			courseBean.setEducation_id(education_id);
			courseBean.setSubject_id(subject_id);
			courseBean.setCourse_name(course_name);
			courseBean.setCourse_introduction(course_introduction);
			courseBean.setCourse_price(course_price);
			courseBean.setCourse_duration(course_duration);
			courseBean.setEnrollment(enrollment);
			courseBean.setCourse_date(course_date);
			courseBean.setLecturer_name(lecturer_name);
			courseBean.setLecturer_email(lecturer_email);
			courseBean.setCourse_picture(course_picture);

			return courseBean;
		}
		return null;
	}

	@Override
	public boolean deleteOne(int course_id) {
		Session session = factory.getCurrentSession();
		CourseBean courseBean = session.get(CourseBean.class, course_id);

		if (courseBean != null) {
			session.delete(courseBean);
			return true;
		}

		return false;
	}

	

}

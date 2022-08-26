//package dao;
//
//
//
//import java.lang.reflect.Member;
//
//import bean.MemberBean;
//import cartdao.BaseDAO;
//import memberbean.User;
//import util.JdbcUtil;
//
//
//
//public class logindao extends BaseDAO<MemberBean>{
//	public MemberBean checkAccount(String account,String password) {
//		try {
//			String sql = "select * from member where account = ? and password = ? ";
//			MemberBean user = QueryForOne(sql, MemberBean.class,account,password);
//			return user;
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//}
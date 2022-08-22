package service;

import java.util.List;

import bean.MemberBean;
import bean.MemberDao;

public class MemberService {

	private MemberDao mDao;
	
	public MemberService() {
		this.mDao=new MemberDao();
	}

	public MemberBean checkLogin(String loginAccount, String loginPassword) {
		return mDao.queryAccountAndPassword(loginAccount, loginPassword);
	}

	
	public MemberBean insertUser(MemberBean memberBean) {
		return mDao.insertUser(memberBean);
	}

	public List<MemberBean> selectAllMembers() {
		return mDao.selectAllMembers();
	}

	public boolean deleteUser(String account) {
		return mDao.deleteUser(account);
	}

	public MemberBean selectUserByAccount(String account) {
		System.out.println("selectuseraccount service");
		return mDao.selectUserByAccount(account);
	}

	public MemberBean updateUser(MemberBean memberBean) {
				return mDao.updateUser(memberBean);
	}
		

	public MemberBean registerUser(MemberBean newRegister) {
		return mDao.newRegister(newRegister);
		
	}

	
}

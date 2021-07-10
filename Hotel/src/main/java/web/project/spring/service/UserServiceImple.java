package web.project.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.spring.domain.UserVO;
import web.project.spring.persistence.UserDAO;

@Service
public class UserServiceImple implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImple.class);

	@Autowired
	private UserDAO user_dao;
	
	@Override
	public int createUser(UserVO user_vo) {
		logger.info("createUser() 호출 : vo = " + user_vo.toString());
		return user_dao.insertUser(user_vo);
	}

	@Override
	public UserVO readByUserId(String user_id) {
		logger.info("readByUserId() 호출 : user_id = " + user_id);
		return user_dao.selectByUserId(user_id);
	}

	@Override
	public UserVO readByUserIdUserPw(String user_id, String user_pw) {
		logger.info("readByUserIdUserPw() 호출 : user_id = " + user_id + ", user_pw = " + user_pw);
	    return user_dao.selectByUserIdUserPw(user_id, user_pw);
	}

	@Override
	public UserVO readByUserNameUserEmail(String user_name, String user_email) {
		logger.info("readByUserNameUserEmail() 호출 : user_name = " + user_name + ", user_email = " + user_email);
		return user_dao.selectByUserNameUserEmail(user_name, user_email);
	}

	@Override
	public UserVO readByUserIdUserEmail(String user_id, String user_email) {
		logger.info("readByUserIdUserEmail() 호출 : user_id = " + user_id + ", user_email = " + user_email);
	    return user_dao.selectByUserIdUserEmail(user_id, user_email);
	}

	@Override
	public int readByUserIdCheck(String user_id) {
		logger.info("readByUserIdCheck() 호출 : user_id = " + user_id);
		return user_dao.selectByUserIdCheck(user_id);
	}
	
	@Override
	public int readByUserEmailCheck(String user_email) {
		logger.info("readByUserEmailCheck() 호출 : user_email = " + user_email);
		return user_dao.selectByUserEmailCheck(user_email);
	}
	
	@Override
	public UserVO readByUserIdUserPwUserEmail(String user_id, String user_pw, String user_email) {
		logger.info("readByUserIdUserPwUserEmail() 호출 : user_id = " + user_id + "user_pw = " + user_pw + "user_email = " + user_email);
		return user_dao.selectByUserIdUserPwUserEmail(user_id, user_pw, user_email);
	}

	@Override
	public int updateUser(UserVO user_vo) {
		logger.info("updateUser() 호출 : user_vo = " + user_vo.toString());
		return user_dao.updateUser(user_vo);
	}

	@Override
	public int updateUserPw(UserVO user_vo) {
		logger.info("updateUserPw() 호출 : user_vo = " + user_vo.toString());
		return user_dao.updateUserPw(user_vo);
	}

	@Override
	public int deleteUser(String user_id) {
		logger.info("deleteUser() 호출 : user_id = " + user_id);
		return user_dao.deleteUser(user_id);
	}

}

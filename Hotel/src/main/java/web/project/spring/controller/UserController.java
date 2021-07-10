package web.project.spring.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.project.spring.domain.UserVO;
import web.project.spring.findpw.RandomOut;
import web.project.spring.service.UserService;

@Controller
@RequestMapping(value = "/hotel/user") // url : /spring/hotel/user/
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// db 연결
	@Autowired
	private UserService user_service;
	@Autowired
	private JavaMailSender mail_sender;
	
	// '회원가입' 페이지
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}
	// '회원가입' 기능
	@PostMapping("/register")
	public String registerPOST(UserVO user_vo, String user_pw_check, RedirectAttributes redirect_attributes, HttpSession http_session) {
		// RedirectAttributes - 재경로 위치에 속성값을 전송하는 객체
		if(user_vo.getUser_pw().equals(user_pw_check) 
				&& user_service.readByUserEmailCheck(user_vo.getUser_email()) == 0
				&& user_service.readByUserId(user_vo.getUser_id()) == null
				&& user_vo.getUser_id().length() >= 5) {
			logger.info("registerPOST() 호출");
			logger.info(user_vo.toString());
			
			int result = user_service.createUser(user_vo); // 삽입 성공 : 1, 실패 : 0
			logger.info(result+"행 삽입");
			
			if(result == 1) {
				// "insert_result"의 키 이름을 가진 데이터를 전송한다.
				redirect_attributes.addFlashAttribute("insert_result", "success");
			}
			return "redirect:/hotel/main"; // main으로 이동(get방식)
			// request response 객체를 전달해서 사용할 수 있다.
			// 기존 redirect방식을 response request 객체를 재사용이 불가능 하지만 위와 같은 방식으로는 가능
		} else {
			redirect_attributes.addFlashAttribute("overlap_check", "fail");
			return "redirect:/hotel/user/register";
		}
	}
	
	// '로그인' 페이지
	@GetMapping("/login")
	public void loginGet() {
		logger.info("loginGet() 호출");
	}
	
	// '로그인' 기능 
	@PostMapping("/login")
	public String loginPost(String user_id, String user_pw, HttpSession http_session, RedirectAttributes redirect_attributes) {
		logger.info("indexPOST() 호출");
        logger.info("user_id : " + user_id);
        logger.info("user_pw : " + user_pw);
		
        UserVO user_vo = user_service.readByUserIdUserPw(user_id, user_pw);
        
        // user_id, user_pw를 디비에 저장되어있는 정보에서 select했을때 vo가 있으면 로그인
        // user_id, user_pw를 디비에 저장되어있는 정보에서 select했을때 vo가 없으면 로그인 실패
        if(user_vo != null) {
        	logger.info("로그인 성공");
        	http_session.setAttribute("user_id", user_id);
        	redirect_attributes.addFlashAttribute("login_result", "success");
        	return "redirect:/hotel/main";
        } else { // 값이 없으면 로그인 실패 >> 로그인 페이지
        	logger.info("로그인 실패");
        	redirect_attributes.addFlashAttribute("login_result", "fail");
        	return "redirect:/hotel/user/login";
        }
	}
	
	// '아이디찾기' 페이지
	@GetMapping("/idfind")
	public void idfindGET() {
		logger.info("idfindGET() 호출");
	}
		
	
	
	
	// '아이디찾기' 기능
	// @requestmapping @postmapping @getmapping 가능
	@RequestMapping(value="/sendmailId", method = RequestMethod.POST)
	public String sendMailId(String user_name, String user_email, RedirectAttributes redirect_attributes) {
		logger.info("user_name : " + user_name + ", user_email : " + user_email);
		
		UserVO user_vo = user_service.readByUserNameUserEmail(user_name, user_email);
		if(user_vo != null) {
			logger.info("이름, 이메일 일치. 이메일을 전송합니다.");
				String subject = "HotelBookers, 아이디 발급메일입니다.";
				String content = "아이디 : " + user_vo.getUser_id() +"입니다.";
				String from = "ehddnoops@naver.com";
				String to = user_vo.getUser_email();
				
				try {
					MimeMessage mail = mail_sender.createMimeMessage();
					MimeMessageHelper mail_helper = new MimeMessageHelper(mail,true,"UTF-8");
					mail_helper.setFrom(from);
	                mail_helper.setFrom("호텔서비스관리자<ehddnoops@naver.com>");
	                mail_helper.setTo(to);
	                mail_helper.setSubject(subject);
	                mail_helper.setText(content, true);//true는 html사용
	                mail_sender.send(mail);
				} catch(Exception e) {
					e.printStackTrace();
				}
			redirect_attributes.addFlashAttribute("id_result", "success");
			return "redirect:/hotel/user/login";
		} else {
			redirect_attributes.addFlashAttribute("id_result", "fail");
			logger.info("아이디 혹은 이메일이 틀렸습니다.");
			return "redirect:/hotel/user/login";
		}
	}
	
	// '비밀번호찾기' 페이지
	@GetMapping("/pwfind")
	public void pwfindGET() {
		logger.info("pwfindGET() 호출");
	}
	
	// '비밀번호찾기' 기능
	@RequestMapping(value="/sendmail", method = RequestMethod.POST)
	public String sendMail(String user_id, String user_email, RedirectAttributes redirect_attributes) {
		logger.info("user_id : " + user_id + ", user_email : " + user_email);
		UserVO user_vo = user_service.readByUserIdUserEmail(user_id, user_email);
		if(user_vo != null) {
			logger.info("아이디, 이메일 일치. 이메일을 전송합니다.");
			String user_pw = RandomOut.getRandomStr(6); // 6자리 난수를 생성하여 db에 넣어줌(pw)
			logger.info("user_pw : " + user_pw);
			
			user_vo = new UserVO(user_vo.getUser_id(), user_pw, user_vo.getUser_name(), user_vo.getUser_phone(), user_vo.getUser_email(), user_vo.getUser_birth());
			int result = user_service.updateUserPw(user_vo); // user_pw update
			logger.info("result = " + result);
			if(result == 1) {
				String subject = "HotelBookers, 임시 비밀번호 발급메일입니다.";
				String content = "임시 비밀번호 : " + user_pw +"\n임시비밀번호로 로그인하여 비밀번호를 변경해 주세요.";
				String from = "ehddnoops@naver.com";
				String to = user_vo.getUser_email(); 
				
				try {
					MimeMessage mail = mail_sender.createMimeMessage();
					MimeMessageHelper mail_helper = new MimeMessageHelper(mail,true,"UTF-8");
					mail_helper.setFrom(from);
	                mail_helper.setFrom("호텔서비스관리자<ehddnoops@naver.com>");
	                mail_helper.setTo(to);
	                mail_helper.setSubject(subject);
	                mail_helper.setText(content, true);
	                mail_sender.send(mail);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			redirect_attributes.addFlashAttribute("pw_result", "success");
			return "redirect:/hotel/user/login";
		} else {
			redirect_attributes.addFlashAttribute("pw_result", "fail");
			logger.info("아이디 혹은 이메일이 틀렸습니다.");
			return "redirect:/hotel/user/login";
		}
	}
	// '회원가입' 기능(ajax를 통한 ID중복확인)
	@PostMapping("/useridcheck")
	@ResponseBody // 서버에서 클라이언트로 응답 데이터를 전송하기 위해서
	public int userIdCheckPost(String user_id) {
		logger.info("userIdCheckPost() 실행");
		logger.info("user_id의 길이 = " + user_id.length());
		int result;
		if(user_id.length() < 5) {
			result = -1;
		} else {
			result = user_service.readByUserIdCheck(user_id); // 있으면 1, 없으면 0
		}
		return result;
	}
	// '회원가입' 기능(ajax를 통한 PW확인)
	@PostMapping("/userpwcheck")
	@ResponseBody
	public int userPwCheckPost(String user_pw, String user_pw_check) {
		logger.info("userPwCheckPost() 실행");
		if(user_pw_check.equals(user_pw)) {
			return 1;
		} else {
			return 0;
		}
	}
	// '회원가입'기능(ajax를 통한 E-mail중복확인)
	@PostMapping("/useremailcheck")
	@ResponseBody
	public int userEmailCheckPost(String user_email) {
		logger.info("userEmailCheckPost() 실행");
		int result = user_service.readByUserEmailCheck(user_email); // 있으면 1, 없으면 0
		return result;
	}
	
	// '회원정보'수정 들어가기 전 계정확인
	@GetMapping("/mypagecheck")
	public void mypagecheckGET() {
		logger.info("mypagecheckGET() 호출");
	}
	// '로그인' 기능('회원정보'로 가기 위해서)
	@PostMapping("/mypagecheck")
	public String mypagecheckPOST(Model model, HttpSession http_session, RedirectAttributes redirect_attributes, String user_pw) {
		logger.info("mypagePOST() 호출");
		String user_id = (String) http_session.getAttribute("user_id");
		
		UserVO user_vo = user_service.readByUserIdUserPw(user_id, user_pw);
		if(user_vo != null) {
			logger.info("계정정보 확인. mypage 수정페이지로 이동합니다.");
			redirect_attributes.addFlashAttribute("user_vo", user_vo);
			return "redirect:/hotel/user/mypageupdate";
		} else {
			logger.info("계정정보 확인 실패. 비밀번호를 다시 입력해주세요");
			redirect_attributes.addFlashAttribute("user_pw_check", "fail");
			return "redirect:/hotel/user/mypagecheck";
		}
	}
	
	// '회원정보'수정 페이지
	@GetMapping("/mypageupdate")
	public void mypageupdateGET(Model model, HttpSession http_session) {
		logger.info("mypageupdateGET() 호출");
		String user_id = (String) http_session.getAttribute("user_id");
		UserVO user_vo = user_service.readByUserId(user_id);
		model.addAttribute("user_vo", user_vo);
	}
	// '회원정보'수정 기능 
	@PostMapping("/mypageupdate")
	public String mypageupdatePOST(UserVO user_vo, RedirectAttributes redirect_attributes, HttpSession http_session) {
		logger.info("mypageupdatePOST() 호출 : user_vo = " + user_vo.toString());
		
		int result = user_service.updateUser(user_vo);
		logger.info(result + "업데이트"); // 업데이트 성공시 1, 실패시 0
		if(result == 1) {
			// "insert_result"의 키이름을 가진 데이터 전송
			redirect_attributes.addFlashAttribute("update_result", "success");
			http_session.removeAttribute("user_id");
			return "redirect:/hotel/user/login"; // mypage로 이동(get방식)
		} else {
			redirect_attributes.addFlashAttribute("update_result", "fail");
			return "redirect:/hotel/user/mypageupdate"; // mypageupdate로 이동(get방식)
		}
	}
	
	// '회원정보'탈퇴 들어가기 전 계정확인
	@GetMapping("/deletecheck")
	public void deletecheckGet() {
		logger.info("deletecheckGet() 호출");
	}
	// '로그인' 기능('회원정보'탈퇴로 가기 위해서)
	@PostMapping("/deletecheck")
	public String deletecheckPOST(Model model, HttpSession http_session, RedirectAttributes redirect_attributes, String user_pw, String user_email) {
		logger.info("deletecheckPOST() 호출");
		
		String user_id = (String) http_session.getAttribute("user_id");
		
		UserVO user_vo = user_service.readByUserIdUserPwUserEmail(user_id, user_pw, user_email);
		if(user_vo != null) {
			logger.info("계정정보 확인. 회원탈퇴페이지로 이동합니다.");
			return "redirect:/hotel/user/mypagedelete"; // hotel/user/mypagedelete 경로로 이동(get방식)
		} else {
			logger.info("계정정보 확인 실패. 비밀번호와 이메일을 다시 입력해주세요");
			redirect_attributes.addFlashAttribute("user_pw_check", "fail");
			return "redirect:/hotel/user/deletecheck";
		}
	}
	
	// '회원정보'탈퇴 기능
	@GetMapping("/mypagedelete")
	public String deleteGet(RedirectAttributes redirect_attributes, HttpSession http_session) {
		logger.info("deleteGet() 호출 ");
		String user_id = (String) http_session.getAttribute("user_id");
		int result = user_service.deleteUser(user_id);
		http_session.removeAttribute("user_id");
		if(result == 1) {
			// "delete_result"의 키이름을 가진 데이터 전송
			redirect_attributes.addFlashAttribute("delete_result", "success");
			return "redirect:/hotel/main"; // hotel/main 경로로 이동(get방식)
		} else {
			redirect_attributes.addFlashAttribute("delete_result", "fail");
			return "redirect:/hotel/main";
		}
	}
	
	// '로그아웃'
	@GetMapping("/logout")
	public String logoutGet(String user_id, HttpSession http_session) {
		http_session.removeAttribute("user_id");
		return "redirect:/hotel/main";
	}
	
	@GetMapping("/test")
	public void df() {
	}
	
	@GetMapping("/test2")
	public void df2() {
		
	}
}

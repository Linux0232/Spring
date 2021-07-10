package web.project.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.project.spring.domain.HotelVO;
import web.project.spring.domain.RoomVO;
import web.project.spring.domain.UserVO;
import web.project.spring.pageutil.PageCriteria;
import web.project.spring.pageutil.PageMaker;
import web.project.spring.service.HotelService;
import web.project.spring.service.UserService;
import web.project.spring.util.MapUtil;

@Controller
@RequestMapping(value = "/hotel/board") // url : /spring/hotel/board/
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	// db 연결
	@Autowired
	private HotelService hotel_service;
	@Autowired
	private UserService user_service;
	
	// '호텔목록' 페이지
	@GetMapping("/list")
	public void listGet(Model model, Integer page, Integer perPage, String sort,
			String keyword, String check_in, String check_out, Integer person) throws ParseException {
		logger.info("listGet() 호출");
		logger.info("page = " + page + ", perPage = " + perPage);
		logger.info("keyword : " + keyword);
		logger.info("sort : " + sort);
		
		// Paging 처리
		PageCriteria c = new PageCriteria();

		// nullPointerException 방지
		if(page != null) {c.setPage(page);}
		if(perPage != null) {c.setNumsPerPage(perPage);}
		if(keyword != null) {
			c.setKeyword(keyword);			
			c.setUrlKeyword(keyword); 
		}
		if(check_in != null) {c.setCheck_in(check_in);}
		if(check_out != null) {c.setCheck_out(check_out);}
		if(person!=null) {c.setPerson(person);}

		List<HotelVO> list;
		// '호텔목록' 정렬(평점순 / 리뷰순 / 최저가격순 / 최고가격순)
		if (sort == null || sort.equals("별점순")) {
			list = hotel_service.readOrderByStar(c);
			model.addAttribute("list", list);
			sort = "별점순";
		} else {
			switch (sort) {
			case "리뷰순":
				list = hotel_service.readOrderByReviewCount(c);
				model.addAttribute("list", list);
				break;
			case "최저가격순":
				list = hotel_service.readOrderByLowPrice(c);
				model.addAttribute("list", list);
				break;
			case "최고가격순":
				list = hotel_service.readOrderByHighPrice(c);
				model.addAttribute("list", list);
				break;
			}
		}
		Integer hotel_count = hotel_service.getTotalHotelInfoNums();
		model.addAttribute("hotel_count", hotel_count);
		
		PageMaker maker = new PageMaker();
		maker.setCriteria(c);
		maker.setTotalCount(hotel_service.getTotalNumsOfRecords(c));
		logger.info("c : " + c);
		model.addAttribute("c", c);
		model.addAttribute("sort", sort);
		
		maker.setPageData();
		model.addAttribute("pageMaker", maker);

	} // end listGet()
	
	// '호텔상세정보' 룸예약페이지
	@GetMapping("/detail")
	public void detailGet(Model model, Integer page, Integer hotel_no, String keyword, 
			String check_in, String check_out, Integer person) throws ParseException{
		logger.info("detailGet()호출 : hotel_no = " + hotel_no + ", keyword = " + keyword + ", check_in = " + check_in + ", check_out = " + check_out + ", person = " + person);
		
		HotelVO hotel_info = hotel_service.readHotelInfo(hotel_no);
		model.addAttribute("hotel_info", hotel_info);
		
		// 호텔넘버를 검색해 hotel_vo를 가져온다.
		List<RoomVO> list = hotel_service.readDetailForRoom(check_in, check_out, hotel_no, person);
		model.addAttribute("list", list);

		model.addAttribute("check_in", check_in);
		model.addAttribute("check_out",check_out);
		model.addAttribute("person", person);
		model.addAttribute("page", page);
		
		// 체크인/체크아웃 날짜 차이를 계산하여 각 룸의 totalPrice를 구해준다.
		long cal_date;
		long cal_date_days = 1; // 체크인,체크아웃 날짜의 차이 (기본값 1)
		
		if(check_in != "" || check_out != "") {
	         // String 타입으로 저장되어있는 체크인/체크아웃 날짜를 yyyy-mm-dd형식의 date타입으로 포맷 해준다.
	         SimpleDateFormat simple_date_format = new SimpleDateFormat("yyyy-MM-dd");
	         Date fm_check_in = simple_date_format.parse(check_in); // fm_check_in : simple_date_format_check_in
	         Date fm_check_out = simple_date_format.parse(check_out); // fm_check_out : simple_date_format_check_out
	         
	         cal_date = fm_check_out.getTime() - fm_check_in.getTime();
	         cal_date_days = cal_date / (24*60*60*1000);
	         cal_date_days = Math.abs(cal_date_days);
	         logger.info("두 날짜의 차이 : " + cal_date_days);
	    }
		model.addAttribute("cal_date_days", cal_date_days);
	} // end detailGet()
	// '호텔상세정보' 결재기능	
	@PostMapping("/paycheck")
	public void paycheckPost(Model model, Integer hotel_no, String check_in, String check_out,
			String room_no, String room_name, long total_price, HttpSession http_session) throws ParseException{
		logger.info("paycheckPost() 호출");
		logger.info("hotel_no : " + hotel_no + ", check_in : " + check_in + ", check_out : " + check_out + 
				", room_no : " + room_no + ", room_name : " + room_name + ", total_price : " + total_price);
		
		String user_id = (String) http_session.getAttribute("user_id");
		UserVO user_vo = user_service.readByUserId(user_id);
		model.addAttribute("user_vo", user_vo);
		HotelVO hotel_info = hotel_service.readHotelInfo(hotel_no);
		model.addAttribute("hotel_info", hotel_info);
		
		model.addAttribute("room_no", room_no);
		model.addAttribute("room_name", room_name);
		model.addAttribute("check_in", check_in);
		model.addAttribute("check_out", check_out);
		model.addAttribute("total_price", total_price);
	} // end paycheckPost()
}

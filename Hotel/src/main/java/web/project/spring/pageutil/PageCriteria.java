package web.project.spring.pageutil;

// 브라우저에서 보여질 페이지 번호와
// 한 페이지에서 보여질 게시글의 개수를 저장하는 클래스
// -> paging 처리에 필요한 start와 end 번호를 알 수 있음
public class PageCriteria {
	private int page; // 현재 페이지 번호
	private int numsPerPage; // 한 페이지의 게시글 개수
	private String keyword; // 검색 키워드
	private String urlKeyword; // url용 % 처리 안한 keyword 변수 (HotelController list 참조)
	private String check_in; // 검색 check_in
	private String check_out; // 검색 check_out
	private int person; // 검색 person
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 5;
		this.keyword = "";
		this.urlKeyword = "";
		this.check_in= "";
		this.check_out= "";
		this.person = 0;
	}

	public PageCriteria(int page, int numsPerPage, String keyword, String check_in, String check_out, int person) {
		this.page = page;
		this.numsPerPage = numsPerPage;
		this.keyword = keyword;
		this.check_in = check_in;
		this.check_out = check_out;
		this.person = person;
	}

	// getter/setter
	public String getKeyword() {return keyword;}
	public void setKeyword(String keyword) {this.keyword = keyword;}

	public String getUrlKeyword() {return urlKeyword;}
	public void setUrlKeyword(String urlKeyword) {this.urlKeyword = urlKeyword;}

	public String getCheck_in() {return check_in;}
	public void setCheck_in(String check_in) {this.check_in = check_in;}

	public String getCheck_out() {return check_out;}
	public void setCheck_out(String check_out) {this.check_out = check_out;}

	public int getPerson() {return person;}
	public void setPerson(int person) {this.person = person;}

	public int getPage() {return page;}
	public void setPage(int page) {this.page = page;}

	public int getNumsPerPage() {return numsPerPage;}
	public void setNumsPerPage(int numsPerPage) {this.numsPerPage = numsPerPage;}
	
	// 현재 보여지는 페이지의 시작 글 일련번호(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// 현재 보여지는 페이지의 마지막 글 일련번호(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
}

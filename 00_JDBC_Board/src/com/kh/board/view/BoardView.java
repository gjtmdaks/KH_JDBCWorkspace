package com.kh.board.view;

import java.util.List;
import java.util.Scanner;

import com.kh.board.controller.BoardController;
import com.kh.board.model.vo.Board;

public class BoardView {
	// 입력을 위한 Scanner변수 선언 및 초기화
	// 기능 실행을 위한 BoardController 변수 선언 및 초기화.
	Scanner sc = new Scanner(System.in);
	BoardController bc = new BoardController();
	String memberId = null;
	/**
	 * 로그인 기능.
	 * 사용자에게 로그인을 할 계정의 ID와, PWD를 입력받고 로그인 요청을 보내는 메소드
	 * 로그인 성공시 mainMenu를 호출, 로그인 실패시 ID,PWD를 다시 입력받음.
	 * 로그인 성공시 memberId에 사용자의 id를 저장.
	 *  */
	public void login() {
		System.out.println("### 게시판 서비스###");
		System.out.println("서비스 이용을 위해 로그인을 진행해주세요.");
		while(true) {
			System.out.print("ID : ");
			String id = sc.nextLine();
			System.out.print("PWD : ");	
			String pwd = sc.nextLine();	
			
			if(bc.login(id, pwd)) {
				memberId = id;
				mainMenu();
				break;
			}
		}
	}
	
	/**
	 * 메인메뉴
	 * 사용자에게 서비스중인 기능목록을 보여준후 , 정수형태로 서비스 번호를 입력받아 원하는 서비스를 제공해주는
	 * 메소드.
	 *  */
	public void mainMenu() {
		
		while(true) {
			System.out.println("### 게시판 서비스 ###");
			System.out.println("1. 게시판 목록 보기 ");
			System.out.println("2. 게시판 상세보기");
			System.out.println("3. 게시판 등록하기");
			System.out.println("4. 게시판 수정하기");
			System.out.println("5. 게시판 삭제하기");			
			System.out.println("9. 끝");
			System.out.print("이용할 메뉴 선택 : ");
			int menu = Integer.parseInt(sc.nextLine());
			
			switch(menu) {
			case 1: selectBoardList(); break;
			case 2: selectBoard(); break;
			case 3: insertBoard(); break;
			case 4: updateBoard(); break;
			case 5: deleteBoard(); break;
			case 9: System.out.println("프로그램을 종료합니다.."); return;
			default: System.out.println("잘못된 메뉴를 선택했습니다. 다시입력해주세요.");
			}
		}
	}
	/** 
	 * 게시글 목록 조회 메서드
	 * 게시판에 존재하는 게시글 정보를 모두 출력하는 메서드.
	 * 게시글의 번호, 제목, 작성자, 작성시간 출력
	 * */
	public void selectBoardList() {
		System.out.println("게시글 번호\t게시글 제목\t작성자\t작성시간");
		
		System.out.println("### 게시글 목록 조회 ###");
		bc.selectBoardList();
	}
	
	/** 
	 * 게시글 상세 조회 메서드
	 * 사용자로 하여금 게시글 번호를 입력받아 게시글 정보를 요청 한 후,
	 * 전달 받은 게시글정보를 출력하는 메소드.
	 * 
	 * */
	public void selectBoard() {
		System.out.println("### 게시글 상세 조회 ###");
		System.out.print("게시글 번호 : ");
		int bno = Integer.parseInt(sc.nextLine());
		
		System.out.println("게시글 내용");
		bc.selectBoard(bno);
	}
	
	/** 
	 * 게시글 등록 메서드
	 * 사용자로 하여금 게시글 제목과, 내용을 입력받아 게시글을 등록요청을 보내는 메소드
	 * */
	public void insertBoard() {
		System.out.println("### 게시글 등록 ###");
		System.out.print("게시글 제목 : ");
		String title = sc.nextLine();
		System.out.println("게시글 내용");
		System.out.print("=> ");
		String content = sc.nextLine();
		
		bc.insertBoard(memberId, title, content);
	}
	
	/** 
	 * 게시글 수정 메소드
	 * 사용자로 하여금 수정할 게시글 제목 번호과 내용을 입력받아 게시글 수정요청을 보내는 메소드
	 * */
	public void updateBoard() {
		System.out.println("### 게시글 수정 ###");
		System.out.print("수정할 게시글 번호 : ");
		int bno = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.println("수정할 내용");
		System.out.print("=> ");
		String content = sc.nextLine();
		
		bc.updateBoard(memberId, bno, title, content);
	}
	
	/** 
	 * 게시글 삭제 메소드
	 * 사용자로 하여금 삭제할 게시글 번호를 입력받아 게시글 삭제 요청을 보내는 메소드
	 * */
	public void deleteBoard() {
		System.out.println("### 게시글 삭제 ###");
		System.out.print("삭제할 게시글 번호 : ");
		int bno = Integer.parseInt(sc.nextLine());
		
		bc.deleteBoard(memberId, bno);
	}
	
	// -- 서비스 요청 처리후 사용자가 보게될 응답 View
	public void displaySuccess(String message) {
		System.out.println(message);
	}

	public void displayFail(String message) {
		System.out.println(message);
	}

	public void displayNodata(String message) {

		System.out.println(message);
	}

	public void displayList(List<Board> list) {

		System.out.println("\n조회된 데이터는 " + list.size() + "건 입니다.\n");
		for(Board b : list) {
	        System.out.printf("%d\t%s\t%s\t%s\n",
	            b.getBno(),
	            b.getTitle(),
	            b.getWriter(),
	            b.getCreate_date()
	        );
	    }
	}
	
	public void displayOne(Board b) {

		System.out.println("번호 : " + b.getBno());
	    System.out.println("제목 : " + b.getTitle());
	    System.out.println("작성자 : " + b.getWriter());
	    System.out.println("작성일 : " + b.getCreate_date());
	    System.out.println("내용");
	    System.out.println("=> "+b.getContent());
	}
}

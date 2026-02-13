package com.kh.board.controller;

import java.util.List;

import com.kh.board.model.service.BoardServiceImpl;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Member;
import com.kh.board.view.BoardView;
import com.kh.mvc.view.MemberView;

/* 
 * View요청에 맞는 Service를 선택하여 메서드를 실행 한 후 결과값을 돌려주는 클래스.
 * */
public class BoardController {
	
	// service 변수 선언 및 초기화
	BoardServiceImpl bs = new BoardServiceImpl();

	// view의 login요청을 담당할 메서드
	public boolean login(String id, String pwd) {
		int result = bs.login(id, pwd);
		
		if(result > 0) {
			new BoardView().displaySuccess("로그인 성공");
			return true;
		}else {
			new BoardView().displayFail("로그인에 실패했습니다. 다시 입력해주세요.");
			return false;
		}
	}

	// view의 selectBoardList요청을 담당할 메서드
	public void selectBoardList() {
		List<Board> list = bs.selectBoardList();
		
		if(list.isEmpty()) {
			new BoardView().displayNodata("전체 조회 결과가 없습니다.");
		}else {
			new BoardView().displayList(list);
		}
	}

	// view의 selectBoard요청을 담당할 메서드
	public void selectBoard(int num) {
		Board b = bs.selectBoard(num);
		
		if(b == null) {
			new BoardView().displayNodata("전체 조회 결과가 없습니다.");
		}else {
			new BoardView().displayOne(b);
		}
	}

	// view의 insertBoard요청을 담당할 메서드
	public void insertBoard(String memberId, String title, String content) {
		Board b = new Board();
		b.setMemberId(memberId);
		b.setTitle(title);
		b.setContent(content);

		int result = bs.insertBoard(b);
		
		if(result > 0) {
			new BoardView().displaySuccess("게시글 등록 성공");
		}else {
			new BoardView().displayFail("게시글 등록 실패");
		}
	}

	// view의 updateBoard요청을 담당할 메서드
	public void updateBoard(String memberId, int num, String content) {
		Board b = new Board();
		b.setMemberId(memberId);
		b.setContent(content);

		int result = bs.updateBoard(num, b);
		
		if(result > 0) {
			new BoardView().displaySuccess("게시글 수정 성공");
		}else {
			new BoardView().displayFail("게시글 수정 실패");
		}
	}

	// view의 deleteBoard요청을 담당할 메서드
	public void deleteBoard(String memberId, int num) {
		int result = bs.deleteBoard(num);
		
		if(result > 0) {
			new BoardView().displaySuccess("게시글 삭제 성공");
		}else {
			new BoardView().displayFail("게시글 삭제 실패");
		}
	}
}

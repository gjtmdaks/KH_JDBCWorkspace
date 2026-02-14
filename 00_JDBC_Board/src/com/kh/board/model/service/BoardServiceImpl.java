package com.kh.board.model.service;

import static com.kh.board.model.template.JDBCTemplate.close;
import static com.kh.board.model.template.JDBCTemplate.commit;
import static com.kh.board.model.template.JDBCTemplate.getConnection;
import static com.kh.board.model.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Member;

// BoardService 인터페이스를 구현하는 클래스.
// 각 메서드의 설명에 맞게 기능을 작성.
public class BoardServiceImpl implements BoardService{
	BoardDao bDao = new BoardDao();

	@Override
	public int login(String memberId, String memberPwd) {
		Connection conn = getConnection();
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPwd(memberPwd);
		
		List<Member> list = bDao.login(conn, m);
		close(conn);
		
		int result = list.size();
		
		return result;
	}

	@Override
	public List<Board> selectBoardList() {
		Connection conn = getConnection();
		List<Board> list = bDao.selectBoardList(conn);
		close(conn);
		
		return list;
	}

	@Override
	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		Board b = bDao.selectBoard(conn, boardNo);
		close(conn);
		
		return b;
	}

	@Override
	public int insertBoard(String memberId, Board b) {
		Connection conn = getConnection();
		int result = bDao.insertBoard(conn, memberId, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	@Override
	public int updateBoard(String memberId, Board b) {
		Connection conn = getConnection();
		int result = bDao.updateBoard(conn, memberId, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	@Override
	public int deleteBoard(String memberId, int boardNo) {
		Connection conn = getConnection();
		int result = bDao.deleteBoard(conn, memberId, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}

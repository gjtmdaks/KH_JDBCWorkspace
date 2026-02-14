package com.kh.board.model.dao;

import static com.kh.board.model.template.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Member;

/** 
 * Service의 요청에 맞는 sql문을 실행할 클래스.
 * 단, sql문은 resources/query.xml에 보관/관리한다.
 * */
public class BoardDao {

	public List<Member> login(Connection conn, Member m) {
		// member테이블에 전달받은 m id, pwd가 존재하는지 select
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member mem = new Member();
				
				mem.setMemberId(rset.getString("MEMBER_ID"));
				mem.setMemberName(rset.getString("MEMBER_NAME"));
				mem.setPhone(rset.getString("PHONE"));
				mem.setAge(rset.getInt("AGE"));
				mem.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Board> selectBoardList(Connection conn) {
		// board테이블의 모든 정보를 select
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM BOARD";

		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreate_date(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDelete_yn(rset.getString("DELETE_YN").charAt(0));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Board selectBoard(Connection conn, int boardNo) {
		// board테이블의 where bno = boardNo 정보를 select
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM BOARD WHERE BNO = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreate_date(rset.getDate("CREATE_DATE"));
				b.setWriter(rset.getString("WRITER"));
				b.setDelete_yn(rset.getString("DELETE_YN").charAt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public int insertBoard(Connection conn, String memberId, Board b) {
		// board에 bno = SEQ_BOARD, title, content, writer를 insert(나머진 default)
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		int result = 0;
		String sql = "INSERT INTO BOARD VALUES(SEQ_BOARD.NEXTVAL,?,?,DEFAULT,?,DEFAULT)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, memberId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection conn, String memberId, Board b) {
		// board에 where bno = bno, writer = memberId으로 title, content를 update
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		int result = 0;
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BNO = ? AND WRITER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getBno());
			pstmt.setString(4, memberId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, String memberId, int boardNo) {
		// board에 where bno = bno, writer = memberId으로 delete
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		int result = 0;
		String sql = "DELETE FROM BOARD WHERE BNO = ? AND WRITER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}

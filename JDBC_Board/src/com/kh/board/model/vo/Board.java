package com.kh.board.model.vo;

import java.sql.Date;

/**
 * Board 테이블의 한행의 정보를 보관할 클래스
 */
public class Board {
	private int bno;
	private String title;
	private String content;
	private Date create_date;
	private String writer;
	private char delete_yn;
	
	public Board() {
		super();
	}

	public Board(int bno, String title, String content, Date create_date, String writer, char delete_yn) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.create_date = create_date;
		this.writer = writer;
		this.delete_yn = delete_yn;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public char getDelete_yn() {
		return delete_yn;
	}

	public void setDelete_yn(char delete_yn) {
		this.delete_yn = delete_yn;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", create_date=" + create_date
				+ ", writer=" + writer + ", delete_yn=" + delete_yn + "]";
	}
}

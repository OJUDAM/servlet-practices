package com.bit2020.guestbook01.dao.test;

import java.util.List;

import com.bit2020.guestbook01.dao.guestbookDao.GuestBookDao;
import com.bit2020.guestbook01.vo.guestbookvo.GuestBookVo;

public class GuestBookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testInsert();
		testSelectList();
	}
	public static void testSelectList() {
		List<GuestBookVo> list = new GuestBookDao().findAll();
		
		if(list.size() == 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
	public static void testInsert() {
		GuestBookVo vo = new GuestBookVo();
		vo.setMessage("sdfsdf");
		vo.setName("ohoh");
		vo.setPassword("123123");
		
		boolean result = new GuestBookDao().insert(vo);
		if(result) {
			System.out.println("성");
		}else {
			System.out.println("실");
		}
	}
}

package com.bit2020.guestbook01.dao.guestbookDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2020.guestbook01.vo.guestbookvo.GuestBookVo;

public class GuestBookDao {
	
	public boolean insert(GuestBookVo vo) {
		boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		try {
			// 1. JDBC Driver(MariaDB Driver)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";

			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. Statement 객체 생성 sql 실행하기 위해서
			stmt =  connection.createStatement();
			
			// 4. SQL 실행
			//String sql = "insert into guestbook values(null, '"+vo.getFirstName()+"','"+vo.getLastName()+"','"+vo.getEmail()+"')";			
			String sql1 = "insert into guestbook values(null,'"+vo.getName()+"','"+vo.getPassword()+"',now(),'"+vo.getMessage()+"')";
			int count = stmt.executeUpdate(sql1);
			result =(count == 1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	public List<GuestBookVo> findAll(){
		List<GuestBookVo> result = new ArrayList<>();
		ResultSet rs = null;
		Connection connection = null;
		Statement stmt = null;
		try {
			// 1. JDBC Driver(MariaDB Driver)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";

			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. Statement 객체 생성 sql 실행하기 위해서
			stmt =  connection.createStatement();
			
			// 4. SQL 실행
			String sql = "select no, name, message, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook";
					
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String regDate = rs.getString(4);
				System.out.println(no);
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);;
				vo.setRegDate(regDate);;
				
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}

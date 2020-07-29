package com.bit2020.emaillist02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit2020.emaillist02.vo.EmaillistVo;

public class EmaillistDao {
	
	public boolean delete(long no, String lastName) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			String sql = "delete from emaillist where no = ? and last_name = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setString(2, lastName);
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("SQL delete 에러 : "+e);
		}
		
		
		return result;
	}
	public boolean insert(EmaillistVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			// 연결
			connection = getConnection();
			
			//준비
			String sql = "insert into emaillist values(null, ?,?,?)";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1,vo.getFirstName());
			pstmt.setString(2,vo.getLastName());
			pstmt.setString(3,vo.getEmail());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("SQL 에러 : "+e);
		}
		return result;
	}
	public List<EmaillistVo> findAll(){
		List<EmaillistVo> list= new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 연결
			connection = getConnection();
			
			//준비
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo vo = new EmaillistVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo);
			}
		
		} catch (SQLException e) {
			System.out.println("SQL 에러 : "+e);
		}
		return list;
	}
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			// 1. JDBC Driver(MariaDB Driver)
			Class.forName("org.mariadb.jdbc.Driver");
	
			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		
		return connection;
	}
}

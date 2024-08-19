package com.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guestbook.vo.guestbookVo;

public class guestbookDao {
	// 필드
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;

		private String driver = "com.mysql.cj.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/guestbook_db";
		private String id = "guestbook";
		private String pw = "guestbook";
		
		//생성자
		//기본생성자 사용(그래서 생략)  
		
		//메소드 gs
		//필드값을 외부에서 사용하면 안됨(그래서 생략)
		
		//메소드 일반
		// DB연결 메소드
		private void getConnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		// 자원정리 메소드
		private void close() {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		public int registerInfo(guestbookVo guest) {
			int count = -1;
			
			this.getConnection();
			
			try {
				String query = "";
				query += " insert into guest ";
				query += " values(null,?,?,?) ";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1 , guest.getName());
				pstmt.setString(2, guest.getPassword());
				pstmt.setString(3, guest.getComments());
				
				count = pstmt.executeUpdate();
				
			}catch (SQLException e ) {
				System.out.println("Errors: " + e);
			}
			
			this.close();
			return count;
		}
		
		public int deletePerson(int no){
			int count = -1;
			
			this.getConnection();
			
			try {
				//*SQL문 준비
				String query ="";
				query += " delete from guest ";
				query += " where person_id = ? ";
				
				//*바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				
				
				//*실행
				count = pstmt.executeUpdate();
				
				// 4.결과처리
				System.out.println(count+ "건 삭제");
				
			}catch(SQLException e) {
				System.out.println("error:" + e);
			}
			this.close();
			
			
			return count;
		}
		
		public guestbookVo getPersonInfo(int no) {
			guestbookVo guest1 = null;
			this.getConnection();
			
			try {
				String query = "";
				query += " select 	person_id, ";
				query += "		    name, ";
				query += "          pw, ";
				query += "          comments ";
				query += " from guest ";
				query += " where person_id = ? ";
				
				//*바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				
				//*실행
				rs = pstmt.executeQuery();
				
				// 4.결과처리
				rs.next();
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String comments = rs.getString("comments");
				
				guest1 = new guestbookVo(personId, name, pw, comments);
				
			}catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			
			return guest1;
			
		}
		
		public List<guestbookVo> getPersonList() {
			List<guestbookVo> guestList = new ArrayList<guestbookVo>();
			
			this.getConnection();
			
			try {
				String query = "";
				query += " select 	person_id, ";
			    query += "		    name, ";
			    query += "          pw, ";
			    query += "          comments ";
			    query += " from guest ";
			    
			    pstmt = conn.prepareStatement(query);
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	int id = rs.getInt("person_id");
			    	String name = rs.getString("name");
			    	String pw = rs.getString("pw");
			    	String comments = rs.getString("comments");
			    	
			    	guestbookVo personVo = new guestbookVo(id, name, pw, comments);
			    	
			    	guestList.add(personVo);
			    }
			}catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			this.close();
			
			return guestList;
		}
		
		public boolean checkPw(int no,String pw) {
			this.getConnection();
			try {
				String query = "";
				query += " select 	person_id, ";
			    query += "		    name, ";
			    query += "          comments ";
			    query += " from guest where pw = ? and person_id = ? ";
			    
			    pstmt = conn.prepareStatement(query);
			
			    pstmt.setString(1, pw);
			    pstmt.setInt(2, no);
			    rs = pstmt.executeQuery();
			    
			    if(rs.next()) {
			    	return true;
			    }
			    
			    
				
			    
			    
			}catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			this.close();
			return false;
			
		}
		
		
		
		
}

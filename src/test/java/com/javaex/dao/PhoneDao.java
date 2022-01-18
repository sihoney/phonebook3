package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.PersonVo;

public class PhoneDao {

	/////////////////////
	// field
	/////////////////////
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	Connection conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/////////////////////
	// constructor
	/////////////////////
	public PhoneDao() {
		
	}
	
	/////////////////////
	// method
	/////////////////////
	
	public void getConnection() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "phonedb", "phonedb");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void close() {
		try {

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
	
	public void insertPerson(PersonVo pvo) {
		
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pvo.getName());
			pstmt.setString(2, pvo.getHp());
			pstmt.setString(3, pvo.getCompany());
			
			int count = pstmt.executeUpdate();
			
	        /*****************
			 4.결과처리
			******************/
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
	}
	
	public ArrayList<PersonVo> selectPerson(){	
		
		ArrayList<PersonVo> list = new ArrayList<>();
		this.getConnection();
		
		try {
	         /*****************************
	          3. SQL문 준비 / 바인딩 / 실행
	         ******************************/
			String query = "";
			query += " select person_id, name, hp, company ";
			query += " from person ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
	         /******************
	         4.결과처리
	         *******************/
			while(rs.next()) {
				int personId = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);
				
				PersonVo pvo = new PersonVo(personId, name, hp, company);
				list.add(pvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		return list;
	}
	
	public void updatePerson(PersonVo pvo) {
		
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += " 	   company = ? ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pvo.getName());
			pstmt.setString(2, pvo.getHp());
			pstmt.setString(3, pvo.getCompany());
			pstmt.setInt(4, pvo.getPersonId());
			
			int count = pstmt.executeUpdate();
			
	        /*****************
			 4.결과처리
			******************/
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
	}
	
	public void deletePerson(int personId) {
		
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, personId);
			
			int count = pstmt.executeUpdate();
			
	        /*****************
			 4.결과처리
			******************/
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
	}
	
	public ArrayList<PersonVo> searchPerson(String search) {
		
		search = "%" + search + "%";
		
		ArrayList<PersonVo> list = new ArrayList<>();
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " select person_id, name, hp, company ";
			query += " from person ";
			query += " where (name like ? or ";
			query += "        hp like ? or ";
			query += "        company like ?) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			
			rs = pstmt.executeQuery();
			
	        /*****************
			 4.결과처리
			******************/
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				list.add(new PersonVo(personId, name, hp, company));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		return list;
	}
	
	public PersonVo getPerson(int personId) {
		
		PersonVo pvo = null;
		this.getConnection();
		
		try {
	         /*****************************
	          3. SQL문 준비 / 바인딩 / 실행
	         ******************************/
			String query = "";
			query += " select person_id, name, hp, company ";
			query += " from person ";
			query += " where person_id=? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, personId);
			
			rs = pstmt.executeQuery();
			
	         /******************
	         4.결과처리
	         *******************/
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);
				
				pvo = new PersonVo(id, name, hp, company);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return pvo;
	}
}

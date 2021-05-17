package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.JDBC_Close;


//DAO : data access object / Database Access Object
//데이터베이스 (DB) 와 연동해서  CRUD를 구현하는 클래스
public class MemberDao {
	private final String DRIVER = "oracle.jdbc.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mytrip";
	private final String PASSWORD = "mytrip";
	
//	private final String URL = "jdbc:oracle:thin:@192.168.0.27:1521:xe";
//	private final String USER = "trip";
//	private final String PASSWORD = "trippw";		
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int num;
	
	public MemberDao() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//SELECT : 테이블 전체 데이터 조회
	public List<MemberVo> selectAll() {
	      List<MemberVo> list = null;
	      
	      //DB 연결 - Connection 객체 생성 (DB와 연결된)
	      try {
	         conn = DriverManager.getConnection(URL,USER,PASSWORD);
	         //SQL문 실행
	         StringBuilder sb = new StringBuilder ();
	         sb.append("SELECT ID, NAME, PW, AGE, EMAIL, PHONE ");
	         sb.append("  FROM MEMBER ");
	         sb.append("ORDER BY ID ");
	         pstmt = conn.prepareStatement(sb.toString());
	         
	         rs = pstmt.executeQuery();
	         
	         //SQL문 실행 결과에 대한 처리
	         list = new ArrayList<MemberVo>();
	         while (rs.next()) {
	            MemberVo mvo = new MemberVo (
	                  rs.getString("ID"),
	                  rs.getString("NAME"),
	                  rs.getString("PW"),
	                  rs.getInt("AGE"),
	                  rs.getString("EMAIL"),
	                  rs.getString("PHONE"));
	            list.add(mvo);
	         }
	         
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      }finally {
	         JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
	         }
	      
	      
	      return list;
	   }
	
//--------------가입 정보확인-----------------------------
	public MemberVo selectOne(String id, String pw) {
	      MemberVo mvo = null;
	      
	      try {
	         conn = DriverManager.getConnection(URL,USER,PASSWORD);
	         //SQL문 실행
	         StringBuilder sb = new StringBuilder ();
	         sb.append("SELECT ID, NAME, PW, AGE, EMAIL, PHONE ");
	         sb.append("  FROM MEMBER ");
	         sb.append("WHERE ID = ? AND PW = ? ");
	         
	         pstmt = conn.prepareStatement(sb.toString());
	         //? 위치에 값 설정
	         pstmt.setString(1,id);
	         pstmt.setString(2,pw);
	         rs = pstmt.executeQuery();
	         
	         //SQL문 실행 결과에 대한 처리
	         
	        	 if (rs.next()) {
	            //MemberVO에 데이터 저장
	            mvo = new MemberVo(rs.getString("ID"),rs.getString("NAME"),
	                     rs.getString("PW"),rs.getInt("AGE"), rs.getString("EMAIL"), rs.getString("PHONE"));
	         }
	         
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      }finally {
	         JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
	      }
	      
	      return mvo;
	   }
	   
//-------------------로그인-----------------------
	   public int selectTwo(MemberVo loginDto) {
	      
	      
	      try {
	         conn = DriverManager.getConnection(URL,USER,PASSWORD);
	         //SQL문 실행
	         StringBuilder sb = new StringBuilder ();
	         sb.append("SELECT ID, NAME, PW, AGE, EMAIL, PHONE ");
	         sb.append("  FROM MEMBER ");
	         sb.append("WHERE ID = ? ");
	         
	         pstmt = conn.prepareStatement(sb.toString());
	         //? 위치에 값 설정
	         pstmt.setString(1,loginDto.getId());
	         rs = pstmt.executeQuery();
	         
	         //SQL문 실행 결과에 대한 처리
	         while (rs.next()) {
	                if (rs.getString(1).contentEquals(loginDto.getId())&& rs.getString(3).contentEquals(loginDto.getPw())) {
	                   return 1; // 로그인 성공
	                } else  {
	                   return  2; // 비밀번호 불일치
	               }
	            } return 3;
	             
	             
	             
	          } catch (Exception e) {
	             e.printStackTrace();
	          } finally {
	             JDBC_Close.closeConnStmt(conn, pstmt);
	          }
	      
	      return 0;
	      
	       
	   }

//SELECT : 1개의 데이터 조회(VO) - selectOne : MemberVO
	      public MemberVo select(MemberVo member) {
	         return selectOne(member.getId(), member.getPw());
	      }
	      
//INSERT : VO 객체를 받아서 입력 - insertOne : int
	      public int insertOne (MemberVo member) {
	         int result = 0;
	         try {
	            conn = DriverManager.getConnection(URL,USER,PASSWORD);
	            //SQL문 실행
	            StringBuilder sb = new StringBuilder();
	            sb.append("INSERT INTO MEMBER");
	            sb.append("   (SELECT ID, NAME, PW, AGE, EMAIL, PHONE)");
	            sb.append("VALUES(?,?,?,?,?,?)");
	            pstmt = conn.prepareStatement(sb.toString());
	            //바인드 변수에 값 설정
	            pstmt.setString(1, member.getId());
	            pstmt.setString(2, member.getName());
	            pstmt.setString(3, member.getPw());
	            pstmt.setInt(4, member.getAge());
	            pstmt.setString(5, member.getEmail());
	            pstmt.setString(6, member.getPhone());
	            //SQL문 실행 
	            result = pstmt.executeUpdate();
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }finally {
	            JDBC_Close.closeConnStmt(conn, pstmt);
	         }
	         
	         return result;
	      
	      }
	      
//update : vo객체를 받아서 수정 - updateOne : int
	      public int updateOne(MemberVo member) {
	         int result = 0;
	         
	         try {
	            //DB 연결 - Connection 객체 생성(DB와 연결된)
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            
	            //SQL문 실행
	            StringBuilder sb = new StringBuilder();
	            sb.append("UPDATE MEMBER ");
	            sb.append("   SET NAME = ?, "); //1
	            sb.append("       PW = ?, ");     //2
	            sb.append("       AGE = ?, ");    //3
	            sb.append("       EMAIL = ? ");   //4
	            sb.append("        PHONE");      //5
	            sb.append(" WHERE ID = ? ");        //6
	            pstmt = conn.prepareStatement(sb.toString());
	            
	            //? 바인드변수에 값 설정
	            int i = 1;
	            pstmt.setString(i++, member.getId());
	            pstmt.setString(i++, member.getName());
	            pstmt.setString(i++, member.getPw());
	            pstmt.setInt(i++, member.getAge());
	            pstmt.setString(i++, member.getEmail());
	            pstmt.setString(i++, member.getPhone());
	            
	            //SQL문 실행(INSERT, UPDATE, DELETE - executeUpdate())
	            result = pstmt.executeUpdate();
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            JDBC_Close.closeConnStmt(conn, pstmt);
	         }
	         return result;
	         
	      }
	      
//---------------------회원 가입---------------------------------
	      public int insert (MemberVo member) {
	         int result = 0;
	         try {
	            conn = DriverManager.getConnection(URL,USER,PASSWORD);
	            //SQL문 실행
	            StringBuilder sb = new StringBuilder();
	            sb.append("INSERT INTO MEMBER");
	            sb.append("   ( ID, NAME, PW, AGE, EMAIL, PHONE )");
	            sb.append(" VALUES(?,?,?,?,?,?)");
	            pstmt = conn.prepareStatement(sb.toString());
	            //바인드 변수에 값 설정
	            pstmt.setString(1, member.getId());
	            pstmt.setString(2, member.getName());
	            pstmt.setString(3, member.getPw());
	            pstmt.setInt(4, member.getAge());
	            pstmt.setString(5, member.getEmail());
	            pstmt.setString(6, member.getPhone());
	            //SQL문 실행 
	            result = pstmt.executeUpdate();
	            System.out.println("──────────────────────────────────────────────────────");
	                System.out.println("*** 회원가입 완료 ***");
	                System.out.println("──────────────────────────────────────────────────────");
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }finally {
	            JDBC_Close.closeConnStmt(conn, pstmt);
	         }	         
	         return result;	      
	      }
	}
package bulletinBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Member.MMain_First;


public class Board_DAO {
	//디비 연결
	private Connection conn;
	public Board_DAO() {
		
		//내거
		String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String USER = "mytrip";
		String PASSWORD = "mytrip";
		
		//유현씨랑 연결
//		String URL = "jdbc:oracle:thin:@192.168.0.27:1521:xe";
//		String USER = "trip";
//		String PASSWORD = "trippw";
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD); 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
//--------------글쓰기--------------------------------
	 public void insert(BoardVO bv) {

	      String sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.nextval,?,?,TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'),?)";
	      try {
	         PreparedStatement ptm = conn.prepareStatement(sql);
	         MMain_First mmain = new MMain_First();
	         String id = mmain.send();
	         ptm.setString(1, bv.getTitle());
	         ptm.setString(2, bv.getContent()); 
	         ptm.setString(3, id); 
	         
	         ptm.executeUpdate();
	         ptm.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }      
	      
	   }

//-----------글 번호순으로 조회-----------------------
	   public void selectAll() {
	      String sql = "SELECT * FROM BOARD ORDER BY NO";
	      //정적쿼리
	      try {
	         Statement stm = conn.createStatement();
	         ResultSet rs = stm.executeQuery(sql);
	         while(rs.next()) { //항목들 여러개일 경우 계속해서 출력
	            System.out.println(rs.getString(1)+", "+
	                           rs.getString(2)+", "+
	                           rs.getString(3)+", "+
	                           rs.getString(4)+", "+
	                           rs.getString(5)); 
	         }
	         rs.close();
	         stm.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
//-------------------내용으로 검색----------------------------------
	   public List<BoardVO> selectAll_sc(String content) {
	        List<BoardVO> listsc = new ArrayList<>(); //수정       
	        PreparedStatement ptm;
	        try {
	          StringBuilder sb = new StringBuilder();
	          sb.append("SELECT * FROM BOARD WHERE CONTENT like '%'|| ? || '%' ORDER BY NO");
	           ptm = conn.prepareStatement(sb.toString());
	           ptm.setString(1, content);
	           ResultSet rs = ptm.executeQuery();
	           
	           listsc = new ArrayList<BoardVO>();   
	           while (rs.next()) {  
	        	   
	        	  BoardVO vo = null;
	        	  vo = new BoardVO(rs.getInt("no"), rs.getString("title"), rs.getString("content"));
	        	  //setContent는 vo에서 getter setter생성한 이름 
	              //"content"는 컬럼명
	              listsc.add(vo);
	           }
	           rs.close();
	           ptm.close();
	        } catch (SQLException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	        }
	        return listsc;      
	     }

	   
//-----------------글 수정--------------------------------
	   public void update(int no, String title, String content) {
	      String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
	      try {
	         PreparedStatement ptm = conn.prepareStatement(sql);
	         ptm.setString(1, title); //제목
	         ptm.setString(2, content); //내용
	         ptm.setInt(3, no); //글번호
	         ptm.executeUpdate();
	         ptm.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }      
	      }
	   
//------------------글 삭제---------------------------------
	   public void delete(int no) {
	      String sql = "DELETE FROM BOARD WHERE NO = ?";
	      try {
	         PreparedStatement ptm = conn.prepareStatement(sql);
	         ptm.setInt(1, no);
	         
	         ptm.executeUpdate();
	         ptm.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	   }   

//----------------내가 쓴 글 조회-------------------
	   public void selectone(String id) {
	      String sql = "SELECT * FROM BOARD WHERE ID = ?";
	      try {
	         PreparedStatement ptm = conn.prepareStatement(sql);
	         ptm.setString(1, id);
	         ResultSet rs = ptm.executeQuery();
	         BoardVO bvo = null;
	         
	          while(rs.next()) {
	            bvo = new BoardVO(rs.getString("ID"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getInt("NO"), rs.getString("REGDATE"));
	            System.out.println(bvo);
	         }
	         
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      } 
	      
	   }
	   
//-------------------글 수정 완료 후 조회------------------------
	   public void select(int No) {
	      String sql = "SELECT * FROM BOARD WHERE NO= ?";
	      try {
	         PreparedStatement ptm = conn.prepareStatement(sql);
	         ptm.setInt(1, No);
	         ResultSet rs = ptm.executeQuery();
	         BoardVO bvo = null;
	         if (rs.next()) {
	            bvo = new BoardVO(rs.getString("ID"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getInt("NO"), rs.getString("REGDATE"));
	            System.out.println(bvo);
	         }
	         
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	      }
	   }

}
package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotBoardDAO {
	private Connection conn;
	public HotBoardDAO() {
		
		//유현씨랑 연결
//		String URL = "jdbc:oracle:thin:@192.168.0.27:1521:xe";
//		String USER = "trip";
//		String PASSWORD = "trippw";
		
		//내거
		String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String USER = "mytrip";
		String PASSWORD = "mytrip";
		
		try {
	         Class.forName("oracle.jdbc.OracleDriver");
	         conn = DriverManager.getConnection(URL, USER, PASSWORD); 
	      } catch (ClassNotFoundException | SQLException e) {
	         e.printStackTrace();
	      }
	   }

	
//--------------------공지사항 조회-----------------------------
	    public List<HotBoardVO> selectAll_Hot(){
	       List<HotBoardVO> listh = null;
	       listh = new ArrayList<HotBoardVO>();
	       PreparedStatement pstm;
	      try {
	         StringBuilder sb = new StringBuilder();
	         sb.append("SELECT * FROM HOTBOARD ORDER BY HOT_NUM");
	         pstm = conn.prepareStatement(sb.toString());
	         ResultSet rs = pstm.executeQuery();
	         
	         listh = new ArrayList<HotBoardVO>();
	         while(rs.next()) {
	            listh.add(new HotBoardVO(rs.getInt("HOT_NUM"), 
	                  rs.getString("HOT_TITLE"),
	                  rs.getString("HOT_REGDATE")));
	         }
	   
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } 
	      return listh;
		 
	 }
}

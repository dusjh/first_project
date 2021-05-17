package bulletinBoard;

import java.io.IOException;
import java.util.Scanner;

public class BCRUD_Third {
   public static void UpdateDelete() throws IOException {
      
      Scanner scan = new Scanner(System.in);
      BoardVO bv = new BoardVO(null, null, null, 0, null);
      Board_DAO db = new Board_DAO();
      
      while(true) {
         System.out.println("1.수정하기 | 2.삭제하기 | 3.뒤로가기");
         System.out.println("──────────────────────────────────────────────────────");
         System.out.print(">> ");
         int choice = scan.nextInt();
         
         if(choice == 1) {
            System.out.print("[글 번호] ");
            int no = scan.nextInt();
            scan.nextLine();
            System.out.print("[제목] ");   
            String title = scan.nextLine();   
            System.out.print("[내용] ");
            String content = scan.nextLine(); 
            db.update(no, title, content);
            System.out.println();
            System.out.println(" *** 수정완료 되었습니다. ***");
            System.out.println();
            db.select(no);
            
         }else if (choice == 2) {
            System.out.print("[글 번호] ");   
            int no = scan.nextInt();
            db.delete(no);
            System.out.println("*** 삭제되었습니다. ****");
            
         }else if (choice == 3) {
            BMain_Second bm = new BMain_Second();
            try {
				bm.BMain();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
            break;
             
         }else {
            System.out.println("메뉴에 있는 숫자만 입력해주세요.");
         }
         
      }          
   }   
}
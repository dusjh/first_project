package bulletinBoard;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Member.MMain_First;
import Member.MemberVo;
import function.HotBoardDAO;
import function.HotBoardVO;


public class BMain_Second {

   public static void BMain() throws Exception {   
 
      Scanner scan = new Scanner(System.in);
      BoardVO bv = new BoardVO(null, null, null, 0, null);
      Board_DAO bd = new Board_DAO();
      BCRUD_Third bc = new BCRUD_Third();
      HotBoardDAO hbd = new HotBoardDAO();
      
      while(true) {
         System.out.println("──────────────────────────────────────────────────────");
         System.out.println("               <<  홍콩여행 후기게시판  >>         ");
         System.out.println();
         System.out.println("1. 글쓰기 \n2. 내가 쓴 글 조회 \n3. 내용으로 검색 \n4. 공지사항 \n0. 로그아웃");
         System.out.println("──────────────────────────────────────────────────────");
         System.out.print(">> ");
         int choice = scan.nextInt(); //번호선택
         if(choice == 1) {
            
        	scan.nextLine();
            System.out.print("[제목] ");
            String title = scan.nextLine();
            bv.setTitle(title); //제목 입력받는 부분
            
            System.out.print("[내용] ");
            String content =  scan.nextLine();
            bv.setContent(content); //내용 입력받는 부분   
            bd.insert(bv);      
            
         } else if (choice == 2) {
            //System.out.print("[ID]");
            //String id = scan.next();
            MMain_First mmain = new MMain_First();
            String id = mmain.send();
            bd.selectone(id);
            
               try {
                  //bc.main(null);
            	   BCRUD_Third bt = new BCRUD_Third();
            	   bt.UpdateDelete();
                  break;
               } catch (Exception e) {
                  e.printStackTrace();
               }
                     
               } else if(choice == 3) {
                  System.out.print("[검색어] ");
                   String content = scan.next();
                   System.out.println();
                  
                   List<BoardVO> listsc = bd.selectAll_sc(content);
                   for(BoardVO bvo : listsc) {
                      System.out.print("[글 번호]"+bvo.getNo()+" |");  //글번호
                      System.out.print("[제목] "+bvo.getTitle()+" |");  //제목
                      System.out.print("[내용] "+bvo.getContent()+"\n");  //내용
                      System.out.println();                
            }

               } else if (choice == 4) {
                   System.out.println("               << 공지사항 >>           ");

                   List<HotBoardVO> list = hbd.selectAll_Hot();
                   for (HotBoardVO hbvo : list) {
                      System.out.print(hbvo.getHotNum() + "  ");
                      System.out.print(hbvo.getHotTitle());
                      System.out.println();
                   }

                   System.out.println();
                   System.out.println("[공유하기] 1) 네 | 2) 아니오");
                   System.out.print(">> ");
                   int choice2 = scan.nextInt();

                   if (choice2 == 1) {
                      System.out.println("*** 공유할 글 번호 선택 ***");
                      System.out.print(">> ");
                      int share = scan.nextInt();
                      
                      if(share >= 6) {
                         System.out.println("*** 해당 글 번호가 없습니다. ***");
                         System.out.println();
                      } else {
                         System.out.println();
                         System.out.println("*** 공유할 SNS 선택 ***");
                         System.out.print("1) Instagram | 2) FaceBook | 3) Naver Blog  | 4) KakaoTalk \n>> ");
                         int share2 = scan.nextInt();
                      
                         if(share2 > 0 && share2 < 5 ) {
                            System.out.println("***  공유되었습니다.  ***");
                         } else {
                            System.out.println("*** 지원하지 않는 SNS입니다. ***");
                         }   
                      
                      }
                         
                   } else if (choice2 > 2) {
                      System.out.println("***  메뉴에 있는 숫자만 입력해주세요.  ***");
                   }

                }else if(choice == 0) {              
                  break;
         } else {
            System.out.println("메뉴에 있는 숫자만 입력해주세요.");
         }

      }

   }

}
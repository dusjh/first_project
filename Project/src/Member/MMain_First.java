package Member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import bulletinBoard.BMain_Second;
import common.util.JDBC_Close;

public class MMain_First {
   
   static String id;
   static String pw;
   
   public String send() {
      return id;
   }
   
   public static void Member_First() {
      MemberDao dao = new MemberDao();
      MemberVo vo = new MemberVo(null, null);
      BMain_Second bm = new BMain_Second();
      
      Scanner scanner = new Scanner(System.in);         boolean runMain = true;

         while (runMain) {
            // 메인 메뉴
               System.out.println("──────────────────────────────────────────────────────");
               System.out.println("                  << 여행 정보공유 플랫폼 >>              ");
               System.out.println("                                                      ");
               System.out.println("           §§ 시그니처 홍콩 Signature HongKong §§        ");
               System.out.println("                                                      ");
               System.out.println("1. 로그인 \n2. 회원가입 \n3. 가입정보 확인 \n0. 종료");
               System.out.println("──────────────────────────────────────────────────────");
               System.out.print(">> ");

            int choice = scanner.nextInt();

            int num;
         switch (choice) {
            case 1:
               // 로그인
               scanner.nextLine();
                  System.out.println("로그인 정보를 입력해주세요.");
                  System.out.print("[Id] ");
                  id = scanner.nextLine();
                  System.out.print("[비밀번호] ");
                  pw = scanner.nextLine();
                  MemberVo loginDto = new MemberVo(id, pw);
               
              int num1 = dao.selectTwo(loginDto);
              
               boolean runSub = false;               
             
               if ( num1 == 1) {
                    System.out.println("──────────────────────────────────────────────────────");
                    System.out.println("***  로그인 성공  ***");
                    System.out.println("──────────────────────────────────────────────────────");
                    runSub = true;
                    
                  } else if (num1 == 2) {
                    System.out.println("──────────────────────────────────────────────────────");
                    System.out.println("*** 비밀번호가 틀렸습니다.  ***");
                    System.out.println("──────────────────────────────────────────────────────");
                 } else if (num1 == 3) {
                    System.out.println("──────────────────────────────────────────────────────");
                    System.out.println("*** 없는 아이디입니다.  ***");
                    System.out.println("──────────────────────────────────────────────────────");                 
                 }   
               
               boolean rinArtReplyItem = true;
               
               if (runSub) {
                    try {
                 BMain_Second BMain = new BMain_Second();
                 BMain.BMain();
                 //bm.main(null);
              } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
              } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
                    
                 }

               break;
               
            case 2 : 
               //회원 가입
               
               scanner.nextLine();
                 System.out.println("가입 정보를 입력해주세요.");
                 System.out.print("[Id] ");
                 String id1 = scanner.nextLine();
                 System.out.print("[이름] ");
                 String name = scanner.nextLine();
                 System.out.print("[비밀번호] ");
                 String pw1 = scanner.nextLine();
                 System.out.print("[나이] ");
                 int age = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("[이메일] ");
                 String email = scanner.nextLine();
                 System.out.print("[전화번호] ");
                 String phone = scanner.nextLine();
                 
                 MemberVo member = new MemberVo(id1, name, pw1, age, email, phone);
                 dao.insert(member);
                 break;
                  
            case 3:
                 
               System.out.print("Id: ");
               String id2 = scanner.next();
               
               System.out.print("비밀번호: ");
               String pw2 = scanner.next();
               
               MemberVo mvo = dao.selectOne(id2,pw2);
               if (mvo != null) {
                  System.out.println(mvo);
               } else {
                  System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
               }

                  break;
            case 0:
                  // 종료
                  System.out.println("*** 이용해주셔서 감사합니다. ***");
                  runMain = false;
                  return;
               }
            }
            
         }         
            
 }   
package main;

import java.io.IOException;

import Member.MMain_First;
import bulletinBoard.BCRUD_Third;
import bulletinBoard.BMain_Second;

//실행할 수 있는 메인창
public class Final_Main {

	public static void main(String[] args) {
		
		//로그인창
		MMain_First first = new MMain_First();
		first.Member_First(); 
		
		//게시판
		BMain_Second second = new BMain_Second();
		try {
			second.BMain();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//수정, 삭제
		BCRUD_Third third = new BCRUD_Third();
		try {
			third.UpdateDelete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package bulletinBoard;

public class BoardVO {
   
   private String id;
   private String title;
   private String content;
   private int no; //게시글 번호
   private String regDate; //글 등록날짜

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public int getNo() {
      return no;
   }

   public void setNo(int no) {
      this.no = no;
   }

   public String getRegDate() {
      return regDate;
   }

   public void setRegDate(String regDate) {
      this.regDate = regDate;
   }
   
   //키워드 검색할때 vo
   public BoardVO(int no, String title, String content) {
	super();
	this.no = no;
	this.title = title;
	this.content = content;
	
}

public BoardVO(String id, String title, String content, int no, String regDate) {
      super();
      this.id = id;
      this.title = title;
      this.content = content;
      this.no = no;
      this.regDate = regDate;
   }

   @Override
   public String toString() {
      String str = String.format("[제목] %s\n[내용] %s\n[글 번호] %s\n[작성일] %s\n", title, content, no, regDate);
      return str;
   }

}

   
   

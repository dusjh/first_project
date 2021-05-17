package Member;

public class MemberVo {
   private String id;
   private String name;
   private String pw;
   private int age;
   private String email;
   private String phone;
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public int getAge() {
      return age;
   }
   public void setAge(int age) {
      this.age = age;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhone() {
      return phone;
   }
   public void setNick_name(String phone) {
      this.phone = phone;
   }
   public MemberVo(String id, String name, String pw, int age, String email, String phone) {
      super();
      this.id = id;
      this.name = name;
      this.pw = pw;
      this.age = age;
      this.email = email;
      this.phone = phone;
   }
   public MemberVo(String id, String pw) {
      super();
      this.id = id;
      this.pw = pw;
   }
   @Override
   public String toString() {
      String str = String.format(" [id] %s\n [이름] "
               + "%s\n [비밀번호] %s\n [나이] %s\n "
               + "[이메일] %s\n [전화번호] %s\n",
               id,name,pw,age,email,phone);
      return str;
   }

}
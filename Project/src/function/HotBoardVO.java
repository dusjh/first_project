package function;

public class HotBoardVO {
   private int hotNum;
   private String hotTitle;
   private String hotRegdate;
   
   public int getHotNum() {
      return hotNum;
   }
   public void setHotNum(int hotNum) {
      this.hotNum = hotNum;
   }
   public String getHotTitle() {
      return hotTitle;
   }
   public void setHotTitle(String hotTitle) {
      this.hotTitle = hotTitle;
   }
   public String getHotRegdate() {
      return hotRegdate;
   }
   public void setHotRegdate(String hotRegdate) {
      this.hotRegdate = hotRegdate;
   }
   
   public HotBoardVO(int hotNum, String hotTitle, String hotRegdate) {
      super();
      this.hotNum = hotNum;
      this.hotTitle = hotTitle;
      this.hotRegdate = hotRegdate;
   }
   @Override
   public String toString() {
      return "HotBoardVO [hotNum=" + hotNum + ", hotTitle=" + hotTitle + ", hotRegdate=" + hotRegdate + "]";
   }
   
}
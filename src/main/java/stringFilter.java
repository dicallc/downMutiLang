public class stringFilter {
  public static void main(String[] args) {
    //String reg = "^\\S[\\sA-Za-z,\\.·\\-]*\\S$";
    String pswRegex1 = "^[a-zA-Z]+$";
    String str = "·d.-f";
    // 全英文
    str=str.replace("-","");
    str=str.replace(".","");
    str=str.trim();
    System.out.println(str.matches(pswRegex1));
  }
}

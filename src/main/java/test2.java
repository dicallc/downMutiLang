public class test2 {


    public static String  unescape (String src){
      StringBuffer tmp = new StringBuffer();
      tmp.ensureCapacity(src.length());
      int  lastPos=0,pos=0;
      char ch;
      src = src.replace("&#x","%u").replace(";","");
      while (lastPos<src.length()){
        pos = src.indexOf("%",lastPos);
        if (pos == lastPos){
          if (src.charAt(pos+1)=='u'){
            ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
            tmp.append(ch);
            lastPos = pos+6;
          }else{
            ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
            tmp.append(ch);
            lastPos = pos+3;
          }
        } else{

          if (pos == -1){
            tmp.append(src.substring(lastPos));
            lastPos=src.length();
          } else{
            tmp.append(src.substring(lastPos,pos));
            lastPos=pos;
          }
        }
      }
      return tmp.toString();
    }



    public static void main(String[] args) {
      String string2 = "&#x6c40;&#x5dde;&#x8def;&#xff13;&#x6bb5;";
      String str2=unescape(string2);
      System.out.println("newStr: " + str2);
    }

}

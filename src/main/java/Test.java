import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Test {
  public static void main(String[] args) {
    //String str="";
    //String[] d = str.split(",");
    //List<String> mTS = Arrays.asList(d);
    //List<String> newlist = new ArrayList<String>(mTS);
    //newlist.add("sdfsdfsdf");
    String date="30Mar1990";
    SimpleDateFormat sf = new SimpleDateFormat("dMMMyyyy", Locale.ENGLISH);
    Date parseDate = null;
    try {
      parseDate = sf.parse(date);
    } catch (ParseException mE) {
      mE.printStackTrace();
    }
    SimpleDateFormat pFormat = new SimpleDateFormat("yyyy-MM-dd");
    String parseStr = pFormat.format(parseDate);

    //String url="https://resource.mitrade.com/pmsync/htmls/banner/html/2020-03-26/1585210189/TC-withvisuals.html";
    //int startIndex = url.lastIndexOf("/")+1;
    //int endIndex = url.lastIndexOf(".");
    //String str = url.substring(startIndex, endIndex);
    //System.out.println(str);
    //String arr[]=new String[2];
    //arr[0]="111111";
    //arr[1]="1122221";
    //
    //System.out.println(arr.toString());
    //try {
    //  String mDate = StringToDate("2019-08-12");
    //  System.out.println(mDate);
    //} catch (ParseException mE) {
    //  mE.printStackTrace();
    //}
  }

  private static String StringToDate(String time) throws ParseException {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    date = format.parse(time);
    SimpleDateFormat format1 = new SimpleDateFormat("dd/mm/yyyy");
    String s = format1.format(date);
    return s;
  }
}

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Main {
  static String basePath="D:\\code\\mitrade-Android\\";
  private static String sJSESSIONID="";
  static String enFile=basePath+"app\\src\\main\\res\\values\\strings.xml";
  static String enUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=en-US";
  static String cnUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=zh-CN";
  static String cnFile=basePath+"app\\src\\main\\res\\values-zh-rCN\\strings.xml";
  static String twUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=zh-TW";
  static String twFile=basePath+"app\\src\\main\\res\\values-zh-rTW\\strings.xml";
  static String koUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=ko";
  static String koFile=basePath+"app\\src\\main\\res\\values-ko\\strings.xml";
  static String thUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=th";
  static String thFile=basePath+"app\\src\\main\\res\\values-th\\strings.xml";
  static String viUrl="http://cms-app.yuanqu-tech.com/admin/translate/download?terminal=is_android&lang=vi";
  static String viFile=basePath+"app\\src\\main\\res\\values-vi\\strings.xml";
  public static void main(String[] args) {
    try {
      loadLang(enUrl,enFile);
      loadLang(cnUrl,cnFile);
      loadLang(twUrl,twFile);
      loadLang(koUrl,koFile);
      loadLang(thUrl,thFile);
      loadLang(viUrl,viFile);
    } catch (Exception mE) {
      mE.printStackTrace();
    }
  }

  private static void loadLang(String url,String file) throws IOException {
    String enJson = get(url);
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");
    saveAsFileWriter(enJson,file);
  }

  public static void saveDocument(Document doc,String filePath) throws IOException{
    OutputFormat format = OutputFormat.createPrettyPrint();
    XMLWriter writer = new XMLWriter(new FileOutputStream(filePath),format);
    writer.write(doc);
    writer.close();
  }
  private static void saveAsFileWriter(String content,String filePath) {
    try {
      Document document = DocumentHelper.parseText(content);
      saveDocument(document,filePath);
    } catch (Exception mE) {
      mE.printStackTrace();
    }
  }
  static String get(String url) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .addHeader("cookie", sJSESSIONID)
        .url(url)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  // 设置OKHttpClient自动管理Cookie
  private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
      cookieStore.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
      List<Cookie> cookies = cookieStore.get(url.host());
      return cookies != null ? cookies : new ArrayList<Cookie>();
    }
  }).build();

  static String toLogin(String url) throws IOException {

    //RequestBody body = RequestBody.create(json, JSON);
    FormBody formBody = new FormBody.Builder()
        .add("username", "admin")
        .add("password", "yuanqu123")
        .build();
    Request request = new Request.Builder()
        .url(url)
        .post(formBody)
        .build();
    try {
      Response response = mOkHttpClient.newCall(request).execute();
      if (response != null && response.isSuccessful()) {
        String session = response.headers().get("Set-Cookie");
        sJSESSIONID = session.substring(0, session.indexOf(";"));
      }else {
        printErro("请求失败"+response.message());
      }
      return response.body().string();
    }catch (Exception e){
      printErro(e.getMessage());
    }
    return null;
  }
  static void printErro(String msg){
    System.out.println("请求失败"+msg);
  }

}

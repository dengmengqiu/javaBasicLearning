package Pattern.Tempareture.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TemparatureInChinaAdapter implements LocTemparature{
    public static String getURL(){
        Calendar cal=Calendar.getInstance();
        String current = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
        cal.add(cal.DATE, -1);
        String before = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
        String quotewebDource = "http://api.data.cma.cn:8090/api?userId=5546258859083jl9v&pwd=OqO7j2d" +
                "&dataFormat=json&interfaceId=getSurfEleByTimeRangeAndStaID&dataCode=SURF_CHN_MUL_HOR" +
                "&timeRange=[" + before + "," + current + "]" +
                "&staIDs=54399&elements=TEM";
        return quotewebDource;
    }

    /**
     * 发起http请求并获取结果
     * @param requestUrl 请求地址
     */
    public static JsonObject getXpath(String requestUrl){
        String res="";
        JsonObject object = null;
        StringBuffer buffer = new StringBuffer();
        try{
            URL url = new URL(getURL());
            HttpURLConnection urlCon= (HttpURLConnection)url.openConnection();
            if(200==urlCon.getResponseCode()){
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                BufferedReader br = new BufferedReader(isr);

                String str = null;
                while((str = br.readLine())!=null){
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JsonParser parse =new JsonParser();
                object = (JsonObject) parse.parse(res);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public static JsonObject postDownloadJson(String path,String post){
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write(post);//post的参数 xx=xx&yy=yy
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while((len=bis.read(arr))!= -1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            JsonParser parse = new JsonParser();
            return (JsonObject)parse.parse(bos.toString("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public float getTemparature() {
        JsonObject res = null;
        res = getXpath(getURL());
        JsonArray arr = res.get("DS").getAsJsonArray();
        return arr.get(arr.size()-1).getAsJsonObject().get("TEM").getAsFloat();
    }

    //测试
    public static void main(String args [] ) {
        JsonObject res = null;
        res = getXpath(getURL());
        System.out.println(res);
        JsonArray arr = res.get("DS").getAsJsonArray();
        System.out.println(arr.get(arr.size()-1).getAsJsonObject().get("TEM").getAsFloat());
    }
}
//{"returnCode":"0",
//"returnMessage":"Query Succeed",
//"rowCount":"16",
//"colCount":"1",
//"requestParams":"datacode=SURF_CHN_MUL_HOR&staids=54399&timerange=[20190406210914,20190407210914]&elements=TEM",
//"requestTime":"2019-04-07 13:09:15",
//"responseTime":"2019-04-07 13:09:15",
//"takeTime":"0.014",
//"fieldNames":"温度/气温",
//"fieldUnits":"摄氏度(℃)",
//"DS":[{"TEM":"3.9000"},{"TEM":"4.7000"},{"TEM":"10.0000"},{"TEM":"13.0000"},{"TEM":"15.0000"},{"TEM":"15.6000"},{"TEM":"17.5000"},{"TEM":"18.8000"},{"TEM":"20.6000"},{"TEM":"20.6000"},{"TEM":"19.5000"},{"TEM":"18.8000"},{"TEM":"17.6000"},{"TEM":"16.0000"},{"TEM":"14.8000"},{"TEM":"14.0000"}]}


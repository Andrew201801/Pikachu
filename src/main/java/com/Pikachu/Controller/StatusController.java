package com.Pikachu.Controller;

import com.Pikachu.Bean.Login;
import com.Pikachu.Bean.Member;
import com.Pikachu.Bean.Status;
import com.Pikachu.Bean.Transaction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.web.context.request.SessionScope;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@SessionAttributes({"login", "accountID", "credit", "point"})
public class StatusController {

    private Status status = new Status();

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();
            if (connection.getResponseCode() == 404) {
                return "False";
            }

            // 获取所有响应头字段
//            try{
//           Map<String, List<String>> map = connection.getHeaderFields();

//            }catch (FileNotFoundException e){
//                throw e;
//            }
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    //    新增的Put方法，用于更新credit
    public static String sendPut(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // 发送Put请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 Put 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    //    新增的Del方法，用于删除数据
    public static String sendDel(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();
            if (connection.getResponseCode() == 404) {
                return "False";
            }
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public String getHistoryById(String aid) {

        String id = status.getAccountID();
//      type里填充类型，注意大小写
        String type = status.getType();
        String credit = status.getCredit();
        String[] s = new String[4];

//       信用最低那条的IP
        if (Integer.parseInt(credit) < 33 && type.equals("Company")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/companyEarnPoints");
            s[1] = "False";
            s[2] = sendGet("http://35.211.105.21:3000/api/companyUsePoints");
            s[3] = "False";
        } else if (Integer.parseInt(credit) < 33 && type.equals("Member")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/userEarnPoints");
            s[1] = sendGet("http://35.211.105.21:3000/api/userEarnPointsFromUser");
            s[2] = sendGet("http://35.211.105.21:3000/api/userUsePoints");
            s[3] = sendGet("http://35.211.105.21:3000/api/userUsePointsToUser");
        }

//       信用最中间那条的IP
        else if (Integer.parseInt(credit) < 66 && type.equals("Company")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/companyEarnPoints");
            s[1] = "False";
            s[2] = sendGet("http://35.211.105.21:3000/api/companyUsePoints");
            s[3] = "False";
        } else if (Integer.parseInt(credit) < 66 && type.equals("Member")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/userEarnPoints");
            s[1] = sendGet("http://35.211.105.21:3000/api/userEarnPointsFromUser");
            s[2] = sendGet("http://35.211.105.21:3000/api/userUsePoints");
            s[3] = sendGet("http://35.211.105.21:3000/api/userUsePointsToUser");
        }

//       信用最高那条的IP
        else if (Integer.parseInt(credit) > 66 && type.equals("Company")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/companyEarnPoints");
            s[1] = "False";
            s[2] = sendGet("http://35.211.105.21:3000/api/companyUsePoints");
            s[3] = "False";
        } else if (Integer.parseInt(credit) > 66 && type.equals("Member")) {
            s[0] = sendGet("http://35.211.105.21:3000/api/userEarnPoints");
            s[1] = sendGet("http://35.211.105.21:3000/api/userEarnPointsFromUser");
            s[2] = sendGet("http://35.211.105.21:3000/api/userUsePoints");
            s[3] = sendGet("http://35.211.105.21:3000/api/userUsePointsToUser");
        }

        JSONArray cleandata = new JSONArray();
        int Earncount = 0;
        int Usecount = 0;
        for (int i = 0; i < 4; i++) {
            JSONArray jarray = JSONArray.fromObject(s[i]);
            for (int j = 0; j < jarray.size(); j++) {
                if (s[i].equals("False")) {
                    continue;
                }
                JSONObject job = jarray.getJSONObject(j);
                if (type.equals("Company") && job.getString("Company").equals("resource:org.pikachu2.biznet.Company#" + id)) {
                    cleandata.add(job);
                    if (i < 2) {
                        Earncount++;
                    } else {
                        Usecount++;
                    }
                }
                if (type.equals("Member") && job.getString("Member").equals("resource:org.pikachu2.biznet.Member#" + id)) {
                    cleandata.add(job);
                    if (i < 2) {
                        Earncount++;
                    } else {
                        Usecount++;
                    }
                }
            }
        }
        if (Earncount >= 5 && Usecount >= 5 && Earncount + Usecount >= 15) {
            return "Liar!";
        }
        return cleandata.toString();
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/sign", method = {RequestMethod.GET})
    public String sign(Model model) {
        //如果模型数据中包含同名数据则不再添加，
        //如果不判断将一直重新new新的模型数据
        if (!model.containsAttribute("member")) {
            Member member = new Member();
            model.addAttribute("member", new Member());
        }
        return "sign";
    }


    @RequestMapping(value = "/sign", method = {RequestMethod.POST})
    public String signSave(Model model, ModelMap map, @Valid @ModelAttribute("member") Member member,
                           BindingResult result, javax.servlet.http.HttpServletRequest request) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return sign(model);
        }

        String id = member.getAccountID();

        String s1 = sendGet("http://34.220.123.35:3000/api/Company/" + id);
        String s2 = sendGet("http://35.211.105.21:3000/api/Company/" + id);
        String s3 = sendGet("http://54.191.138.172:3000/api/Company/" + id);
        String s4 = sendGet("http://34.220.123.35:3000/api/Member/" + id);
        String s5 = sendGet("http://35.211.105.21:3000/api/Member/" + id);
        String s6 = sendGet("http://54.191.138.172:3000/api/Member/" + id);

        if (s1.equals("False") && s2.equals("False") && s3.equals("False") && s4.equals("False") && s5.equals("False") && s6.equals("False")) {
            String typeCheck = request.getParameter("typeCheck");
//        System.out.println(typeCheck);

            if (typeCheck.equals("Company")) {
                member.setCredit("80");
                member.setPoint("100");
                map.addAttribute("login", member);
                map.addAttribute("accountID", "Company" + member.getAccountID());
                map.addAttribute("credit", member.getCredit());
                map.addAttribute("point", member.getPoint());
                this.status.setCredit("80");
                this.status.setPoint("100");
                String data = "{" + "\"id\":\"" + member.getAccountID() + "\"," + "\"Info\":{" + "\"points\":100," + "\"Credit\":80," + "\"password\":\"" + member.getPassword() + "\"" + "}" + "}";
                String s = sendPost("http://35.211.105.21:3000/api/Company", data);

//        model.addAttribute("error", "Dear " + member.getFirstName() + " , your Registration completed successfully");
                return "welcomeUser";
            }
            member.setCredit("80");
            member.setPoint("0");
            map.addAttribute("login", member);
            map.addAttribute("accountID", member.getAccountID());
            map.addAttribute("credit", member.getCredit());
            map.addAttribute("point", member.getPoint());
//        model.addAttribute("error", "Dear " + member.getFirstName() + " , your Registration completed successfully");
            this.status.setAccountID(member.getAccountID());
            this.status.setCredit("80");
            this.status.setPoint("0");
            this.status.setType(typeCheck);
            String data = "{" + "\"id\":\"" + member.getAccountID() + "\"," + "\"Info\":{" + "\"points\":0," + "\"Credit\":80," + "\"password\":\"psd\"" + "}" + "}";
            String s = sendPost("http://35.211.105.21:3000/api/Company", data);
            return "welcomeUser";
        } else {
            model.addAttribute("error", "This Account ID has existed.");
            return "sign";
        }
    }


    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(Model model) {
        //如果模型数据中包含同名数据则不再添加，
        //如果不判断将一直重新new新的模型数据
        if (!model.containsAttribute("login")) {
            Login login = new Login();
            model.addAttribute("login", new Login());
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String loginSave(Model model, ModelMap map, @Valid @ModelAttribute("login") Login login,
                            BindingResult result) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return login(model);
        }

        String id = login.getAccountID();
        String password = login.getPassword();
        String[] s = new String[6];
        s[0] = sendGet("http://34.220.123.35:3000/api/Company/" + id);
        s[1] = sendGet("http://35.211.105.21:3000/api/Company/" + id);
        s[2] = sendGet("http://54.191.138.172:3000/api/Company/" + id);
        s[3] = sendGet("http://34.220.123.35:3000/api/Member/" + id);
        s[4] = sendGet("http://35.211.105.21:3000/api/Member/" + id);
        s[5] = sendGet("http://54.191.138.172:3000/api/Member/" + id);
        int flag = -1;
        for (int i = 0; i < 6; i++) {
            if (!s[i].equals("False")) {
                flag = i;
                break;
            }
        }
        if (flag == -1) {
            model.addAttribute("error", "This ID is not existed.");
            return "login";
        } else {
            JSONObject job = JSONObject.fromObject(s[flag]);
            if (password.equals(JSONObject.fromObject(job.getString("Info")).getString("password"))) {
//                String aid=job.getString("mID");
                String point = JSONObject.fromObject(job.getString("Info")).getString("points");
                String credit = JSONObject.fromObject(job.getString("Info")).getString("Credit");
//                String apassword=JSONObject.fromObject(job.getString("Info")).getString("password");
                String type = job.getString("$class").substring(20);

//               储存链上获取的账户数据
//                Member aMember=new Member(aid,point,credit,apassword,type);

                map.addAttribute("login", login);
                map.addAttribute("accountID", login.getAccountID());
                login.setPoint(point);
                login.setCredit(credit);
                map.addAttribute("credit", login.getCredit());
                map.addAttribute("point", login.getPoint());
                this.status.setAccountID(login.getAccountID());
                this.status.setCredit(credit);
                this.status.setPoint(point);
                this.status.setType(type);
                return "welcomeUser";
            } else {
                model.addAttribute("error", "Password is not correct.");
                return "login";
            }
        }
    }

    @RequestMapping(value = "/indexUser", method = {RequestMethod.GET})
    public String indexUser() {
        return "indexUser";
    }

    @RequestMapping(value = "/welcomeUser", method = {RequestMethod.GET})
    public String welcomeUser() {
        return "welcomeUser";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }

    @RequestMapping(value = "/transaction", method = {RequestMethod.GET})
    public String transaction(Model model) {
        //如果模型数据中包含同名数据则不再添加，
        //如果不判断将一直重新new新的模型数据
        if (!model.containsAttribute("transaction")) {
            Transaction transaction = new Transaction();
            model.addAttribute("transaction", new Transaction());
        }
        return "transaction";
    }

    @RequestMapping(value = "/transaction", method = {RequestMethod.POST})
    public String transactionSave(Model model, ModelMap map, Member member, @Valid @ModelAttribute("transaction") Transaction transaction,
                                  BindingResult result, javax.servlet.http.HttpServletRequest request, SimpleSessionStatus sessionStatus) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return transaction(model);
        }

        String frompoint = status.getPoint();
        String fromid = status.getAccountID();
        String fromcredit = status.getCredit();
        String frompassword = status.getPassword();
        String fromtype = status.getType();


        String toid = transaction.getAccountID();
        String point = transaction.getPoint();

        String totype = "";
        String topoint = "";
        String tocredit = "";
        String topassword = "";

        if (Integer.parseInt(point) > Integer.parseInt(frompoint)) {
            model.addAttribute("error", "Point is not enough.");
            return "transaction";
        }

        String[] s = new String[6];

        //需要修改IP
        s[0] = sendGet("http://34.220.123.35:3000/api/Company/" + toid);
        s[1] = sendGet("http://35.211.105.21:3000/api/Company/" + toid);
        s[2] = sendGet("http://54.191.138.172:3000/api/Company/" + toid);
        s[3] = sendGet("http://34.220.123.35:3000/api/Member/" + toid);
        s[4] = sendGet("http://35.211.105.21:3000/api/Member/" + toid);
        s[5] = sendGet("http://54.191.138.172:3000/api/Member/" + toid);
        int flag = -1;
        for (int i = 0; i < 6; i++) {
            if (!s[i].equals("False")) {
                flag = i;
                break;
            }
        }
        if (flag == -1) {
            model.addAttribute("error", "Account ID is not found.");
            return "transaction";
        }
        JSONObject job = JSONObject.fromObject(s[flag]);
        totype = job.getString("$class").substring(20);
        topoint = JSONObject.fromObject(job.getString("Info")).getString("points");
        tocredit = JSONObject.fromObject(job.getString("Info")).getString("Credit");
        topassword = JSONObject.fromObject(job.getString("Info")).getString("password");
        String credit = JSONObject.fromObject(job.getString("Info")).getString("Credit");
        Boolean C2C = Integer.parseInt(credit) < 33 && Integer.parseInt(fromcredit) < 33;
        Boolean C2B = Integer.parseInt(credit) < 66 && Integer.parseInt(fromcredit) < 33;
        Boolean B2C = Integer.parseInt(credit) < 33 && Integer.parseInt(fromcredit) < 66;
        if (C2C || C2B || B2C) {
            model.addAttribute("error", "Credit limited.");
            return "transaction";
        }
//
////        判断是否是黄牛，是则惩罚，并输出信息
//        String aresult = getHistoryById(fromid);
//        if (aresult.equals("Lair")) {
//            String updateFromCredit = "{\n" +
//                    "  \"id\": \"" + fromid + "\",\n" +
//                    "  \"Info\": {\n" +
//                    "    \"points\": " + (Integer.parseInt(frompoint) - 100) + ",\n" +
//                    "    \"Credit\": " + (Integer.parseInt(fromcredit) - 20) + ",\n" +
//                    "    \"password\": \"" + frompassword + "\"\n" +
//                    "  }\n" +
//                    "}";
//            String updeteFrom = sendPut("http://35.211.105.21:3000/api/Member/" + fromid, updateFromCredit);
//            model.addAttribute("error", "You are a liar!");
//            return "transaction";
//        }

        //      认证完成，开始更新数据，执行转账，需要填充三个级别的链的IP
        String fromIP = "";
        String toIP = "";

        if (Integer.parseInt(fromcredit) < 33) {
            fromIP = "54.191.138.172";
        } else if (Integer.parseInt(fromcredit) < 66) {
            fromIP = "35.211.105.21";
        } else {
            fromIP = "34.220.123.35";
        }

        if (Integer.parseInt(tocredit) < 33) {
            toIP = "54.191.138.172";
        } else if (Integer.parseInt(tocredit) < 66) {
            toIP = "35.211.105.21";
        } else {
            toIP = "34.220.123.35";
        }

        String useurl = "";
        String earnurl = "";

        if (fromtype.equals("Member") && totype.equals("Member")) {

//            添加交易记录
            useurl = "http://" + fromIP + ":3000/api/" + "userUsePointsToUser";
            earnurl = "http://" + toIP + ":3000/api/" + "userEarnPointsFromUser";

            String fromData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"member\": \"" + fromid + "\",\n" +
                    "  \"othermember\": \"" + toid + "\"\n" +
                    "}";

            String toData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"member\": \"" + toid + "\",\n" +
                    "  \"othermember\": \"" + fromid + "\"\n" +
                    "}";

            sendPost(useurl, fromData);
            sendPost(earnurl, toData);

//          更新信用信息
            Boolean a = Integer.parseInt(fromcredit) < 33 && (Integer.parseInt(fromcredit) + 1) == 33;
            Boolean b = Integer.parseInt(fromcredit) < 66 && (Integer.parseInt(fromcredit) + 1) == 66;
            Boolean c = Integer.parseInt(tocredit) < 33 && (Integer.parseInt(tocredit) + 1) == 33;
            Boolean d = Integer.parseInt(tocredit) < 66 && Integer.parseInt(tocredit) + 1 == 66;


            if (a) {
//                  C升到B
                sendDel("http://" + fromIP + "/api/Member/" + fromid);
                String Data = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "35.211.105.21" + "/api/Member", Data);

            } else if (b) {
//                B升到A
                sendDel("http://" + fromIP + "/api/Member/" + fromid);
                String Data = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "34.220.123.35" + "/api/Member", Data);
            } else {
                String updateFromCredit = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 1) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";

                sendPut("http://" + fromIP + "/api/Member/" + fromid, updateFromCredit);
            }

            if (c) {
//                C升到B
                sendDel("http://" + toIP + "/api/Member/" + toid);
                String Data = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "35.211.105.21" + "/api/Member", Data);
            } else if (d) {
//                B升到A
                sendDel("http://" + toIP + "/api/Member/" + toid);
                String Data = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "34.220.123.35" + "/api/Member", Data);
            } else {
                String updateToCredit = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 1) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPut("http://" + toIP + ":3000/api/Member/" + toid, updateToCredit);
            }
        }


        if (fromtype.equals("Member") && totype.equals("Company")) {
            //            添加交易记录
            useurl = "http://" + fromIP + ":3000/api/" + "userUsePoints";
            earnurl = "http://" + toIP + ":3000/api/" + "companyEarnPoints";

            String fromData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"company\": \"" + toid + "\",\n" +
                    "  \"member\": \"" + fromid + "\"\n" +
                    "}";

            String toData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"company\": \"" + toid + "\",\n" +
                    "  \"member\": \"" + fromid + "\"\n" +
                    "}";

            sendPost(useurl, fromData);
            sendPost(earnurl, toData);

//          更新信用信息
            Boolean a = Integer.parseInt(fromcredit) < 33 && (Integer.parseInt(fromcredit) + 1) == 33;
            Boolean b = Integer.parseInt(fromcredit) < 66 && (Integer.parseInt(fromcredit) + 1) == 66;
            Boolean c = Integer.parseInt(tocredit) < 33 && (Integer.parseInt(tocredit) + 1) == 33;
            Boolean d = Integer.parseInt(tocredit) < 66 && Integer.parseInt(tocredit) + 1 == 66;


            if (a) {
//                  C升到B
                sendDel("http://" + fromIP + "/api/Member/" + fromid);
                String Data = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "35.211.105.21" + "/api/Member", Data);

            } else if (b) {
//                B升到A
                sendDel("http://" + fromIP + "/api/Member/" + fromid);
                String Data = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "34.220.123.35" + "/api/Member", Data);
            } else {
                String updateFromCredit = "{\n" +
                        "  \"id\": \"" + fromid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 1) + ",\n" +
                        "    \"password\": \"" + frompassword + "\"\n" +
                        "  }\n" +
                        "}";

                sendPut("http://" + fromIP + "/api/Member/" + fromid, updateFromCredit);

            }

            if (c) {
//                C升到B
                sendDel("http://" + toIP + "/api/Company/" + toid);
                String Data = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "35.211.105.21" + "/api/Company", Data);
            } else if (d) {
//                B升到A
                sendDel("http://" + toIP + "/api/Company/" + toid);
                String Data = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPost("http://" + "34.220.123.35" + "/api/Company", Data);
            } else {

                String updateToCredit = "{\n" +
                        "  \"id\": \"" + toid + "\",\n" +
                        "  \"Info\": {\n" +
                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 1) + ",\n" +
                        "    \"password\": \"" + topassword + "\"\n" +
                        "  }\n" +
                        "}";
                sendPut("http://" + toIP + ":3000/api/Company/" + toid, updateToCredit);
            }
        }

        if (fromtype.equals("Company") && totype.equals("Member")) {
            //            添加交易记录
            useurl = "http://" + "35.211.105.21" + ":3000/api/" + "companyUsePoints";
            earnurl = "http://" + "35.211.105.21" + ":3000/api/" + "userEarnPoints";

            String fromData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"company\": \"" + fromid + "\",\n" +
                    "  \"member\": \"" + toid + "\"\n" +
                    "}";

            String toData = "{\n" +
                    "  \"points\": " + point + ",\n" +
                    "  \"company\": \"" + fromid + "\",\n" +
                    "  \"member\": \"" + toid + "\"\n" +
                    "}";

            sendPost(useurl, fromData);
            sendPost(earnurl, toData);
            System.out.println(fromData);
            System.out.println(toData);

////          更新信用信息
//            Boolean a = Integer.parseInt(fromcredit) < 33 && (Integer.parseInt(fromcredit) + 1) == 33;
//            Boolean b = Integer.parseInt(fromcredit) < 66 && (Integer.parseInt(fromcredit) + 1) == 66;
//            Boolean c = Integer.parseInt(tocredit) < 33 && (Integer.parseInt(tocredit) + 1) == 33;
//            Boolean d = Integer.parseInt(tocredit) < 66 && Integer.parseInt(tocredit) + 1 == 66;
//
//
//            if (a) {
////                  C升到B
//                sendDel("http://" + fromIP + "/api/Company/" + fromid);
//                String Data = "{\n" +
//                        "  \"id\": \"" + fromid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
//                        "    \"password\": \"" + frompassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//                sendPost("http://" + "35.211.105.21" + "/api/Company", Data);
//
//            } else if (b) {
////                B升到A
//                sendDel("http://" + fromIP + "/api/Company/" + fromid);
//                String Data = "{\n" +
//                        "  \"id\": \"" + fromid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 2) + ",\n" +
//                        "    \"password\": \"" + frompassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//                sendPost("http://" + "34.220.123.35" + "/api/Company", Data);
//            } else {
//                String updateFromCredit = "{\n" +
//                        "  \"id\": \"" + fromid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(frompoint) - Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(fromcredit) + 1) + ",\n" +
//                        "    \"password\": \"" + frompassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//
//                sendPut("http://" + fromIP + "/api/Company/" + fromid, updateFromCredit);
//
//            }
//
//            if (c) {
////                C升到B
//                sendDel("http://" + toIP + "/api/Member/" + toid);
//                String Data = "{\n" +
//                        "  \"id\": \"" + toid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
//                        "    \"password\": \"" + topassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//                sendPost("http://" + "35.211.105.21" + "/api/Member", Data);
//            } else if (d) {
////                B升到A
//                sendDel("http://" + toIP + "/api/Member/" + toid);
//                String Data = "{\n" +
//                        "  \"id\": \"" + toid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 2) + ",\n" +
//                        "    \"password\": \"" + topassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//                sendPost("http://" + "34.220.123.35" + "/api/Member", Data);
//            } else {
//                String updateToCredit = "{\n" +
//                        "  \"id\": \"" + toid + "\",\n" +
//                        "  \"Info\": {\n" +
//                        "    \"points\": " + (Integer.parseInt(topoint) + Integer.parseInt(point)) + ",\n" +
//                        "    \"Credit\": " + (Integer.parseInt(tocredit) + 1) + ",\n" +
//                        "    \"password\": \"" + topassword + "\"\n" +
//                        "  }\n" +
//                        "}";
//                sendPut("http://" + toIP + ":3000/api/Member/" + toid, updateToCredit);
//            }
//        }


        return "success";


    }


}

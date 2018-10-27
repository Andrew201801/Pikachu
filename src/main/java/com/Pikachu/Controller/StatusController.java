package com.Pikachu.Controller;

import com.Pikachu.Bean.Login;
import com.Pikachu.Bean.Member;
import com.Pikachu.Bean.Status;
import com.Pikachu.Bean.Transaction;
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

        String s1 = sendGet("http://35.211.105.21:3000/api/Company/" + id);
        String s2 = sendGet("http://35.211.105.21:3000/api/Company/" + id);
        String s3 = sendGet("http://35.211.105.21:3000/api/Company/" + id);
        String s4 = sendGet("http://35.211.105.21:3000/api/Member/" + id);
        String s5 = sendGet("http://35.211.105.21:3000/api/Member/" + id);
        String s6 = sendGet("http://35.211.105.21:3000/api/Member/" + id);

        if (s1.equals("False") && s2.equals("False") && s3.equals("False") && s4.equals("False") && s5.equals("False") && s6.equals("False")) {
            String typeCheck = request.getParameter("typeCheck");
//        System.out.println(typeCheck);

            if (typeCheck.equals("company")) {
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

        //Todo:获取到用户真正的Password，标记为truePassword
        String truePassword = login.getPassword();
//        String truePassword = "";
        if (login.getPassword().equals(truePassword)) {
            map.addAttribute("login", login);
            map.addAttribute("accountID", login.getAccountID());
            //Todo: login.set... 中调用API，直接map.addAttribute("point", 从API获取point)会存在不能获取到数据的情况
            login.setPoint("1000");
            login.setCredit("800");
            map.addAttribute("credit", login.getCredit());
            map.addAttribute("point", login.getPoint());

            //以防万一我们给login对象赋值
            //Todo: login.setCredit(...);
            //Todo: login.setPoint(...);

            //Todo: 在Session中添加用户的credit和point
//        model.addAttribute("accountID", login.getAccountID());
//            SessionScope sessionScope = new SessionScope();
//            String temp=sessionScope.
//            map.addAttribute("point", );
            this.status.setAccountID(login.getAccountID());
            this.status.setCredit("800");
            this.status.setPoint("1000");
            return "welcomeUser";
        }
        //Todo: 如果用户名不存在也需要判断，然后将错误信息标记成不同的，下述代码中只假设了密码不对
        //Todo: 在登录页中已经设定了Error的输出

        model.addAttribute("error", "Error Password");

        return "login";
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

        //TODO:判断对方用户名是否会不存在
//        if(对方用户名不存在){
//            model.addAttribute("error", "invalid Account ID");
//            return "transaction";
//        }
        //TODO:上面的需要实现


        return "success";
    }


}

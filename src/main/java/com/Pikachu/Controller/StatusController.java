package com.Pikachu.Controller;

import com.Pikachu.Bean.Login;
import com.Pikachu.Bean.Member;
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

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@SessionAttributes({"login", "accountID"})
public class StatusController {

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
                           BindingResult result,javax.servlet.http.HttpServletRequest request) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return sign(model);
        }

        String typeCheck=request.getParameter("typeCheck");
//        System.out.println(typeCheck);

        if(typeCheck.equals("company")){
            member.setCredit("100");
            member.setPoint("100");
            map.addAttribute("login", member);
            map.addAttribute("accountID", "Company"+member.getAccountID());
            map.addAttribute("credit", member.getCredit());
            map.addAttribute("point", member.getPoint());
//        model.addAttribute("error", "Dear " + member.getFirstName() + " , your Registration completed successfully");
            return "welcomeUser";
        }

        //Todo: 用member.getAccountID()获取到用户注册的时候的ID，需要审查ID是否重复，详细代码在登录的组件有实现
        //Todo: 下列实现在成功注册的时候的逻辑
        member.setCredit("80");
        member.setPoint("100");
        map.addAttribute("login", member);
        map.addAttribute("accountID", member.getAccountID());
        map.addAttribute("credit", member.getCredit());
        map.addAttribute("point", member.getPoint());
//        model.addAttribute("error", "Dear " + member.getFirstName() + " , your Registration completed successfully");
        return "welcomeUser";
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
            map.addAttribute("credit", "80");
            map.addAttribute("point", "100");
            //Todo: map.addAttribute("credit", 从API获取credit);
            //Todo:map.addAttribute("point", 从API获取point);
            //以防万一我们给login对象赋值
            //Todo: login.setCredit(...);
            //Todo: login.setPoint(...);

            //Todo: 在Session中添加用户的credit和point
//        model.addAttribute("accountID", login.getAccountID());
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

    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }


}

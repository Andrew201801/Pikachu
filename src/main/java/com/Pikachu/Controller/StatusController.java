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
import java.io.UnsupportedEncodingException;

@Controller
@SessionAttributes({"login", "accountID"})
public class StatusController {

    @RequestMapping("/index")
    public  String index(){
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
                           BindingResult result) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return sign(model);
        }
        map.addAttribute("login", member);
        map.addAttribute("accountID", member.getAccountID());
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
    public String signSave(Model model, ModelMap map, @Valid @ModelAttribute("login") Login login,
                           BindingResult result) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if (result.hasErrors()) {
            return login(model);
        }

        map.addAttribute("login", login);
        map.addAttribute("accountID", login.getAccountID());
//        model.addAttribute("accountID", login.getAccountID());
        return "welcomeUser";
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

package com.Pikachu.Controller;

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

import javax.validation.Valid;
import com.Pikachu.Bean.Member;

import java.io.UnsupportedEncodingException;

@Controller
public class SignUpController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/sign", method = {RequestMethod.GET})
    public String index(Model model){
        //如果模型数据中包含同名数据则不再添加，
        //如果不判断将一直重新new新的模型数据
        if(!model.containsAttribute("member")){
            Member member = new Member();
            model.addAttribute("member", new Member());
        }
        return "sign";
    }

    @RequestMapping(value="/sign", method = {RequestMethod.POST})
    public String indexSave(Model model, @Valid @ModelAttribute("member") Member member,
                            BindingResult result) throws UnsupportedEncodingException {
        //如果存在验证错误信息重定向到表单提交展示错误信息
        if(result.hasErrors()){
            return index(model);
        }
        model.addAttribute("success", "Dear " + member.getFirstName() + " , your Registration completed successfully");
        return "success";
    }

//    @RequestMapping(value="/success")
//    public String success(Model model){
//        return "success";
//    }





}

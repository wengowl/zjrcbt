package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.common.utils.RSAUtils;
import com.zj.rcbt.hibernate.model.LoginuserBean;
import com.zj.rcbt.spring.service.LoginUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserLoginController {
    private Logger log =LogManager.getLogger(this.getClass());

    @Autowired
    private LoginUserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping({"/getuser"})
    @ResponseBody
    public RequestResult getUser(@RequestBody String requestBody){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String id_num = jsonObject.getString("idCard");
        RequestResult result =  new RequestResult();
        LoginuserBean loginuserBean = userService.findByIDnum(id_num);
        Map<String, Object> resultData = new HashMap();
        if (loginuserBean!=null) {
            resultData.put("username",loginuserBean.getUserName());
            resultData.put("idCard",loginuserBean.getIdNum());
            resultData.put("email",loginuserBean.getEmail());

        }

        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);
        result.setData(resultData);


        return result;

    }

    @RequestMapping({"/editpasswd"})
    @ResponseBody
    public RequestResult editPasswd(@RequestBody String requestBody){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String id_num = jsonObject.getString("idCard");
        String userName = jsonObject.getString("user");
        String newPassword = jsonObject.getString("newPassword");
        log.info("editpasswd "+userName);

        RequestResult result =  new RequestResult();
        LoginuserBean loginuserBean = userService.findByUserName(userName);

        if (loginuserBean!=null) {
            loginuserBean.setPasswd(newPassword);
            userService.update(loginuserBean);
            result.setStatus(0);
        } else {
            result.setStatus(1);
        }
        Map<String, Object> resultData = new HashMap();
        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);
        result.setData(resultData);


        return result;

    }



    @RequestMapping({"/register"})
    @ResponseBody
    public RequestResult userRegister(@RequestBody String requestBody){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String userName = jsonObject.getString("user");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String idCard = jsonObject.getString("idCard");
        log.info("userregister "+userName);

        LoginuserBean loginuserBean = new LoginuserBean();
        loginuserBean.setIdNum(RSAUtils.decryptBase64(idCard));
        loginuserBean.setPasswd(RSAUtils.decryptBase64(password));
        loginuserBean.setEmail(RSAUtils.decryptBase64(email));
        loginuserBean.setUserName(RSAUtils.decryptBase64(userName));





        RequestResult result =  new RequestResult();
         int status =userService.save(loginuserBean);
        result.setStatus(status);
        if (status==0){
            String token = JWTUtils.createToken(loginuserBean.getIdNum());
            Map<String, Object> resultData = new HashMap();
            resultData.put("token",token);
            result.setData(resultData);
            this.request.getSession().setAttribute("USER_LOGIN_STATUS", 11);
            this.request.getSession().setAttribute("USER_LOGIN_USER_NAME", loginuserBean.getUserName());
            this.request.getSession().setAttribute("USER_LOGIN_idCard", loginuserBean.getIdNum());
        }


        return result;

    }
    @RequestMapping({"/edit"})
    @ResponseBody
    public RequestResult userEdit(@RequestBody String requestBody){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String userName = jsonObject.getString("user");
        String email = jsonObject.getString("email");
        String idCard = jsonObject.getString("idCard");

        log.info("useredit "+userName);


        LoginuserBean loginuserBean = userService.findByUserName(userName);
        Map<String, Object> resultData = new HashMap();
        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);









        RequestResult result =  new RequestResult();
        int a =userService.saveedit(loginuserBean,email);
        log.info(a);
        result.setData(resultData);
        result.setStatus(a);



        return result;

    }





    @RequestMapping({"/passwd/change"})
    @ResponseBody
    public RequestResult passwdChange(@RequestBody String requestBody){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String userName = jsonObject.getString("user");
        String password = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        log.info("passwdChange "+userName);

        RequestResult result =  new RequestResult();
        LoginuserBean loginuserBean = userService.findByUserName(userName);
        log.info(password);
        if (loginuserBean!=null) {

            if (password.equals(loginuserBean.getPasswd())){
            loginuserBean.setPasswd(newPassword);
            }
            else{
                result.setStatus(1);
                return result;
            }
           userService.update(loginuserBean);
            result.setStatus(0);
        }
        Map<String, Object> resultData = new HashMap();
        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);
        result.setData(resultData);


        return result;

    }

    @RequestMapping({"/password/reset"})
    @ResponseBody
    public RequestResult passwordReset(@RequestBody String requestBody) {
        JSONObject jsonObject = JSON.parseObject(requestBody);
//        String userName = jsonObject.getString("user_name");
        String idnum = jsonObject.getString("idcard");
        String user_e_mail = jsonObject.getString("user_e_mail");
        log.info("passwordReset "+idnum);


        RequestResult result = null;

            int iRtn =userService.savepasswordReset(idnum, user_e_mail);
            result = RequestResult.statusCode(iRtn);
            return result;

    }
    @RequestMapping({"/getkey"})
    @ResponseBody
    public RequestResult getkey(@RequestBody String requestBody) {
        String publicKey = RSAUtils.generateBase64PublicKey();
        RequestResult result = null;


        if (publicKey != null) {
            result = RequestResult.sucessRes();
            Map<String, Object> resultData = new HashMap();
            resultData.put("publicKey", publicKey);
            result.setData(resultData);
        } else {
            result = RequestResult.errorRes(1);
        }

        return result;


    }

    @RequestMapping({"/login"})
    @ResponseBody
    public RequestResult login(@RequestBody String requestBody) {
        JSONObject jsonObject = JSON.parseObject(requestBody);
        String userNamea = jsonObject.getString("user_name");
        String passworda = jsonObject.getString("user_password");
        RequestResult result = null;

        String userName = RSAUtils.decryptBase64(userNamea);
        String password = RSAUtils.decryptBase64(passworda);
        log.info("login "+userName);

            LoginuserBean user = userService.login(userName, password);
            if (user != null) {
                result = RequestResult.sucessRes();
                Map<String, Object> resultData = new HashMap();
                resultData.put("user_type", user.getUserType());
                resultData.put("idCard",user.getIdNum());
                resultData.put("email",user.getEmail());
                String token = JWTUtils.createToken(user.getIdNum());
                resultData.put("token",token);
                result.setData(resultData);
                this.request.getSession().setAttribute("USER_LOGIN_STATUS", 11);
                this.request.getSession().setAttribute("USER_LOGIN_USER_NAME", user.getUserName());
                this.request.getSession().setAttribute("USER_LOGIN_idCard", user.getIdNum());
            } else {
                result = RequestResult.errorRes(1);
            }

            return result;


    }


    @RequestMapping({"/login.status"})
    @ResponseBody
    public RequestResult loginStatus(@RequestBody String requestBody) {
        JSONObject jsonObject = JSON.parseObject(requestBody);
        String idCard = jsonObject.getString("idCard");
        log.info("loginStatus param: idCard "+idCard);
        RequestResult result = null;
        if (this.request.getSession().getAttribute("USER_LOGIN_STATUS") != null && (Integer)this.request.getSession().getAttribute("USER_LOGIN_STATUS") != 101) {
            Integer ls = (Integer)this.request.getSession().getAttribute("USER_LOGIN_STATUS");
            String user_name = (String)this.request.getSession().getAttribute("USER_LOGIN_USER_NAME");
            log.info("return user_name:"+user_name);
            if (ls == 11) {
                result = RequestResult.statusCode(1);
            } else {
                result = RequestResult.statusCode(0);
            }

            result.setData(user_name);
        } else {
            result = RequestResult.errorRes(0);
            result.setData("");
        }

        return result;
    }


    @RequestMapping({"/logout"})
    @ResponseBody
    public RequestResult logout(@RequestBody String requestBody) {
        JSONObject jsonObject = JSON.parseObject(requestBody);
        String userName = jsonObject.getString("user_name");
        log.info("logout "+userName);
        RequestResult result = null;
        if (this.request.getSession().getAttribute("USER_LOGIN_STATUS") != null && (Integer)this.request.getSession().getAttribute("USER_LOGIN_STATUS") != 101) {
            this.request.getSession().setAttribute("USER_LOGIN_STATUS", 101);
            this.request.getSession().setAttribute("USER_LOGIN_USER_NAME", "");
            result = RequestResult.errorRes(0);
            result.setData("");
        } else {
            result = RequestResult.errorRes(101);
            result.setData("");
        }

        return result;
    }




}

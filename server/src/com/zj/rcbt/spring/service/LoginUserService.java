package com.zj.rcbt.spring.service;

import com.zj.rcbt.common.utils.MailModel;
import com.zj.rcbt.hibernate.dao.LoginuserDao;
import com.zj.rcbt.hibernate.model.LoginuserBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

@Service("loginuserService")
public class LoginUserService {

    private Logger log =LogManager.getLogger(this.getClass());
    @Autowired
    LoginuserDao loginuserDao;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private SimpleMailMessage simpleMailMessage;


    public int save(LoginuserBean loginuser) {
        loginuser.setUserType(0);
        if (loginuserDao.findByUsername(loginuser.getUserName())!=null){
            return 1;
        }

        if (loginuserDao.findByIDnum(loginuser.getIdNum())!=null){
            return 2;
        }


        if (loginuserDao.findByEmail(loginuser.getEmail())!=null){
            return 4;
        }

        loginuserDao.save(loginuser);
        return 0;
    }

    public int saveedit(LoginuserBean loginuser,String email) {
      log.info("saveedit");

        if (findByemail(email)!=null){
            return 1;
        }

        loginuser.setEmail(email);

        loginuserDao.save(loginuser);
        return 0;
    }

    public LoginuserBean findByemail(String email){
        return  loginuserDao.findByEmail(email);
    }


    public void update(LoginuserBean loginuser) {
        loginuserDao.save(loginuser);
    }


    public LoginuserBean login(String username,String passwd){
        return loginuserDao.findByNameAndPasswd(username,passwd);

    }


    public LoginuserBean findByUserName(String username){
        return loginuserDao.findByUserName(username);

    }
    public LoginuserBean findByIDnum(String id_num){
        return loginuserDao.findByIDnum(id_num);

    }


    public int savepasswordReset(String idnum, String user_e_mail) {
//        LoginuserBean user = findByUserName(userName);
        LoginuserBean user = loginuserDao.findByIDnum(idnum);
        if (user == null) {
            return 4;
        } else if (!user.getEmail().equals(user_e_mail)) {
            return 2;
        } else {
            MailModel mail = new MailModel();
            mail.setSubject("密码重置邮件");
            mail.setToEmails(user_e_mail);
            String newPass = this.getRandomString(6);
            StringBuilder builder = new StringBuilder();
            builder.append(user.getUserName()+" 你好！\r\n");
            builder.append("         您在诸暨市人才补贴申报系统中的个人密码重置为: ");
            builder.append("                         " + newPass + "\r\n");
            builder.append("  请及时修改您的密码");
            String content = builder.toString();
            mail.setContent(content);
            int iRtn = 0;
            user.setPasswd(newPass);
            loginuserDao.save(user);

            try {
                iRtn =sendEmail(mail);
            } catch (Exception var11) {
                log.error(var11);
                var11.printStackTrace();
                return -1;
            }

            if (iRtn == 0) {


                iRtn = 0;
            }

            return iRtn;
        }
    }

    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }


    public int sendEmail(MailModel mail) throws Exception {
        int iRtn = 0;
        MimeMessage message = this.javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            if (mail.getEmailFrom() != null) {
                messageHelper.setFrom(mail.getEmailFrom());
            } else {
                String xx = this.simpleMailMessage.getFrom();
                messageHelper.setFrom(this.simpleMailMessage.getFrom());
            }

            String cid;
            String[] toEmailArray;
            if (mail.getToEmails() == null) {
                toEmailArray = this.simpleMailMessage.getTo();
                messageHelper.setTo(this.simpleMailMessage.getTo());
            } else {
                toEmailArray = mail.getToEmails().split(";");
                List<String> toEmailList = new ArrayList();
                if (toEmailArray == null || toEmailArray.length <= 0) {
                    throw new Exception("收件人邮箱不得为空！");
                }

                String[] var10 = toEmailArray;
                int var9 = toEmailArray.length;
                int var8 = 0;

                while(true) {
                    if (var8 >= var9) {
                        if (toEmailList == null || toEmailList.size() <= 0) {
                            throw new Exception("收件人邮箱不得为空！");
                        }

                        toEmailArray = new String[toEmailList.size()];

                        for(int i = 0; i < toEmailList.size(); ++i) {
                            toEmailArray[i] = (String)toEmailList.get(i);
                        }

                        messageHelper.setTo(toEmailArray);
                        break;
                    }

                    cid = var10[var8];
                    if (cid != null && !cid.equals("")) {
                        toEmailList.add(cid);
                    }

                    ++var8;
                }
            }

            if (mail.getSubject() != null) {
                messageHelper.setSubject(mail.getSubject());
            } else {
                messageHelper.setSubject(this.simpleMailMessage.getSubject());
            }

            messageHelper.setText(mail.getContent(), true);
            Iterator it;
            Map.Entry entry;
            String filePath;
            File file;
            FileSystemResource fileResource;
            if (mail.getPictures() != null) {
                it = mail.getPictures().entrySet().iterator();

                while(it.hasNext()) {
                    entry = (Map.Entry)it.next();
                    cid = (String)entry.getKey();
                    filePath = (String)entry.getValue();
                    if (cid == null || filePath == null) {
                        throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
                    }

                    file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("图片" + filePath + "不存在！");
                    }

                    fileResource = new FileSystemResource(file);
                    messageHelper.addInline(cid, fileResource);
                }
            }

            if (mail.getAttachments() != null) {
                it = mail.getAttachments().entrySet().iterator();

                while(it.hasNext()) {
                    entry = (Map.Entry)it.next();
                    cid = (String)entry.getKey();
                    filePath = (String)entry.getValue();
                    if (cid == null || filePath == null) {
                        throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
                    }

                    file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("附件" + filePath + "不存在！");
                    }

                    fileResource = new FileSystemResource(file);
                    messageHelper.addAttachment(cid, fileResource);
                }
            }

            messageHelper.setSentDate(new Date());
            log.info("send the mail:"+message);

            this.javaMailSender.send(message);
        } catch (MessagingException var11) {
            iRtn = 5;
            var11.printStackTrace();
        }

        return iRtn;
    }




}

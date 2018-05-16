package com.zj.rcbt.spring.controller;

import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.spring.service.ArchivesService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/archives")
public class ArchivesController {

    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    ArchivesService archivesService;

    @RequestMapping({"/get"})
    @ResponseBody
    public Map<String, Object> getArchives(@RequestParam("idCard") String idCard,@RequestParam("status") String status, @RequestParam("page") int page, @RequestParam("limit") int limit) {

        log.info("getArchives  "+idCard);
        List<ArchivesBean> archivesBeanList = archivesService.findByPages( idCard, status,(page - 1) * limit, limit);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ArchivesBean archivesBean : archivesBeanList) {
            Map<String, Object> so = new HashMap<String, Object>();
            so.put("userName",archivesBean.getUserName());
            so.put("idCard",archivesBean.getIdNum());
            so.put("status",archivesBean.getInzhuji());
            list.add(so);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("count", archivesService.findByPagesCount(idCard,status));
        data.put("code", "0");
        data.put("msg", "");



        return data;
    }
}

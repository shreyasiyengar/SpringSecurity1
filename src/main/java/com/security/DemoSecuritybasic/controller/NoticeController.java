package com.security.DemoSecuritybasic.controller;

import com.security.DemoSecuritybasic.model.Notice;
import com.security.DemoSecuritybasic.repository.NoticeRepositpory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticeController {

    @Autowired
    private NoticeRepositpory noticeRepositpory;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotice(){
        List<Notice> notices = noticeRepositpory.findAllActiveNotices();
        if(notices != null){
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)) //this cache hold the data upto 60 seconds
                    //even if the user loads or refresh the page with in 60 seconds the restapi call does not happen
                    .body(notices);
        }else{
            return null;
        }
    }

//    @GetMapping("/notices")
//    public String getNotice(){
//        return "this is notice page";
//    }
}

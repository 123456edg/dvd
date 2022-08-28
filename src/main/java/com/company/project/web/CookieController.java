package com.company.project.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cookie")
public class CookieController {
    
    @RequestMapping("/get")
    public void cookieTest(HttpServletRequest req, HttpServletResponse resp){
        Cookie cookie = new Cookie("name","wbt");
//        cookie.setPath("/");
//        cookie.setDomain(".localhost");
        cookie.setMaxAge(180);
        resp.addCookie(cookie);
    }
}

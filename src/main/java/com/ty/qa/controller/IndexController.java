package com.ty.qa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Enumeration;

/**
 * created by TY on 2019/5/3.
 * 高级二基础功能  core
 */
@Controller
public class IndexController {
    @RequestMapping(path = {"/", "index"})
    @ResponseBody
    public String index(HttpSession httpSession) {
        return "Hello World" + httpSession.getAttribute("msg");
    }

    /*
     *  url带参数的情况
     *  注解{userId} @PathVariable
     * @param
     * @return
     */
    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam("type") int type,
                          @RequestParam(value = "key", defaultValue = "1", required = false) String key) {
        return String.format("Profile Page of %s / %d, type:%d key:%s", groupId, userId, type, key);
    }


    @RequestMapping(path = {"/ftl"}, method = {RequestMethod.GET})
    public String template() {
        return "home";
    }

    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession httpSession,
                          @CookieValue("__guid") String sessionId) {
        StringBuilder sb = new StringBuilder();

        sb.append("__guid: " + sessionId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }

        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                sb.append("Cookie: " + cookie.getName() + "value: " + cookie.getValue());
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getRequestURI() + "<br>");

        response.addCookie(new Cookie("USERNAME", "TY"));
        return sb.toString();

    }

    @RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirect(@PathVariable("code") int code, HttpSession httpSession) {
        httpSession.setAttribute("msg", "jump from redirect");
        RedirectView red = new RedirectView("/", true );
        if(code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;

    }


    @RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if("admin".equals(key)) {
            return "admin";
        }
        throw new IllegalArgumentException("KEY WRONG");

    }
    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }

}

package com.xcy.community.controller;

import com.xcy.community.entity.Page;
import com.xcy.community.service.AlphaService;
import com.xcy.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String sayHello(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value);
        }
        System.out.println(request.getParameter("name1"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>huluhulu</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //  /student?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(Integer page, Integer page_size) {
        System.out.println("method1");
        System.out.println(page);
        System.out.println(page_size);
        return "someStudents";
    }

    @RequestMapping(path="/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudentById(
            @PathVariable(name="id",required = false) String id,
            @RequestParam(name="current", defaultValue = "1") int page,
            @RequestParam(name="limit", defaultValue = "20") int page_size) {
        System.out.println("method2");
        System.out.println("somestudentwithid: " + id);
        System.out.println(page + page_size);
        return "somestudentwithid: " + id;
    }

    //post请求
    @RequestMapping(path="/student", method = RequestMethod.POST)
    public String saveStudent(String name, long age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "demo/view";
    }

    //响应html数据
    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
         ModelAndView mav = new ModelAndView();
         mav.addObject("name", "zhangsan");
         mav.addObject("age", "32");
         mav.setViewName("/demo/view");
         return mav;
    }

    //简化返回html
    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Page page, Model model, Integer times) {
        model.addAttribute("name", page.getFrom());
        model.addAttribute("age", page.getTo());
        return "/demo/view";
    }

    //响应JSON数据(异步请求)
    @RequestMapping(path="/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary", 80000.00);
        System.out.println(emp);
        return emp;
    }

    @RequestMapping(path="/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(@RequestParam(name="id", defaultValue = "3") int id) {

        System.out.println(id);
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary", 80000.00);
        if(id == 1) list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "lisi");
        emp.put("age", 19);
        emp.put("salary", 50000.00);
        if(id == 2) list.add(emp);

        return list;
    }

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody()
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        cookie.setPath("/");
        cookie.setMaxAge(60 * 10);
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "cookie/get", method = RequestMethod.GET)
    @ResponseBody()
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "haha");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}

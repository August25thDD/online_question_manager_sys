package demo.yang.web.controller;

import demo.yang.service.store.CatalogService;
import demo.yang.service.store.CompanyService;
import demo.yang.service.store.CourseService;
import demo.yang.service.store.QuestionService;
import demo.yang.service.store.impl.CatalogServiceImpl;
import demo.yang.service.store.impl.CompanyServiceImpl;
import demo.yang.service.store.impl.CourseServiceImpl;
import demo.yang.service.store.impl.QuestionServiceImpl;
import demo.yang.service.system.DeptService;
import demo.yang.service.system.ModuleService;
import demo.yang.service.system.RoleService;
import demo.yang.service.system.UserService;
import demo.yang.service.system.impl.DeptServiceImpl;
import demo.yang.service.system.impl.ModuleServiceImpl;
import demo.yang.service.system.impl.RoleServiceImpl;
import demo.yang.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-26 20:43
 **/

public class BaseServlet extends HttpServlet {


    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected RoleService roleService;
    protected ModuleService moduleService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
        roleService = new RoleServiceImpl();
        moduleService = new ModuleServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        try {
            Method method = this.getClass().getDeclaredMethod(operation, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



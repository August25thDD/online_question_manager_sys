package demo.yang.web.controller;

import com.github.pagehelper.PageInfo;
import demo.yang.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-28 17:16
 **/

public abstract class BaseServletTest<T, TService> extends HttpServlet {
    protected TService tService;
    protected String jspPath;
    protected String servletUrl;
    protected BaseService<T> baseService;
    @Override

    public void init() throws ServletException {
    }

    public void initPathAndImpl(String servletUrl,TService tService) {
        this.servletUrl = servletUrl;
        jspPath = "/WEB-INF/pages" + servletUrl + "/list.jsp";
        this.tService = tService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            list(req, resp);
        } else if ("toAdd".equals(operation)) {
            toAdd(req, resp);
        } else if ("save".equals(operation)) {
            save(req, resp);
        } else if ("toEdit".equals(operation)) {
            toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            edit(req, resp);
        } else if ("delete".equals(operation)) {
            delete(req, resp);
        }
    }

    protected abstract void toAdd(HttpServletRequest req, HttpServletResponse resp) ;

    protected abstract void toEdit(HttpServletRequest req, HttpServletResponse resp);

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        T t = baseService.findById(id);
        baseService.delete(t);

        resp.sendRedirect(req.getContextPath() + "/store/t?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        T t = BeanUtil.fillBean(req, T.class, "yyyy-MM-dd");
//        baseService.update(t);

        resp.sendRedirect(req.getContextPath() + servletUrl + "?operation=list");

    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        T t = BeanUtil.fillBean(req, T.class, "yyyy-MM-dd");
//        baseService.save(t);
//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + servletUrl + "?operation=list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 7;
        try {//防止前台没有转递参数时，初始化页面和页面大小
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
        }
        try {
            size = Integer.parseInt(req.getParameter("size"));
        } catch (Exception e) {
        }
        PageInfo<T> all = baseService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher(jspPath).forward(req, resp);
    }
}


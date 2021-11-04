package demo.yang.web.controller.system;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import demo.yang.domain.system.AuthorBean;
import demo.yang.domain.system.Role;
import demo.yang.utils.BeanUtil;
import demo.yang.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-26 09:33
 **/

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {

    private void author(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("roleId");
        String[] ids = req.getParameterValues("ids");
        roleService.updateModulesByRoleId(roleId, ids);

        list(req, resp);
    }

    private void toAuthor(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String roleId = req.getParameter("id");
        List<AuthorBean> authorBeans = roleService.findAuthorBeansById(roleId);
        ObjectMapper mapper = new ObjectMapper();
        String authorJson = mapper.writeValueAsString(authorBeans);

        req.setAttribute("roleId", roleId);
        req.setAttribute("authorJson", authorJson);

        req.getRequestDispatcher("/author.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Role role = roleService.findById(id);
        roleService.delete(role);

        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        roleService.update(role);
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }

    /**
     * 跳转前的准备工作
     *
     * @param req
     * @param resp
     */
    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        获取用户选择的id得到修改的对象
        List<Role> all = roleService.findAll();
        Role role = roleService.findById(id);
        req.setAttribute("role", role);
        req.setAttribute("roleList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        roleService.save(role);
//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Role> all = roleService.findAll();
        req.setAttribute("roleList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 7;
        try {//防止前台没有转递参数时，初始化页面和页面大小
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {}
        try {
            size = Integer.parseInt(req.getParameter("size"));
        } catch (Exception e) {}

        PageInfo<Role> all = roleService.findAll(page, size);

        //将数据保存到指定的位置
        req.setAttribute("page", all);

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req, resp);

    }
}

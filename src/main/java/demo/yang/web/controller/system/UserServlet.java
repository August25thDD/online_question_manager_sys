package demo.yang.web.controller.system;

import com.github.pagehelper.PageInfo;
import demo.yang.domain.system.Dept;
import demo.yang.domain.system.Role;
import demo.yang.domain.system.User;
import demo.yang.utils.BeanUtil;
import demo.yang.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/user")
public class UserServlet extends BaseServlet {

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
//        获取前端勾选的roleId
        String[] roleIds = req.getParameterValues("roleIds");//        获取相同name的多个values

//        System.out.println(userId);
//        System.out.println(roleIds.length);
        userService.updateRole(userId,roleIds);
        req.setAttribute("msg","修改成功");
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }


    private void userRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        User user = userService.findById(userId);
        req.setAttribute("user",user);
        List<Role> all = roleService.findAllRoleByUserId(userId);
        req.setAttribute("roleList", all);

        req.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(req, resp);
    }


    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");


    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = userService.findById(id);
        userService.delete(user);

        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        userService.update(user);

        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");

    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        获取用户选择的id得到修改的对象
        List<Dept> all = deptService.findAll();
        User user = userService.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("deptList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        userService.save(user);
//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(req, resp);
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
        } catch (Exception e) {}

        PageInfo<User> all = userService.findAll(page, size);

        //将数据保存到指定的位置
        req.setAttribute("page", all);

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(req, resp);
    }
}

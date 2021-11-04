package demo.yang.web.controller.system;

import com.github.pagehelper.PageInfo;
import demo.yang.domain.system.Module;
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

@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Module module = moduleService.findById(id);
        moduleService.delete(module);

        resp.sendRedirect(req.getContextPath() + "/system/module?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Module module = BeanUtil.fillBean(req, Module.class, "yyyy-MM-dd");
        moduleService.update(module);

        resp.sendRedirect(req.getContextPath() + "/system/module?operation=list");
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
        List<Module> all = moduleService.findAll();
        Module module = moduleService.findById(id);
        req.setAttribute("module", module);
        req.setAttribute("moduleList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Module module = BeanUtil.fillBean(req, Module.class, "yyyy-MM-dd");
        moduleService.save(module);
//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + "/system/module?operation=list");
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Module> all = moduleService.findAll();
        req.setAttribute("moduleList", all);
        req.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(req, resp);
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

        PageInfo<Module> all = moduleService.findAll(page, size);

        //将数据保存到指定的位置
        req.setAttribute("page", all);

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(req, resp);

    }
}

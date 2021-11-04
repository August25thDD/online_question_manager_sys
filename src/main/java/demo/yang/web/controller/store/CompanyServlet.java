package demo.yang.web.controller.store;

import com.github.pagehelper.PageInfo;
import demo.yang.domain.store.Company;
import demo.yang.utils.BeanUtil;
import demo.yang.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-26 09:33
 **/
@WebServlet("/store/company")
public class CompanyServlet extends BaseServlet {

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Company company = companyService.findById(id);
        companyService.delete(company);

        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.update(company);

        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");

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
        Company company = companyService.findById(id);
        req.setAttribute("company", company);
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.save(company);
//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");
    }

    protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req, resp);
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
        PageInfo<Company> all = companyService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req, resp);
    }
}

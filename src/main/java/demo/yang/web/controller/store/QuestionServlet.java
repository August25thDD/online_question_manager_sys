package demo.yang.web.controller.store;

import com.github.pagehelper.PageInfo;
import demo.yang.domain.store.Catalog;
import demo.yang.domain.store.Company;
import demo.yang.domain.store.Question;
import demo.yang.utils.BeanUtil;
import demo.yang.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @program: maven_project
 * @description:
 * @author: Mr.Yang
 * @create: 2021-10-26 09:33
 **/
@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {

    private void toTestUpload(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(request,response);
    }

    private void testUpload(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(request)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if (!item.isFormField()) {
                    item.write(new File(request.getServletContext().getRealPath("upload"), item.getName()));
                }
            }
        }


    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Question question = questionService.findById(id);
        questionService.delete(question);

        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Question question = BeanUtil.fillBean(req, Question.class, "yyyy-MM-dd");
        questionService.update(question);

        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");

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
        Question question = questionService.findById(id);
        req.setAttribute("question", question);
//        将所属企业和所属类别查询出来并携带到前端进行展示
        List<Catalog> catalogList = catalogService.findAll();
        List<Company> companyList = companyService.findAll();
        req.setAttribute("catalogList", catalogList);
        req.setAttribute("companyList", companyList);


        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (ServletFileUpload.isMultipartContent(req)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            List<FileItem> fileItems = fileUpload.parseRequest(req);

            Question question = BeanUtil.fillBean(fileItems, Question.class);

            questionService.save(question);

            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    item.write(new File(req.getServletContext().getRealPath("upload"), item.getName()));
                }
            }
        }

//       重定向发送list请求并跳转到查询页
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //        将所属企业和所属类别查询出来并携带到前端进行展示
        List<Catalog> catalogList = catalogService.findAll();
        List<Company> companyList = companyService.findAll();

        req.setAttribute("catalogList", catalogList);
        req.setAttribute("companyList", companyList);

        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req, resp);
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
        PageInfo<Question> all = questionService.findAll(page, size);

        //将数据保存到指定的位置
        req.setAttribute("page", all);

        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req, resp);

    }
}

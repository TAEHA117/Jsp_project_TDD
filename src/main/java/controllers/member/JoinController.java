package controllers.member;

import static commons.ScriptUtil.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.member.JoinService;
import models.member.ServiceManager;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/member/join")
public class JoinController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/templates/member/join.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JoinService service = ServiceManager.getInstance().joinService();
            service.join(req);

            String url = req.getContextPath() + "/member/login";
            go(resp, url,"parent");

            // resp.sendRedirect(req.getContextPath() + "/member/login");

        } catch (RuntimeException e) { // 다형성을 위해서 -> RuntimeException 사용
            alertError(resp, e);

//            resp.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = resp.getWriter();
//            out.printf("<script>alert('%s');</script>", e.getMessage()); // history.back();을 사용하기보다는 현재페이지 내에서 동일하게 나오는게 좋다. <iframe>
//
//            e.printStackTrace();
        }
    }
}

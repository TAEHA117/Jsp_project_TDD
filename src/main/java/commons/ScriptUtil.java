package commons;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ScriptUtil {
    // 정적인 형태로 정리할 것 이다.
    public static void alertError(HttpServletResponse resp, Exception e) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<script>alert('%s');</script>", e.getMessage());
        e.printStackTrace();
    }
}

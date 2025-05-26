package filters;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        boolean isPublic = uri.equals(req.getContextPath() + "/") ||
                uri.endsWith("/login") ||
                uri.endsWith("/register") ||
                uri.endsWith("/home") ||
                uri.contains("/css") ||
                uri.contains("/js") ||
                uri.endsWith(".png") ||
                uri.endsWith(".jpg");


        if (user == null && !isPublic) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (uri.startsWith(req.getContextPath() + "/admin")) {
            if (user == null || user.getMaVaiTro() != 0) {
                resp.sendRedirect(req.getContextPath() + "/access-denied");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}

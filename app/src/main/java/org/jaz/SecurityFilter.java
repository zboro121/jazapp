package org.jaz;

import org.jaz.domain.User;

import javax.faces.application.ResourceHandler;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*")
public class SecurityFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (isUserAuthenticated(req.getSession()) || isSiteAllowed(req) || isResourceReq(req)) {
            chain.doFilter(req, res);
        }
        else{
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        }
    }

    private boolean isResourceReq(HttpServletRequest req) {

        return req.getRequestURI().startsWith(req.getContextPath()+ ResourceHandler.RESOURCE_IDENTIFIER+ "/");
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        return "/login.xhtml".equals(req.getServletPath())
                || "/register.xhtml".equals(req.getServletPath());
    }

    private boolean isUserAuthenticated(HttpSession session) {
        if (session!=null){
            if (session.getAttribute("user")!=null){
                return true;
            }
        }
        return false;
    }
}

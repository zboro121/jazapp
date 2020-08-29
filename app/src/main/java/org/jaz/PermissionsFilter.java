package org.jaz;

import org.jaz.domain.User;
import org.jaz.services.UserSearchService;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

//@WebFilter({"/categories/edit.xhtml"})
public class PermissionsFilter extends HttpFilter {

    @Inject
    private UserSearchService userSearchService;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (userHasAdminPrivileges(req.getSession())) {
            chain.doFilter(req, res);
        }
        else{
            res.sendError(403,"Access denied");
        }
    }

    private boolean userHasAdminPrivileges(HttpSession session) {
        UserContext userContext = (UserContext) session.getAttribute("user");
        if (userContext != null) {
            Optional<User> currentUser = userSearchService.findUser(userContext.getUserId());
            if (currentUser.isPresent()) {
                if (currentUser.get().getRole().equals("ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}

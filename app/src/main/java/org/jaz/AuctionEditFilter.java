package org.jaz;

import org.jaz.domain.Auction;
import org.jaz.domain.User;
import org.jaz.services.AuctionService;
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

//@WebFilter({"/auctions/edit.xhtml"})
public class AuctionEditFilter extends HttpFilter {

    @Inject
    private UserSearchService userSearchService;

    @Inject
    private AuctionService auctionService;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (userCreatesAuction(req)||userOwnsAuction(req)) {
            chain.doFilter(req, res);
        }
        else{
            res.sendError(403,"Access denied");
        }
    }

    private boolean userCreatesAuction(HttpServletRequest request){
        if (request.getParameter("auctionId")==null || request.getParameter("auctionId").trim().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean userOwnsAuction(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserContext userContext = (UserContext) session.getAttribute("user");
        if (request.getParameter("auctionId")!=null && !request.getParameter("auctionId").trim().equals("")) {
            long auctionId = Long.parseLong(request.getParameter("auctionId"));
            if (userContext != null) {
                Optional<User> currentUser = userSearchService.findUser(userContext.getUserId());
                Optional<Auction> auction = auctionService.getAuction(auctionId);
                if (currentUser.isPresent() && auction.isPresent()) {
                    if (auction.get().getOwner().getId().equals(currentUser.get().getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

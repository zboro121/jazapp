package org.jaz.webapp.auction;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Category;
import org.jaz.domain.Photo;
import org.jaz.domain.User;
import org.jaz.services.AuctionService;
import org.jaz.services.CategoryService;
import org.jaz.services.UserSearchService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Named
@RequestScoped
public class AuctionController {

    @Inject
    private AuctionService auctionService;

    @Inject
    private UserSearchService userSearchService;

    @Inject
    private CategoryService categoryService;


    //creates or edits auction
    public String Create(AuctionRequest request){
        Optional<User> user = userSearchService.getUserFromSession();
        if (user.isPresent()) {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String auctionId = req.getParameter("auctionId");

            if (auctionId!=null && !auctionId.isBlank()){
                    if (StringUtils.isNumeric(auctionId)) {
                        auctionService.editAuction(Long.parseLong(auctionId), request);
                    }
            }
            else{
                Auction auction = new Auction(request.getTitle(),request.getDescription(),request.getPrice(),request.getCategory());
                Photo photo1 = new Photo(request.getPhoto1());
                Photo photo2 = new Photo(request.getPhoto2());
                Photo photo3 = new Photo(request.getPhoto3());
                auctionService.createAuction(user.get(),auction);
                auctionService.addPhoto(auction,photo1);
                auctionService.addPhoto(auction,photo2);
                auctionService.addPhoto(auction,photo3);
            }
        }
        return "/index.xhtml?faces-redirect=true";
    }


    public List<Auction> getAllAuctions(){ return auctionService.getAllAuctions(); }
    public List<Auction> getUserAuctions(){
        Optional<User> currentUser = userSearchService.getUserFromSession();
        if (currentUser.isPresent()){
            return auctionService.getUserAuctions(currentUser.get());
        }
        return null;
    }
    public List<Category> getAllCategories() {return categoryService.getAllCategories();}

}

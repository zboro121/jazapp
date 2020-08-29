package org.jaz.webapp.auction;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Category;
import org.jaz.domain.User;
import org.jaz.services.AuctionService;
import org.jaz.services.UserSearchService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Optional;

@Named
@ManagedBean
@RequestScoped
public class AuctionRequest {

    @Inject
    private AuctionService auctionService;

    private String id;

    @NotEmpty
    @Size(max = 25)
    private String title;

    @NotEmpty
    @Size(max=200)
    private String description;

    private float price;

    @NotNull
    private Category category;

    @NotEmpty
    @Size(max = 500)
    private String photo1;

    @NotEmpty
    @Size(max = 500)
    private String photo2;

    @NotEmpty
    @Size(max = 500)
    private String photo3;

    public AuctionRequest() {
    }

    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String auctionId = req.getParameter("auctionId");
        if (auctionId!=null){
            if (StringUtils.isNumeric(auctionId)){
                Optional<Auction> auction = auctionService.getAuction(Long.parseLong(auctionId));
                if (auction.isPresent())
                {
                    id = auctionId;
                    title = auction.get().getTitle();
                    description = auction.get().getDescription();
                    price = auction.get().getPrice();
                    category = auction.get().getCategory();
                    photo1 = auction.get().getPhotos().get(0).getUrl();
                    photo2 = auction.get().getPhotos().get(1).getUrl();
                    photo3 = auction.get().getPhotos().get(2).getUrl();
                    req.setAttribute("auctionId",auctionId);
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }
}

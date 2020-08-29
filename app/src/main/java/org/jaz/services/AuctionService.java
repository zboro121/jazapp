package org.jaz.services;

import org.jaz.domain.Auction;
import org.jaz.domain.Photo;
import org.jaz.domain.User;
import org.jaz.webapp.auction.AuctionRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuctionService {

    @Inject
    private UserSearchService userSearchService;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addPhoto(Auction auction, Photo photo){
        photo.setAuction(auction);
        em.persist(photo);
    }

    @Transactional
    public void createAuction(User user, Auction auction){
        auction.setOwner(user);
        em.persist(auction);
    }
    @Transactional
    public void editAuction(long id, AuctionRequest auctionRequest){
        Optional<Auction> auction = getAuction(id);
        Optional<User> currentUser = userSearchService.getUserFromSession();
        if (auction.isPresent() && currentUser.isPresent()) {
            if (auction.get().getOwner().getId().equals(currentUser.get().getId())){
            auction.get().setTitle(auctionRequest.getTitle());
            auction.get().setDescription(auctionRequest.getDescription());
            auction.get().setPrice(auctionRequest.getPrice());
            auction.get().setCategory(auctionRequest.getCategory());
            auction.get().getPhotos().get(0).setUrl(auctionRequest.getPhoto1());
            auction.get().getPhotos().get(1).setUrl(auctionRequest.getPhoto2());
            auction.get().getPhotos().get(2).setUrl(auctionRequest.getPhoto3());
            em.persist(auction.get());

            }
        }
    }

    public List<Auction> getAllAuctions(){
        return em.createQuery("from Auction",Auction.class)
                .getResultList();
    }

    public Optional<Auction> getAuction(long id){
        return em.createQuery("from Auction where id = :id",Auction.class).setParameter("id",id)
                .getResultList().stream()
                .findFirst();
    }

    public List<Auction> getUserAuctions(User user){
        return em.createQuery("from Auction where owner.id = :user_id ",Auction.class).setParameter("user_id", user.getId())
                .getResultList();
    }
}

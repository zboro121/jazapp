package org.jaz.domain;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;
    @Column(name= "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    public Photo() {
    }

    public Photo(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}

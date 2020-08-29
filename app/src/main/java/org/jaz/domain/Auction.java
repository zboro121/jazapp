package org.jaz.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;
    @Column(name= "title")
    private String title;
    @Column(name= "description")
    private String description;
    @Column(name= "price")
    private float price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "auction", fetch = FetchType.EAGER)
    private List<Photo> photos;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Auction() {
    }

    public Auction(String title, String description, float price, Category category, List<Photo> photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.photos = photos;
    }

    public Auction(String title, String description, float price, Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        photos = new ArrayList<Photo>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }


}

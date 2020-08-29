package org.jaz.domain;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "category")
    private List<Auction> auctions;

    public Category() {
    }

    public Category(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Category) && (id != null)
                ? id.equals(((Category) object).id)
                : (object == this);
    }

    @Override
    public String toString() {
        return name;
    }
}

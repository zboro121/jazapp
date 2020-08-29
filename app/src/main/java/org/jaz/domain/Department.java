package org.jaz.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Category> categories;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + (int) (id ^ (id >>> 32));
//        return result;
//    }
//
    @Override
    public boolean equals(Object object) {
        return (object instanceof Department) && (id != null)
                ? id.equals(((Department) object).id)
                : (object == this);
    }

    @Override
    public String toString() {
        return name;
    }
}

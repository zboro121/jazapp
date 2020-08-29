package org.jaz.services;

import org.jaz.domain.Auction;
import org.jaz.domain.Category;
import org.jaz.domain.Department;
import org.jaz.domain.User;
import org.jaz.webapp.auction.AuctionRequest;
import org.jaz.webapp.category.CategoryRequest;
import org.jaz.webapp.departament.DepartmentRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryService {

    @PersistenceContext
    private EntityManager em;

    public List<Category> getAllCategories(){
        return em.createQuery("from Category ", Category.class)
                .getResultList();
    }
    public Optional<Category> getCategory(long id){
        return em.createQuery("from Category where id = :id",Category.class).setParameter("id",id)
                .getResultList().stream()
                .findFirst();
    }
    @Transactional
    public void editCategory(long id, CategoryRequest request){
        Optional<Category> category = getCategory(id);
        if (category.isPresent()) {
            category.get().setName(request.getName());
            category.get().setDepartment(request.getDepartment());
            em.persist(category.get());
        }
    }
    @Transactional
    public void createCategory(Category category){
        em.persist(category);
    }
}

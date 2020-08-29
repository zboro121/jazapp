package org.jaz.services;

import org.jaz.domain.Auction;
import org.jaz.domain.Department;
import org.jaz.webapp.departament.DepartmentRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartmentService {

    @PersistenceContext
    private EntityManager em;

    public List<Department> getAllDepartments(){
        return em.createQuery("from Department ", Department.class)
                .getResultList();
    }
    public Optional<Department> getDepartment(long id){
        return em.createQuery("from Department where id = :id",Department.class).setParameter("id",id)
                .getResultList().stream()
                .findFirst();
    }
    @Transactional
    public void editDepartment(long id,DepartmentRequest request){
        Optional<Department> department = getDepartment(id);
        if (department.isPresent()) {
            department.get().setName(request.getName());
            em.persist(department.get());
        }
    }
    @Transactional
    public void createDepartment(Department department){
        em.persist(department);
    }
}

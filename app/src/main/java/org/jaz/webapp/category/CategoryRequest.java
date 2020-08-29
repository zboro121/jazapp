package org.jaz.webapp.category;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Category;
import org.jaz.domain.Department;
import org.jaz.services.CategoryService;
import org.jaz.services.DepartmentService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Optional;

@Named
@ManagedBean
//@RequestScoped
@ViewScoped
public class CategoryRequest implements Serializable {

    @Inject
    private CategoryService categoryService;

    private String id;
    @NotEmpty
    @Size(max = 25)
    private String name;
    @NotNull
    private Department department;

    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String categoryId = req.getParameter("categoryId");
        if (categoryId!=null){
            if (StringUtils.isNumeric(categoryId)){
                Optional<Category> category = categoryService.getCategory(Long.parseLong(categoryId));
                if (category.isPresent())
                {
                    id = categoryId;
                    name = category.get().getName();
                    department = category.get().getDepartment();
                    req.setAttribute("categoryId",categoryId);
                }
            }
        }
    }

    public String getId() {
        return id;
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
}


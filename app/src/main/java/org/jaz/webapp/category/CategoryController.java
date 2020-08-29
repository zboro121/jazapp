package org.jaz.webapp.category;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Category;
import org.jaz.domain.Department;
import org.jaz.domain.User;
import org.jaz.services.AuctionService;
import org.jaz.services.CategoryService;
import org.jaz.services.DepartmentService;
import org.jaz.services.UserSearchService;
import org.jaz.webapp.auction.AuctionRequest;
import org.jaz.webapp.departament.DepartmentRequest;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Named
@RequestScoped
public class CategoryController {

    @Inject
    private CategoryService categoryService;
    @Inject
    private DepartmentService departmentService;


    //creates or edits auction
    public String Create(CategoryRequest request){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String categoryId = req.getParameter("categoryId");

        if (categoryId!=null && !categoryId.isBlank()){
            if (StringUtils.isNumeric(categoryId)) {
                categoryService.editCategory(Long.parseLong(categoryId), request);
            }
        }
        else{
            Category category = new Category(request.getName(),request.getDepartment());
            categoryService.createCategory(category);
        }
        return "/categories/list.xhtml?faces-redirect=true";
    }


    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    public List<Department> getAllDepartments(){return  departmentService.getAllDepartments();}
}


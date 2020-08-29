package org.jaz.webapp.departament;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Department;
import org.jaz.domain.User;
import org.jaz.services.AuctionService;
import org.jaz.services.DepartmentService;
import org.jaz.services.UserSearchService;
import org.jaz.webapp.auction.AuctionRequest;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Named
@RequestScoped
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;


    //creates or edits auction
    public String Create(DepartmentRequest request){
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String departmentId = req.getParameter("departmentId");

            if (departmentId!=null && !departmentId.isBlank()){
                if (StringUtils.isNumeric(departmentId)) {
                    departmentService.editDepartment(Long.parseLong(departmentId), request);
                }
            }
            else{
                Department department = new Department(request.getName());
                departmentService.createDepartment(department);
            }
        return "/departments/list.xhtml?faces-redirect=true";
    }


    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
}

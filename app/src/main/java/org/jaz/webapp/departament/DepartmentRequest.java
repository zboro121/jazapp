package org.jaz.webapp.departament;

import org.apache.commons.lang3.StringUtils;
import org.jaz.domain.Auction;
import org.jaz.domain.Department;
import org.jaz.services.DepartmentService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

@Named
@ManagedBean
@RequestScoped
public class DepartmentRequest {

    @Inject
    private DepartmentService departmentService;

    private String id;
    @NotEmpty
    @Size(max = 25)
    private String name;

    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String departmentId = req.getParameter("departmentId");
        if (departmentId!=null){
            if (StringUtils.isNumeric(departmentId)){
                Optional<Department> department = departmentService.getDepartment(Long.parseLong(departmentId));
                if (department.isPresent())
                {
                    id = departmentId;
                    name = department.get().getName();
                    req.setAttribute("departmentId",departmentId);
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
}

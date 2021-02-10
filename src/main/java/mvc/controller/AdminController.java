package mvc.controller;

import mvc.model.dto.AdminDTO;
import mvc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Returns /addadmin by GET method.
     */
    @GetMapping("/addadmin")
    public ModelAndView getAddAdmin(){
        return new ModelAndView("addadmin","newAdmin", new AdminDTO());
    }

    /**
     * Executes addition new admin by POST method.
     */
    @PostMapping("/addadmin")
    public String addNewAdmin(@ModelAttribute AdminDTO adminDTO){
        adminService.addAdminS(adminDTO);
        return "admin-success";
    }

    /**
     * Returns all admins by GET method.
     */
    @GetMapping("/admins")
    public ModelAndView getAllAdmins(){
        List<AdminDTO> adminDTOList = adminService.getAllAdminsS();
        return new ModelAndView("admins","adminList", adminDTOList);
    }

    /**
     * Executes removal admin by POST method.
     */
    @PostMapping("/deleteadmin")
    public String deleteAdmin(@ModelAttribute AdminDTO adminDTO){
        adminService.deleteAdminByIdS(adminDTO.getId());
        return "home";
    }
}

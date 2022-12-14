/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.controller;


import javax.annotation.Resource;
import org.company.portal.web.validator.UserValidator;
import org.company.business.domain.User;
import org.company.business.service.UserService;
import org.company.business.util.dto.ChangePasswordDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Edward Zengeni
 */
@Controller
@RequestMapping("/admin")
public class ManageUserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private UserValidator userValidator;

    @RequestMapping(method = RequestMethod.GET, value = "/managepassword")
    public String changeUserPassword(@RequestParam String id, ModelMap model) {
        User user = userService.get(id);
        ChangePasswordDTO userProfile = new ChangePasswordDTO(user);
        model.addAttribute("pageTitle", "HRIS:"+user.getUsername()+" Change Password");
        model.addAttribute("item", userProfile);
        return "admin/managePasswordForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/managepassword")
    public String changeUser(ModelMap model, @ModelAttribute("item") ChangePasswordDTO details, BindingResult result){
        userValidator.manageChangepassword(details, result);
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS:Change Password");
            model.addAttribute("item", details);
            return "admin/managePasswordForm";
        }
        userService.changePassword(details.getInstance(details));
        return "redirect:user/user.list?type=1";
    }
}
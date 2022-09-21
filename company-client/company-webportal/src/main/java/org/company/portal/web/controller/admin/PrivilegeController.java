package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.PrivilegeValidator;
import org.company.business.domain.Privilege;
import org.company.business.service.PrivilegeService;
import org.company.business.util.dto.ItemDeleteDTO;
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
@RequestMapping("/admin/privilege")
public class PrivilegeController extends BaseController{

    @Resource
    private PrivilegeService privilegeService;
    @Resource
    private PrivilegeValidator privilegeValidator;

    @RequestMapping(value = "/privilege.form", method = RequestMethod.GET)
    public String privilegeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit Privileges");
        Privilege p = new Privilege();
        if (id != null) {
            p = privilegeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "privilege.list?type=3");
        return "admin/privilegeForm";
    }

    @RequestMapping(value = "/privilege.form", method = RequestMethod.POST)
    public String savePrivileges(@ModelAttribute("item") @Valid Privilege privilege, BindingResult result, ModelMap model) {
        privilegeValidator.validate(privilege, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit Privileges");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", privilege);
            return "admin/privilegeForm";
        }
        privilegeService.save(privilege);
        return "redirect: privilege.list?type=1";
    }

    @RequestMapping(value = {"/privilege.list", "/"}, method = RequestMethod.GET)
    public String privilegeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Privileges List");
        model.addAttribute("items", privilegeService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/privilegeList";
    }

    @RequestMapping(value = "privilege.delete", method = RequestMethod.GET)
    public String getPrivilegesDeleteForm(@RequestParam("id") String id, ModelMap model) {
        Privilege privilege = privilegeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, privilege.getName(), "privilege.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + privilege.getName() + " Privileges");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "privilege.delete", method = RequestMethod.POST)
    public String deletePrivileges(@Valid ItemDeleteDTO dto) {
        privilegeService.delete(privilegeService.get(dto.getId()));
        return "redirect:privilege.list?type=2";
    }

}

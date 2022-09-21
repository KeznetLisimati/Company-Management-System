package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.ContactTypeValidator;
import org.company.business.domain.ContactType;
import org.company.business.service.ContactTypeService;
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
@RequestMapping("/admin/contactType")
public class ContactTypeController extends BaseController{

    @Resource
    private ContactTypeService contactTypeService;
    @Resource
    private ContactTypeValidator contactTypeValidator;

    @RequestMapping(value = "/contactType.form", method = RequestMethod.GET)
    public String contactTypeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit ContactTypes");
        ContactType p = new ContactType();
        if (id != null) {
            p = contactTypeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "contactType.list?type=3");
        return "admin/contactTypeForm";
    }

    @RequestMapping(value = "/contactType.form", method = RequestMethod.POST)
    public String saveContactTypes(@ModelAttribute("item") @Valid ContactType contactType, BindingResult result, ModelMap model) {
        contactTypeValidator.validate(contactType, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit ContactTypes");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", contactType);
            return "admin/contactTypeForm";
        }
        contactTypeService.save(contactType);
        return "redirect: contactType.list?type=1";
    }

    @RequestMapping(value = {"/contactType.list", "/"}, method = RequestMethod.GET)
    public String contactTypeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::ContactTypes List");
        model.addAttribute("items", contactTypeService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/contactTypeList";
    }

    @RequestMapping(value = "contactType.delete", method = RequestMethod.GET)
    public String getContactTypesDeleteForm(@RequestParam("id") String id, ModelMap model) {
        ContactType contactType = contactTypeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, contactType.getName(), "contactType.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + contactType.getName() + " ContactTypes");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "contactType.delete", method = RequestMethod.POST)
    public String deleteContactTypes(@Valid ItemDeleteDTO dto) {
        contactTypeService.delete(contactTypeService.get(dto.getId()));
        return "redirect:contactType.list?type=2";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.controller.company;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.business.domain.Company;
import org.company.business.domain.CompanyContact;
import org.company.business.service.CompanyContactService;
import org.company.business.service.CompanyService;
import org.company.business.service.ContactTypeService;
import org.company.business.util.dto.ItemDeleteDTO;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.CompanyContactValidator;
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
@RequestMapping("/company/contact")
public class CompanyContactController extends BaseController {

    @Resource
    private CompanyContactService companyContactService;
    @Resource
    private CompanyContactValidator companyContactValidator;
    @Resource
    private ContactTypeService contactTypeService;
    @Resource
    private CompanyService companyService;

    private String setUpModel(ModelMap model, CompanyContact item) {
        model.addAttribute("pageTitle", appPrefix + "Create/ Edit Company Contact Details");
        model.addAttribute("item", item);
        model.addAttribute("contactType", contactTypeService.getAll());
        model.addAttribute("company", companyService.get(item.getCompany().getCompanyId()));
        model.addAttribute("itemDelete", "../dashboard/profile.htm?type=3&id="+ item.getCompany().getCompanyId());
        return "company/contactForm";
    }

    @RequestMapping(value = "/item.form", method = RequestMethod.GET)
    public String getItemForm(ModelMap model, @RequestParam(required = false) String id, @RequestParam(required = false) String companyId) {
        CompanyContact contact;
        if (id != null) {
            contact = companyContactService.get(id);
            return setUpModel(model, contact);
        }
        contact = new CompanyContact(companyService.get(companyId));
        return setUpModel(model, contact);
    }

    @RequestMapping(value = "/item.form", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("item") @Valid CompanyContact companyContact, BindingResult result, ModelMap model) {
        companyContactValidator.validateAll(companyContact, result);
        if (result.hasErrors()) {
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());
            setUpModel(model, companyContact);
            return "company/contactForm";
        }
        companyContactService.save(companyContact);
        return "redirect:../dashboard/profile.htm?type=1&id=" + companyContact.getCompany().getCompanyId();
    }

    @RequestMapping(value = "item.delete", method = RequestMethod.GET)
    public String getDeleteForm(@RequestParam("id") String id, ModelMap model) {
        CompanyContact companyContact = companyContactService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, companyContact.getCompany().getName()+" Contact item", "../dashboard/profile.htm?type=3&id="+companyContact.getCompany().getCompanyId());
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", appPrefix+"Delete " + companyContact.getCompany().getName() + " Contact");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "item.delete", method = RequestMethod.POST)
    public String deleteItem(@Valid ItemDeleteDTO dto) {
        CompanyContact contact = companyContactService.get(dto.getId());
        Company p = contact.getCompany();
        companyContactService.delete(contact);
        return "redirect:../dashboard/profile.htm?type=2&id=" + p.getCompanyId();
    }
}
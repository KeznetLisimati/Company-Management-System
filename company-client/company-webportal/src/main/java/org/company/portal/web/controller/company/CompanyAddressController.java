/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.controller.company;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.business.domain.Company;
import org.company.business.domain.CompanyAddress;
import org.company.business.service.CompanyAddressService;
import org.company.business.service.CompanyService;
import org.company.business.service.AddressTypeService;
import org.company.business.util.dto.ItemDeleteDTO;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.CompanyAddressValidator;
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
@RequestMapping("/company/address")
public class CompanyAddressController extends BaseController {

    @Resource
    private CompanyAddressService companyAddressService;
    @Resource
    private CompanyAddressValidator companyAddressValidator;
    @Resource
    private AddressTypeService addressTypeService;
    @Resource
    private CompanyService companyService;

    private String setUpModel(ModelMap model, CompanyAddress item) {
        model.addAttribute("pageTitle", appPrefix + "Create/ Edit Company Address Details");
        model.addAttribute("item", item);
        model.addAttribute("addressType", addressTypeService.getAll());
        model.addAttribute("company", companyService.get(item.getCompany().getCompanyId()));
        model.addAttribute("itemDelete", "../dashboard/profile.htm?type=3&id="+ item.getCompany().getCompanyId());
        return "company/addressForm";
    }

    @RequestMapping(value = "/item.form", method = RequestMethod.GET)
    public String getItemForm(ModelMap model, @RequestParam(required = false) String id, @RequestParam(required = false) String companyId) {
        CompanyAddress address;
        if (id != null) {
            address = companyAddressService.get(id);
            return setUpModel(model, address);
        }
        address = new CompanyAddress(companyService.get(companyId));
        return setUpModel(model, address);
    }

    @RequestMapping(value = "/item.form", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("item") @Valid CompanyAddress companyAddress, BindingResult result, ModelMap model) {
        companyAddressValidator.validateAll(companyAddress, result);
        if (result.hasErrors()) {
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());
            setUpModel(model, companyAddress);
            return "company/addressForm";
        }
        companyAddressService.save(companyAddress);
        return "redirect:../dashboard/profile.htm?type=1&id=" + companyAddress.getCompany().getCompanyId();
    }

    @RequestMapping(value = "item.delete", method = RequestMethod.GET)
    public String getDeleteForm(@RequestParam("id") String id, ModelMap model) {
        CompanyAddress companyAddress = companyAddressService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, companyAddress.getCompany().getName()+" Address item", "../dashboard/profile.htm?type=3&id="+companyAddress.getCompany().getCompanyId());
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", appPrefix+"Delete " + companyAddress.getCompany().getName() + " Address");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "item.delete", method = RequestMethod.POST)
    public String deleteItem(@Valid ItemDeleteDTO dto) {
        CompanyAddress address = companyAddressService.get(dto.getId());
        Company p = address.getCompany();
        companyAddressService.delete(address);
        return "redirect:../dashboard/profile.htm?type=2&id=" + p.getCompanyId();
    }
}
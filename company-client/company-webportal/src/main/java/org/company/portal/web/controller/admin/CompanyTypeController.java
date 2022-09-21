package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.CompanyTypeValidator;
import org.company.business.domain.CompanyType;
import org.company.business.service.CompanyTypeService;
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
@RequestMapping("/admin/companyType")
public class CompanyTypeController extends BaseController{

    @Resource
    private CompanyTypeService companyTypeService;
    @Resource
    private CompanyTypeValidator companyTypeValidator;

    @RequestMapping(value = "/companyType.form", method = RequestMethod.GET)
    public String companyTypeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit CompanyTypes");
        CompanyType p = new CompanyType();
        if (id != null) {
            p = companyTypeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "companyType.list?type=3");
        return "admin/companyTypeForm";
    }

    @RequestMapping(value = "/companyType.form", method = RequestMethod.POST)
    public String saveCompanyTypes(@ModelAttribute("item") @Valid CompanyType companyType, BindingResult result, ModelMap model) {
        companyTypeValidator.validate(companyType, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit CompanyTypes");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", companyType);
            return "admin/companyTypeForm";
        }
        companyTypeService.save(companyType);
        return "redirect: companyType.list?type=1";
    }

    @RequestMapping(value = {"/companyType.list", "/"}, method = RequestMethod.GET)
    public String companyTypeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::CompanyTypes List");
        model.addAttribute("items", companyTypeService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/companyTypeList";
    }

    @RequestMapping(value = "companyType.delete", method = RequestMethod.GET)
    public String getCompanyTypesDeleteForm(@RequestParam("id") String id, ModelMap model) {
        CompanyType companyType = companyTypeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, companyType.getName(), "companyType.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + companyType.getName() + " CompanyTypes");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "companyType.delete", method = RequestMethod.POST)
    public String deleteCompanyTypes(@Valid ItemDeleteDTO dto) {
        companyTypeService.delete(companyTypeService.get(dto.getId()));
        return "redirect:companyType.list?type=2";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.company.portal.web.controller.company;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.business.domain.Company;
import org.company.business.domain.CompanyBankDetail;
import org.company.business.service.AccountTypeService;
import org.company.business.service.CompanyBankDetailService;
import org.company.business.service.CompanyService;
import org.company.business.util.dto.ItemDeleteDTO;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.CompanyBankDetailValidator;
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
@RequestMapping("/company/bank")
public class CompanyBankDetailController extends BaseController {
@Resource
    private CompanyBankDetailService companyBankDetailService;
    @Resource
    private CompanyBankDetailValidator companyBankDetailValidator;
    @Resource
    private AccountTypeService accountTypeService;
    @Resource
    private CompanyService companyService;

    public void setUpModel(ModelMap model, CompanyBankDetail item){
        model.addAttribute("pageTitle", appPrefix+"Create/ Edit Company Bank Details");
        model.addAttribute("item", item);
        model.addAttribute("accountType", accountTypeService.getAll());
        model.addAttribute("company", item.getCompany());
        model.addAttribute("itemDelete", "../dashboard/profile.htm?type=1&id=" + item.getCompany().getCompanyId());
    }
    
    @RequestMapping(value = "/item.form", method = RequestMethod.GET)
    public String getItemForm(ModelMap model, @RequestParam(required = false) String id, @RequestParam String companyId) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        CompanyBankDetail p = new CompanyBankDetail(companyService.get(companyId));
        if (id != null) {
            p = companyBankDetailService.get(id);
        }else {
            p.setAccountName(p.getCompany().getName());
        }
        setUpModel(model, p);
        return "company/bankDetailForm";
    }

    @RequestMapping(value = "/item.form", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("item") @Valid CompanyBankDetail companyBankDetail, BindingResult result, ModelMap model) throws UnsupportedEncodingException {
        companyBankDetailValidator.validate(companyBankDetail, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());
            setUpModel(model, companyBankDetail);
            return "company/bankDetailForm";
        }
        companyBankDetailService.save(companyBankDetail);
        return "redirect:../dashboard/profile.htm?type=1&id=" + companyBankDetail.getCompany().getCompanyId();
    }
    
    @RequestMapping(value = "item.delete", method = RequestMethod.GET)
    public String getDeletForm(@RequestParam("id") String id, ModelMap model){
        CompanyBankDetail companyBankDetail = companyBankDetailService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, companyBankDetail.getCompany().getName()+" Bank detail item", "../dashboard/profile.htm?type=3&id=" + companyBankDetail.getCompany().getCompanyId());
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", appPrefix+"Delete "+companyBankDetail.getCompany().getName()+" Bank detail item");
        return "admin/deleteItem";
    }
    
    @RequestMapping(value = "item.delete", method = RequestMethod.POST)
    public String deleteItem(@Valid ItemDeleteDTO dto) throws UnsupportedEncodingException{
        CompanyBankDetail companyBankDetail = companyBankDetailService.get(dto.getId());
        Company emp = companyBankDetail.getCompany();
        companyBankDetailService.delete(companyBankDetail);
        return "redirect:../dashboard/profile.htm?type=2&id=" + emp.getCompanyId();
    }
}

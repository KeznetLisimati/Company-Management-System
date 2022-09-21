package org.company.portal.web.controller.company;

import javax.annotation.Resource;
import org.company.business.domain.Company;
import org.company.business.service.CompanyAddressService;
import org.company.business.service.CompanyBankDetailService;
import org.company.business.service.CompanyContactService;
import org.company.business.service.CompanyService;
import org.company.portal.util.AppMessage;
import org.company.portal.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Edward Zengeni
 */
@Controller
@RequestMapping("/company/dashboard")
public class CompanyDashboardController extends BaseController {

    @Resource
    private CompanyService companyService;
    @Resource
    private CompanyBankDetailService companyBankDetailService;
    @Resource
    private CompanyContactService companyContactService;
    @Resource
    private CompanyAddressService companyAddressService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String companyProfileTabs(ModelMap model, @RequestParam String id, @RequestParam(required = false) Integer type) {
        Company company = companyService.get(id);
        model.addAttribute("pageTitle", appPrefix + company.getName() + "'s Dashboard");
        model.addAttribute("company", company);
        if (type != null) {
            model.addAttribute("message", AppMessage.getMessage(type));
        }
        model.addAttribute("companyBankDetails", companyBankDetailService.getByCompany(company));
        model.addAttribute("companyContacts", companyContactService.getByCompany(company));
        model.addAttribute("companyAddress", companyAddressService.getByCompany(company));
        return "company/companyDashboard";
    }
}

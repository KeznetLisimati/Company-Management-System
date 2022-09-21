package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.AccountTypeValidator;
import org.company.business.domain.AccountType;
import org.company.business.service.AccountTypeService;
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
@RequestMapping("/admin/accountType")
public class AccountTypeController extends BaseController{

    @Resource
    private AccountTypeService accountTypeService;
    @Resource
    private AccountTypeValidator accountTypeValidator;

    @RequestMapping(value = "/accountType.form", method = RequestMethod.GET)
    public String accountTypeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit AccountTypes");
        AccountType p = new AccountType();
        if (id != null) {
            p = accountTypeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "accountType.list?type=3");
        return "admin/accountTypeForm";
    }

    @RequestMapping(value = "/accountType.form", method = RequestMethod.POST)
    public String saveAccountTypes(@ModelAttribute("item") @Valid AccountType accountType, BindingResult result, ModelMap model) {
        accountTypeValidator.validate(accountType, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit AccountTypes");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", accountType);
            return "admin/accountTypeForm";
        }
        accountTypeService.save(accountType);
        return "redirect: accountType.list?type=1";
    }

    @RequestMapping(value = {"/accountType.list", "/"}, method = RequestMethod.GET)
    public String accountTypeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::AccountTypes List");
        model.addAttribute("items", accountTypeService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/accountTypeList";
    }

    @RequestMapping(value = "accountType.delete", method = RequestMethod.GET)
    public String getAccountTypesDeleteForm(@RequestParam("id") String id, ModelMap model) {
        AccountType accountType = accountTypeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, accountType.getName(), "accountType.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + accountType.getName() + " AccountTypes");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "accountType.delete", method = RequestMethod.POST)
    public String deleteAccountTypes(@Valid ItemDeleteDTO dto) {
        accountTypeService.delete(accountTypeService.get(dto.getId()));
        return "redirect:accountType.list?type=2";
    }
}

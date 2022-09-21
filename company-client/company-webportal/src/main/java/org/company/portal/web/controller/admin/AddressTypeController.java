package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.AddressTypeValidator;
import org.company.business.domain.AddressType;
import org.company.business.service.AddressTypeService;
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
@RequestMapping("/admin/addressType")
public class AddressTypeController extends BaseController{

    @Resource
    private AddressTypeService addressTypeService;
    @Resource
    private AddressTypeValidator addressTypeValidator;

    @RequestMapping(value = "/addressType.form", method = RequestMethod.GET)
    public String addressTypeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit AddressTypes");
        AddressType p = new AddressType();
        if (id != null) {
            p = addressTypeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "addressType.list?type=3");
        return "admin/addressTypeForm";
    }

    @RequestMapping(value = "/addressType.form", method = RequestMethod.POST)
    public String saveAddressTypes(@ModelAttribute("item") @Valid AddressType addressType, BindingResult result, ModelMap model) {
        addressTypeValidator.validate(addressType, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit AddressTypes");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", addressType);
            return "admin/addressTypeForm";
        }
        addressTypeService.save(addressType);
        return "redirect: addressType.list?type=1";
    }

    @RequestMapping(value = {"/addressType.list", "/"}, method = RequestMethod.GET)
    public String addressTypeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::AddressTypes List");
        model.addAttribute("items", addressTypeService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/addressTypeList";
    }

    @RequestMapping(value = "addressType.delete", method = RequestMethod.GET)
    public String getAddressTypesDeleteForm(@RequestParam("id") String id, ModelMap model) {
        AddressType addressType = addressTypeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, addressType.getName(), "addressType.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + addressType.getName() + " AddressTypes");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "addressType.delete", method = RequestMethod.POST)
    public String deleteAddressTypes(@Valid ItemDeleteDTO dto) {
        addressTypeService.delete(addressTypeService.get(dto.getId()));
        return "redirect:addressType.list?type=2";
    }
}

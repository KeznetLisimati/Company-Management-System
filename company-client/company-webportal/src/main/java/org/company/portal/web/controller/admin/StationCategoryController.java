package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.StationCategoryValidator;
import org.company.business.domain.StationCategory;
import org.company.business.service.StationCategoryService;
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
@RequestMapping("/admin/stationCategory")
public class StationCategoryController extends BaseController{

    @Resource
    private StationCategoryService stationCategoryService;
    @Resource
    private StationCategoryValidator stationCategoryValidator;

    @RequestMapping(value = "/stationCategory.form", method = RequestMethod.GET)
    public String stationCategoryForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit StationCategorys");
        StationCategory p = new StationCategory();
        if (id != null) {
            p = stationCategoryService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "stationCategory.list?type=3");
        return "admin/stationCategoryForm";
    }

    @RequestMapping(value = "/stationCategory.form", method = RequestMethod.POST)
    public String saveStationCategorys(@ModelAttribute("item") @Valid StationCategory stationCategory, BindingResult result, ModelMap model) {
        stationCategoryValidator.validate(stationCategory, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit StationCategorys");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", stationCategory);
            return "admin/stationCategoryForm";
        }
        stationCategoryService.save(stationCategory);
        return "redirect: stationCategory.list?type=1";
    }

    @RequestMapping(value = {"/stationCategory.list", "/"}, method = RequestMethod.GET)
    public String stationCategoryList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::StationCategorys List");
        model.addAttribute("items", stationCategoryService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/stationCategoryList";
    }

    @RequestMapping(value = "stationCategory.delete", method = RequestMethod.GET)
    public String getStationCategorysDeleteForm(@RequestParam("id") String id, ModelMap model) {
        StationCategory stationCategory = stationCategoryService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, stationCategory.getName(), "stationCategory.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + stationCategory.getName() + " StationCategorys");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "stationCategory.delete", method = RequestMethod.POST)
    public String deleteStationCategorys(@Valid ItemDeleteDTO dto) {
        stationCategoryService.delete(stationCategoryService.get(dto.getId()));
        return "redirect:stationCategory.list?type=2";
    }
}

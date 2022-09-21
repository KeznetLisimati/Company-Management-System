/*
 * Copyright 2014 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.company.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.company.portal.util.AppMessage;
import org.company.portal.util.MessageType;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.ProvinceValidator;
import org.company.business.domain.Province;
import org.company.business.service.ProvinceService;
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
 * @author Edward Zengeni
 */
@Controller
@RequestMapping("/admin/province")
public class ProvinceController extends BaseController {

    @Resource
    private ProvinceService provinceService;
    @Resource
    private ProvinceValidator provinceValidator;

    @RequestMapping(value = "/province.form", method = RequestMethod.GET)
    public String provinceForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit Province");
        Province p = new Province();
        if (id != null) {
            p = provinceService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "province.list?type=3");
        return "admin/provinceForm";
    }

    @RequestMapping(value = "/province.form", method = RequestMethod.POST)
    public String saveProvince(@ModelAttribute("item") @Valid Province province, BindingResult result, ModelMap model) {
        provinceValidator.validate(province, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit Province");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", province);
            return "admin/provinceForm";
        }
        provinceService.save(province);
        return "redirect: province.list?type=1";
    }

    @RequestMapping(value = {"/province.list", "/"}, method = RequestMethod.GET)
    public String provinceList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Province List");
        model.addAttribute("items", provinceService.getAll());
        if(type != null){
            model.addAttribute("message", AppMessage.getMessage(type));
        }
        return "admin/provinceList";
    }
    
    @RequestMapping(value = "province.delete", method = RequestMethod.GET)
    public String getProvinceDeleteForm(@RequestParam("id") String id, ModelMap model){
        Province province = provinceService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, province.getName(), "province.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete "+province.getName()+" Province");
        return "admin/deleteItem";
    }
    
    @RequestMapping(value = "province.delete", method = RequestMethod.POST)
    public String deleteProvince(@Valid ItemDeleteDTO dto){
        provinceService.delete(provinceService.get(dto.getId()));
        return "redirect:province.list?type=2";
    }
}
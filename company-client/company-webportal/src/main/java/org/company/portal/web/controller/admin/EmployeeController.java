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
import org.company.portal.web.validator.EmployeeValidator;
import org.company.business.domain.Employee;
import org.company.business.service.EmployeeService;
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
@RequestMapping("/admin/employee")
public class EmployeeController extends BaseController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private EmployeeValidator employeeValidator;

    @RequestMapping(value = "/employee.form", method = RequestMethod.GET)
    public String employeeForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit Employee");
        Employee p = new Employee();
        if (id != null) {
            p = employeeService.get(id);
        }
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "employee.list?type=3");
        return "admin/employeeForm";
    }

    @RequestMapping(value = "/employee.form", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("item") @Valid Employee employee, BindingResult result, ModelMap model) {
        employeeValidator.validate(employee, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit Employee");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());

            model.addAttribute("item", employee);
            return "admin/employeeForm";
        }
        employeeService.save(employee);
        return "redirect: employee.list?type=1";
    }

    @RequestMapping(value = {"/employee.list", "/"}, method = RequestMethod.GET)
    public String employeeList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Employee List");
        model.addAttribute("items", employeeService.getAll());
        if(type != null){
            model.addAttribute("message", AppMessage.getMessage(type));
        }
        return "admin/employeeList";
    }
    
    @RequestMapping(value = "employee.delete", method = RequestMethod.GET)
    public String getEmployeeDeleteForm(@RequestParam("id") String id, ModelMap model){
        Employee employee = employeeService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, employee.getName(), "employee.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete "+employee.getName()+" Employee");
        return "admin/deleteItem";
    }
    
    @RequestMapping(value = "employee.delete", method = RequestMethod.POST)
    public String deleteEmployee(@Valid ItemDeleteDTO dto){
        employeeService.delete(employeeService.get(dto.getId()));
        return "redirect:employee.list?type=2";
    }
}
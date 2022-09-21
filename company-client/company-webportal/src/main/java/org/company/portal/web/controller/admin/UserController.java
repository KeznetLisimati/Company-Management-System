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
import org.company.portal.web.validator.UserValidator;
import org.company.business.domain.User;
import org.company.business.service.UserRoleService;
import org.company.business.service.UserService;
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
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private UserValidator userValidator;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping(value = "/user.form", method = RequestMethod.GET)
    public String userForm(ModelMap model, @RequestParam(required = false) String id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::Create/ Edit User");
        User p = new User();
        if (id != null) {
            p = userService.get(id);
        }
        model.addAttribute("roles", userRoleService.getAll());
        model.addAttribute("item", p);
        model.addAttribute("itemDelete", "user.list?type=3");
        return "admin/userForm";
    }

    @RequestMapping(value = "/user.form", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("item") @Valid User user, BindingResult result, ModelMap model) {
        userValidator.validate(user, result);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "HRIS::Create/ Edit User");
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());
            model.addAttribute("item", user);
            model.addAttribute("roles", userRoleService.getAll());
            return "admin/userForm";
        }
        userService.save(user);
        return "redirect: user.list?type=1";
    }

    @RequestMapping(value = {"/user.list", "/"}, method = RequestMethod.GET)
    public String userList(ModelMap model, @RequestParam(required = false) Integer type) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "HRIS::User List");
        model.addAttribute("items", userService.getAll());
        if (type != null) {
            model.addAttribute("message", getMessage(type));
        }
        return "admin/userList";
    }

    @RequestMapping(value = "user.delete", method = RequestMethod.GET)
    public String getUserDeleteForm(@RequestParam("id") String id, ModelMap model) {
        User user = userService.get(id);
        ItemDeleteDTO dto = new ItemDeleteDTO(id, user.getUsername(), "user.list?type=3");
        model.addAttribute("item", dto);
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Are you sure you want to delete this record").messageType(MessageType.WARNING).build());
        model.addAttribute("pageTitle", "HRIS::Delete " + user.getUsername() + " User");
        return "admin/deleteItem";
    }

    @RequestMapping(value = "user.delete", method = RequestMethod.POST)
    public String deleteUser(@Valid ItemDeleteDTO dto) {
        userService.delete(userService.get(dto.getId()));
        return "redirect:user.list?type=2";
    }
}

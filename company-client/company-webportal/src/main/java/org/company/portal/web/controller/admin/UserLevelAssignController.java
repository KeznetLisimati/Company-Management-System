package org.company.portal.web.controller.admin;

/*
 * Copyright 2015 Edward Zengeni.
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

import org.company.business.util.dto.UserLevelDTO;
import javax.annotation.Resource;
import org.company.portal.web.controller.BaseController;
import org.company.portal.web.validator.UserValidator;
import org.company.business.domain.User;
import org.company.business.service.DistrictService;
import org.company.business.service.ProvinceService;
import org.company.business.service.UserService;
import org.company.business.util.dto.UserLevel;
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
@RequestMapping("/admin")
public class UserLevelAssignController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private UserValidator userValidator;
    @Resource
    private ProvinceService provinceService;
    @Resource
    private DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET, value = "/assign-user-org")
    public String getUserLevelForm(@RequestParam String id, ModelMap model) {
        User user = userService.get(id);
        UserLevelDTO userLevel = new UserLevelDTO(user);
        model.addAttribute("formAction", "assign-user-org");
        model.addAttribute("pageTitle", "COMPANY REGISTRATION:" + user.getDisplayName() + " Assign User Level");
        model.addAttribute("levels", UserLevel.getUserLevel());
        model.addAttribute("item", userLevel);
        if (userLevel.getUser().getUserLevel() != null) {
            if (userLevel.getUser().getUserLevel().equals(UserLevel.PROVINCE)) {
                model.addAttribute("provinces", provinceService.getAll());
            }
        }
        return "admin/userLevelForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/assign-user-org")
    public String saveUserLevel(ModelMap model, @ModelAttribute("item") UserLevelDTO dto, BindingResult result) throws Exception {
        userValidator.validateUserLevel(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "COMPANY REGISTRATION:" + dto.getUser().getDisplayName() + " Assign User Level");
            model.addAttribute("item", dto);
            return "admin/userLevelForm";
        }
        userService.save(dto.getInstance(dto));
        return "redirect:../admin/user/?type=1";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/re-load-user-pref")
    public String reloadUserPref(ModelMap model, @ModelAttribute("item") UserLevelDTO dto) throws Exception {
        model.addAttribute("pageTitle", "COMPANY REGISTRATION:" + dto.getUser().getDisplayName() + " Assign User Level");
        model.addAttribute("formAction", "assign-user-org");
        if (dto.getUser().getUserLevel().equals(UserLevel.NATIONAL)) {
            return "admin/userLevelForm";
        } else if (dto.getUser().getUserLevel().equals(UserLevel.PROVINCE)) {
            model.addAttribute("provinces", provinceService.getAll());
            return "admin/userLevelForm";
        } else if (dto.getUser().getUserLevel().equals(UserLevel.DISTRICT)) {
            model.addAttribute("provinces", provinceService.getAll());
            if (dto.getUser().getProvince() != null) {
                model.addAttribute("districts", districtService.getDistrictByProvince(dto.getUser().getProvince()));
            }
            return "admin/userLevelForm";
        }
        return "admin/userLevelForm";
    }
}
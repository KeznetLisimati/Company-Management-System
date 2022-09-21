package org.company.portal.web.controller.admin;

import org.apache.log4j.Logger;
import org.company.portal.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    protected static Logger logger = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
        logger.info("Received request to show index page (Admin Index)");
        model.addAttribute("pageTitle", "HRIS: Administration Page Home");
        return "admin/index";
    }

}

package org.company.portal.web.controller.reports;

import org.apache.log4j.Logger;
import org.company.portal.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reports")
public class ReportsController extends BaseController{

    protected static Logger logger = Logger.getLogger(ReportsController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
        logger.info("Received request to show index page (Reports Index)");
        model.addAttribute("pageTitle", "HRIS::Reports Page Home");
        return "reports/index";
    }

}

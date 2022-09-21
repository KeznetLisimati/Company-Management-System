package org.company.portal.web.controller;

import org.company.portal.util.AppMessage;
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
@RequestMapping("/search")
public class SearchController extends BaseController {

 
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getPatientIndex(ModelMap model, @RequestParam(required = false) Integer type) {
        if (type != null) {
            model.addAttribute("message", AppMessage.getMessage(type));
        }
        return "searchByName";
    }


}

package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel) {
        theModel.addAttribute("inhousepart", part);
        if (part.validInv()) {
            if (theBindingResult.hasErrors()) {
                return "InhousePartForm";
            } else {
                PartService partService = context.getBean(PartServiceImpl.class);
                if(part.getId() != 0) { // if the part exists, it needs to be updated
                    Part existingPart = partService.findById((int) part.getId());

                    existingPart.setName(part.getName());
                    existingPart.setMaxInv(part.getMaxInv());
                    existingPart.setMinInv(part.getMinInv());
                    existingPart.setPrice(part.getPrice());
                    existingPart.setInv(part.getInv());
                    existingPart.setId(part.getId());

                    // Save the updated part
                    partService.save(existingPart);
                }
                else { // If the part does not exist - it is a new part
                    Part newPart = new Part();

                    newPart.setName(part.getName());
                    newPart.setMaxInv(part.getMaxInv());
                    newPart.setMinInv(part.getMinInv());
                    newPart.setPrice(part.getPrice());
                    newPart.setInv(part.getInv());
                    newPart.setId(part.getId());

                    // Save the new part
                    partService.save(newPart);
                }
                }
            }
        else {
            if(part.getInv() < part.getMinInv()) {
                return "underMinScreen";
            }
            else if(part.getInv() > part.getMaxInv()) {
                return "overMaxScreen";
            }
        }
            return "confirmationaddpart";
    }
}
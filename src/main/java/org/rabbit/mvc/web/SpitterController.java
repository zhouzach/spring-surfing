package org.rabbit.mvc.web;

import org.rabbit.mvc.Spitter;
import org.rabbit.mvc.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @RequestPart("profilePircture") Part profilePicture,
            @Valid Spitter spitter,
            RedirectAttributes model,
            Errors errors) {

        if (errors.hasErrors()) {
            return "registerForm";
        }

        try {
            profilePicture.write("/data/spittr/" + profilePicture.getSubmittedFileName());
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        spitterRepository.save(spitter);
        model.addAttribute("username", spitter.getUserName());
        model.addFlashAttribute(spitter);
        return "redirect: /spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("spitter")) {
            model.addAttribute(
                spitterRepository.findByUsername(username));
        }
        return "profile";
    }

}

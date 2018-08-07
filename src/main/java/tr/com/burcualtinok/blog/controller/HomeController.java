package tr.com.burcualtinok.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.burcualtinok.blog.model.Entry;
import tr.com.burcualtinok.blog.repository.EntryRepository;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/blog")
public class HomeController {
    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping(value ="", method = RequestMethod.GET )
    public String getIndex(Model model){
        Iterable <Entry> entries = entryRepository.findAll();
        model.addAttribute("entries",entries);
        return "entries/listEntries";
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String getEntryForm(Model model){
        model.addAttribute("entry", new Entry());
        return "entries/newEntry";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postEntryForm(@Valid @ModelAttribute Entry entry, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "entries/newEntry";
        }
        else{
            entryRepository.save(entry);
            // System.out.println("entry title:"+ entry.getTitle());
            // System.out.println("body title:"+ entry.getBody());
            return "redirect:/blog";
        }

    }

    @RequestMapping(value= "/{id}", method= RequestMethod.GET)
    public String showEntry(@PathVariable Integer id, Model model){
        Optional<Entry> entryOptional = entryRepository.findById(id);

        if(!entryOptional.isPresent()){ //entry yoksa
//            log.warn("Entry with {} id is not present");
            return "index";
        }
        else{
            model.addAttribute("entry", entryOptional.get());
            return "entries/showEntry";
        }

    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String getUpdateEntry(@PathVariable("id") Integer id, Model model){
        Optional<Entry> entryOptional = entryRepository.findById(id);

        if(!entryOptional.isPresent()){ //entry yoksa
//            log.warn("Entry with {} id is not present",id);
            return "index";
        }
        else{
            model.addAttribute("entry", entryOptional.get());
            return "entries/updateEntry";
        }
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String postUpdateEntry(@Valid @ModelAttribute Entry entry, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "entries/updateEntry";
        }
        else{
            entryRepository.save(entry);
            return "redirect:/blog";
        }

    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String deleteEntry(@PathVariable("id") Integer id) {
        Optional<Entry> entryOptional = entryRepository.findById(id);
        if (!entryOptional.isPresent()) { //entry yoksa
//            log.warn("Entry with {} id is not present", id);
            return "redirect:/blog";
        }
        else {
            entryRepository.delete(entryOptional.get());
            return "redirect:/blog";
        }
    }
}

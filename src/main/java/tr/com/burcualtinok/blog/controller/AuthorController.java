package tr.com.burcualtinok.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.burcualtinok.blog.model.Author;
import tr.com.burcualtinok.blog.repository.AuthorRepository;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import static tr.com.burcualtinok.blog.repository.AuthorRepository.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value ="", method = RequestMethod.GET )
    public String getIndex(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors/listAuthors";

    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String getRegisterAuthor(Model model){
        model.addAttribute("author", new Author());
        return "authors/newRegister";
    }
     @RequestMapping(value ="/new", method = RequestMethod.POST)
        public String posRegisterAuthor(@Valid @ModelAttribute Author authors,BindingResult bindingResult){
         if(bindingResult.hasErrors()){
             return "authors/newRegister";
         }
         else{
             authorRepository.save(authors);
             return "redirect:/authors";
         }
            }

    }



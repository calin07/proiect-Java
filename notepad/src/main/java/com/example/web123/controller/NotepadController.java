package com.example.web123.controller;

import com.example.web123.entity.Notepad;
import com.example.web123.repository.NotepadRepository;
import model.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/notepad")

public class NotepadController {

    final NotepadRepository notepadRepository;

    public NotepadController(NotepadRepository notepadRepository) {
        this.notepadRepository = notepadRepository;
    }

    @RequestMapping("/")
    public String list(Model model,Principal principal) {
        List<Notepad> list = this.notepadRepository.findByUserID(getUserDetailsFromAuthentication(principal).getId());
        List<Date> now = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
            now.add(list.get(i).getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<String> dateStr = new ArrayList<>();
        for (int i = 0; i < now.size(); i++)
            dateStr.add(dateFormat.format(now.get(i)));
        String dateStr1;
        if (dateStr.size() == 0)
            dateStr1 = "";
        else
            dateStr1 = dateStr.get(dateStr.size() - 1);
        model.addAttribute("time", dateStr1);
        model.addAttribute("notepad", list);
        return "notepad/list";
    }

    @GetMapping("/add")
    public String from() {
        return "notepad/add";
    }

    @PostMapping("/add")
    public String addNotepad(Model model, @RequestParam("text") String text, @RequestParam("content") String content,Principal principal) {
        Date now = new Date();
        UUID userid=getUserDetailsFromAuthentication(principal).getId();
        Notepad notepad=new Notepad(text,content,now,userid);
        notepadRepository.save(notepad);
        return "redirect:/notepad/";
    }

    @GetMapping("/delete")
    public String deleteNotepad(@RequestParam String param1) {
        System.out.println(param1);
        UUID id = UUID.fromString(param1);
        notepadRepository.deleteAllById(Collections.singleton(id));
        return "redirect:/notepad/";
    }

    @RequestMapping("/update/{id}")
    public String fromNotepad(Model model,@PathVariable("id") String ceva) {
        model.addAttribute("id",ceva);
        System.out.println("000:"+ceva);
        return "notepad/update";
    }

     @PostMapping("/update/{id}")
    public String updateNotepad(@PathVariable("id") String id, @RequestParam("content") String content){
         UUID id_update = UUID.fromString(id);
         notepadRepository.updateNotepad(content,id_update);
      return "redirect:/notepad/";
    }

    @GetMapping("/search")
        public String search(){
            return "notepad/search";
        }

    @PostMapping("/search")
    public String searchNotepad(Model model,@RequestParam("text") String text,Principal principal)
    {
        List<Notepad> list =  notepadRepository.searchNotepad(text,getUserDetailsFromAuthentication(principal).getId());
        List<Date> now = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
            now.add(list.get(i).getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<String> dateStr = new ArrayList<>();
        for (int i = 0; i < now.size(); i++)
            dateStr.add(dateFormat.format(now.get(i)));
        String dateStr1;
        if (dateStr.size() == 0)
            dateStr1 = "";
        else
            dateStr1 = dateStr.get(dateStr.size() - 1);
        model.addAttribute("time",dateStr1);
        model.addAttribute("notepad",list);
        return "/notepad/search";
    }

    private CustomUserDetails getUserDetailsFromAuthentication(Principal principal) {
        return ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
    }
}


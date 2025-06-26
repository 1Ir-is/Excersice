package com.example.song_validator.controllers;

import com.example.song_validator.dtos.SongDTO;
import com.example.song_validator.models.Song;
import com.example.song_validator.services.ISongService;
import com.example.song_validator.validators.SongValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class SongController {

    @Autowired
    private ISongService songService;

    private final SongValidator songValidator = new SongValidator();

    @GetMapping()
    public String listSongs(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("message", message);
        return "list";
    }

    @GetMapping("new")
    public String showAddForm(Model model) {
        model.addAttribute("songDTO", new SongDTO());
        return "create";
    }

    @PostMapping("save")
    public String saveSong(@ModelAttribute("songDTO") SongDTO songDTO, BindingResult bindingResult, Model model) {
        songValidator.validate(songDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create";
        }

        songService.saveSong(songDTO);
        return "redirect:/?message=Song created successfully!";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SongDTO songDTO = songService.findById(id);
        model.addAttribute("songDTO", songDTO);
        model.addAttribute("songId", id);
        return "edit";
    }

    @PostMapping("update/{id}")
    public String updateSong(@PathVariable Long id, @ModelAttribute("songDTO") SongDTO songDTO, BindingResult bindingResult, Model model) {
        songValidator.validate(songDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("songId", id);
            return "edit";
        }

        songService.updateSong(id, songDTO);
        return "redirect:/?message=Song updated successfully!";
    }

    @GetMapping("delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteById(id);
        return "redirect:/?message=Song deleted successfully!";
    }
}
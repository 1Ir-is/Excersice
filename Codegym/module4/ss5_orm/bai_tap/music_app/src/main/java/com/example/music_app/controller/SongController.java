package com.example.music_app.controller;

import com.example.music_app.model.Song;
import com.example.music_app.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("song", new Song());
        return "/create";
    }

    @PostMapping("/create")
    public String createSong(@ModelAttribute Song song,
                             @RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/";

        // Tạo thư mục nếu chưa tồn tại
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Lưu file
        String fileName = file.getOriginalFilename();
        File uploadFile = new File(uploadDir + fileName);
        file.transferTo(uploadFile);

        // Gán đường dẫn file vào song
        song.setFilePath(uploadDir + fileName);
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String editSong(@ModelAttribute Song song) {
        // Không sửa file khi edit, giữ nguyên filePath
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs";
    }

    @GetMapping("/play/{id}")
    public String playSong(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "/play";
    }
}

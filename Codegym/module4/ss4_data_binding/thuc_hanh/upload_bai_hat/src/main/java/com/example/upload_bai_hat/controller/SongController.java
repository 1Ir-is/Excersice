package com.example.upload_bai_hat.controller;

import com.example.upload_bai_hat.model.Song;
import com.example.upload_bai_hat.model.SongForm;
import com.example.upload_bai_hat.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ISongService songService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String index(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveSong(@ModelAttribute SongForm songForm) {
        MultipartFile multipartFile = songForm.getFile();
        String fileName = multipartFile.getOriginalFilename();

        if (fileName != null && !fileName.matches(".*\\.(mp3|wav|ogg|m4p)$")) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("songForm", songForm);
            modelAndView.addObject("error", "Chỉ chấp nhận file .mp3, .wav, .ogg, .m4p!");
            return modelAndView;
        }

        try {
            File dir = new File(fileUpload);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Song song = new Song(songForm.getId(), songForm.getName(), songForm.getGenre(), songForm.getArtist(), fileName);
        songService.save(song);

        return new ModelAndView("redirect:/song");
    }

}

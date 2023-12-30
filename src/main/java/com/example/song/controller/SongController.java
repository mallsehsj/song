/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.song.controller;

import com.example.song.model.*;
import com.example.song.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

public class SongController {
    @Autowired
    private SongH2Service songService;

    @GetMapping("/songs")
    public ArrayList<Song> getAllSong() {
        return songService.getAllSong();
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);

    }

    @GetMapping("/songs/{songId}")
    public Song getById(@PathVariable("songId") int songId) {
        return songService.getById(songId);

    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songService.updateSong(songId, song);

    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable("songId") int songId) {
        songService.deleteSong(songId);

    }

}

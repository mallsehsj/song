// Write your code here
package com.example.song.repository;

import java.util.*;
import com.example.song.model.Song;

public interface SongRepository {
    ArrayList<Song> getAllSong();

    Song addSong(Song song);

    Song getById(int songId);

    Song updateSong(int songId, Song song);

    void deleteSong(int songId);

}
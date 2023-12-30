/*
 * 
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.song.service;

import com.example.song.model.*;

import com.example.song.repository.*;
import com.example.song.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class SongH2Service implements SongRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getAllSong() {
        List<Song> list = db.query("select * from PLAYLIST", new SongRowMapper());
        ArrayList<Song> allSongs = new ArrayList<>(list);
        return allSongs;
    }

    @Override
    public Song addSong(Song song) {
        db.update("insert into PLAYLIST(songName, lyricist, singer, musicDirector) values(?,?,?,?)", song.getSongName(),
                song.getLyricist(), song.getSinger(), song.getMusicDirector());
        Song song2 = db.queryForObject("select * from PLAYLIST where songId=?", new SongRowMapper(), song.getSongId());
        return song2;
    }

    @Override
    public Song getById(int songId) {
        Song song2 = db.queryForObject("select * from PLAYLIST where songId=?", new SongRowMapper(), songId);
        if (song2 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return song2;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        Song song3 = getById(songId);
        if (song3 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (song.getSongName() != null) {
            db.update("update PLAYLIST set songName=? where songId=?", song.getSongName(), songId);

        }
        if (song.getLyricist() != null) {
            db.update("update PLAYLIST set lyricist=? where songId=?", song.getLyricist(), songId);
        }
        if (song.getSinger() != null) {
            db.update("update PLAYLIST set singer=? where songId=?", song.getSinger(), songId);
        }
        if (song.getMusicDirector() != null) {
            db.update("update PLAYLIST set musicDirector=? where songId=?", song.getMusicDirector(), songId);
        }
        return song3;

    }

    @Override

    public void deleteSong(int songId) {
        Song song4 = getById(songId);
        if (song4 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        } else {
            db.update("delete from PLAYLIST where songId=?", songId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

    }

}
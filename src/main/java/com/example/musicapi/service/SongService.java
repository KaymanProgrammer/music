package com.example.musicapi.service;

import com.example.musicapi.model.Result;
import com.example.musicapi.model.Song;
import org.json.JSONException;

import java.util.List;

public interface SongService {

    Song findSong(String title);
//    Song getSongById(String trackId);
}

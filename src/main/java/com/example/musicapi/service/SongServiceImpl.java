package com.example.musicapi.service;

import com.example.musicapi.model.Result;
import com.example.musicapi.model.Song;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.writer.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Service
public class SongServiceImpl implements SongService {

    @Override
    public Song findSong(String title) {
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

// Note: here we are making this converter to process any kind of response,
// not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        Song song = restTemplate.getForObject("https://itunes.apple.com/search?term=" + title + "&country=US&limit=50", Song.class);


        return song;
    }



//    @Override
//    public Song getSongById(String trackId) {
//        RestTemplate restTemplate = new RestTemplate();
//        return  restTemplate.getForObject("https://itunes.apple.com/search?term=" + trackId + "&entity=song", Song.class);
//
//    }
}

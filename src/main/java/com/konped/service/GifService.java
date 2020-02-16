package com.konped.service;

import com.konped.model.Gif;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GifService {
    List<Gif> findAllGifs();
    Gif findById(Long id);
    void save(MultipartFile file, Gif gif) throws IOException;
    void delete(Gif gif);
}

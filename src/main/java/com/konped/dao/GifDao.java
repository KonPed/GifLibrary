package com.konped.dao;

import com.konped.model.Gif;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GifDao {
    List<Gif> findAllGifs();
    Gif findById(Long id);
    void save(Gif gif);
    void delete(Gif gif);
}

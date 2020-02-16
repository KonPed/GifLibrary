package com.konped.service;

import com.konped.dao.GifDao;
import com.konped.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GifServiceImpl implements GifService {

    @Autowired
    private GifDao gifDao;

    @Override
    public List<Gif> findAllGifs() {
        return gifDao.findAllGifs();
    }

    @Override
    public Gif findById(Long id) {
        return gifDao.findById(id);
    }

    @Override
    public void save(MultipartFile file, Gif gif) {
        try {
            byte[] aByte = file.getBytes();
            gifDao.save(gif);
        } catch (IOException ioe) {
            System.err.println("Unable to get byte array from uploaded file.");
        }
    }

    @Override
    public void delete(Gif gif) {
        gifDao.delete(gif);
    }
}

package com.live.core.service;

import com.live.core.entities.Live;
import com.live.core.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl implements LiveService{
    @Autowired
    LiveRepository liveRepository;
    @Override
    public Live save(Live live) {
        return liveRepository.save(live);
    }

    @Override
    public void delete(Live live) {
        if (liveRepository.getOne(live.getId()) != null) {
            liveRepository.delete(live);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Live findOne(Long id) {
        return liveRepository.getOne(id);
    }

    @Override
    public List<Live> findAll() {
        return liveRepository.findAll();
    }
}

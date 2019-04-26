package com.live.core.service;

import com.live.core.entities.Live;

import java.util.List;

public interface LiveService {
    Live save(Live live);
    void delete(Live live);
    Live findOne(Long id);
    List<Live> findAll();
}

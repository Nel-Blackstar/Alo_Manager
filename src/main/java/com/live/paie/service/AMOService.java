package com.live.paie.service;

import com.live.paie.entities.AMO;

import java.util.List;

public interface AMOService {
    AMO save(AMO amo);
    void delete(AMO amo);
    AMO findOne(Long id);
    List<AMO> findAll();
}

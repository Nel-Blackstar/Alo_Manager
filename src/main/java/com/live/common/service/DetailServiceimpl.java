package com.live.common.service;

import com.live.common.entities.Detail;
import com.live.common.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Repository
public class DetailServiceimpl implements DetailService {
    @Autowired
    DetailRepository detailRepository;
    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public void delete(Detail detail) {
        if (detailRepository.getOne(detail.getId()) != null){
            detailRepository.delete(detail);
        } else {
            new RuntimeException("entity detail doesn't exist");
        }

    }

    @Override
    public Detail findOne(String id) {
        return detailRepository.getOne(Long.parseLong(id));
    }

    @Override
    public Detail findOne(Long id) {
        return detailRepository.getOne(id);
    }

    @Override
    public Detail findByNom(String nom) {
        return detailRepository.findDetailByNom(nom);
    }

    @Override
    public Detail findByLabel(String label) {
        return detailRepository.findDetailByLabel(label);
    }

    @Override
    public List<Detail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public List<Detail> findDetailByNom_entite(String entity_name) {
        return detailRepository.findDetailByNom_entite(entity_name);
    }
}

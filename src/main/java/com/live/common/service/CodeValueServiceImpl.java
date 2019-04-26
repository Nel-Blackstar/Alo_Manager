package com.live.common.service;

import com.live.common.entities.Code;
import com.live.common.entities.CodeValue;
import com.live.common.repository.CodeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeValueServiceImpl implements CodeValueService {
    @Autowired
    private CodeValueRepository codeValueRepository;

    @Override
    public CodeValue save(CodeValue codeValue) {
        return codeValueRepository.save(codeValue);
    }

    @Override
    public void delete(CodeValue codeValue) {
        codeValueRepository.delete(codeValue);
    }

    @Override
    public CodeValue findOne(String id) {
        return this.codeValueRepository.getOne(Long.valueOf(id));
    }

    @Override
    public CodeValue findById(Long id) {
        return this.codeValueRepository.getOne(id);
    }

    @Override
    public List<CodeValue> findAll() {
        return codeValueRepository.findAll();
    }

    @Override
    public CodeValue findByCodeValue(String codeValue) {
        return codeValueRepository.findByValeur(codeValue);
    }

    @Override
    public List<CodeValue> findCodeValueByCode(Code code) {
        return codeValueRepository.findCodeValueByCode(code);
    }

    @Override
    public List<CodeValue> findByIdentifier(String identifier) {
        return codeValueRepository.findCodeValueByCode_Identifier(identifier);
    }
}

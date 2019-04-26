package com.live.common.service;

import com.live.common.entities.Code;
import com.live.common.entities.CodeValue;

import java.util.List;

public interface CodeValueService {

    CodeValue save(CodeValue codeValue);

    void delete(CodeValue codeValue);

    CodeValue findOne(String id);
    CodeValue findById(Long id);

    List<CodeValue> findAll();

    CodeValue findByCodeValue(String codeValue);

    List<CodeValue> findCodeValueByCode(Code code);

    List<CodeValue> findByIdentifier(String identifier);
}

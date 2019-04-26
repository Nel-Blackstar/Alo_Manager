package com.live.common.repository;

import com.live.common.entities.Code;
import com.live.common.entities.CodeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeValueRepository extends JpaRepository<CodeValue, Long> {
    CodeValue findByValeur(String codeValue);
    List<CodeValue> findCodeValueByCode(Code code);
    List<CodeValue> findCodeValueByCode_Identifier(String identifier);
}

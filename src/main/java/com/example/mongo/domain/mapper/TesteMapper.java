package com.example.mongo.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.mongo.domain.Teste;
import com.example.mongo.domain.dto.TesteDTO;

@Mapper(componentModel = "cdi",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TesteMapper {

	Teste toEntity(TesteDTO testeDTO);
	
	TesteDTO toDTO(Teste teste);

}

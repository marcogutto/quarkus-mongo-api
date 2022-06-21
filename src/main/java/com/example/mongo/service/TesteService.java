package com.example.mongo.service;

import javax.ws.rs.core.Response;

import com.example.mongo.domain.dto.TesteDTO;

import io.smallrye.mutiny.Uni;

public interface TesteService {
	
	void save(TesteDTO testeDTO);
	
}

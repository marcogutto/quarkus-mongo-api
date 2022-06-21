package com.example.mongo.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mongo.domain.dto.TesteDTO;
import com.example.mongo.domain.mapper.TesteMapper;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class TesteServiceImpl implements TesteService {

	@Inject
	private TesteMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(TesteServiceImpl.class);

	@Override
	// @Transactional
	public void save(TesteDTO testeDTO) {

		mapper.toEntity(testeDTO)
			.persist().subscribe().with(
				created -> logger.info("Teste Criado: "+created),
				failed -> logger.info("Salvar Teste Falhou: "+failed));
		
	}
}
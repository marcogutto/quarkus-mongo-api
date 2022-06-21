package com.example.mongo.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mongo.domain.Teste;
import com.example.mongo.domain.dto.TesteDTO;
import com.example.mongo.domain.mapper.TesteMapper;
import com.example.mongo.service.TesteService;

import io.smallrye.mutiny.Uni;

@Path("/testes")
public class TesteResource {

    @Inject
    private TesteService service;

    @Inject
	private TesteMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(TesteResource.class);

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{id}")
    public Uni<Response> findById(@PathParam("id") String id) {
		try {

			logger.info(id);

			return Teste.findTesteById(id).map(teste -> {

				if (teste != null) {
					return Response.status(Response.Status.OK)
						.entity(mapper.toDTO(teste)).build();
				}
	
				return Response.status(Response.Status.NO_CONTENT).build();

			});

		} catch (Exception e) {
			logger.error(e.getMessage());
			return Uni.createFrom().item(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
		}
	}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(TesteDTO testeDTO) {

        mapper.toEntity(testeDTO).persist().subscribe().with(
            created -> logger.info("Teste Criado: "+created),
            failed -> logger.info("Criar Teste Falhou: "+failed));

        return Uni.createFrom().item(Response.status(Response.Status.CREATED)
			.entity(testeDTO).build());
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") String id, TesteDTO testeDTO) {

        mapper.toEntity(testeDTO).update().subscribe().with(
            created -> logger.info("Teste Atualizado: "+created),
            failed -> logger.info("Atualizar Teste Falhou: "+failed));

    }
}
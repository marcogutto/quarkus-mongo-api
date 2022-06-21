package com.example.mongo.domain;

import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.mutiny.Uni;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)

@MongoEntity(collection="teste")
public class Teste extends ReactivePanacheMongoEntityBase {

	@BsonId
	public String id;
	
	public String nome;

	public static Uni<Teste> findTesteById(String id){
        return Teste.findById(id);
    }
}
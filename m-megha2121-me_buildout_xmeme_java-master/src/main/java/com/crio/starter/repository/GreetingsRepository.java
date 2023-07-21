package com.crio.starter.repository;

import java.util.List;

import com.crio.starter.data.GreetingsEntity;

import org.bson.codecs.IntegerCodec;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GreetingsRepository extends MongoRepository<GreetingsEntity, IntegerCodec> {
  
  public GreetingsEntity findById(Integer id);
  @Query("{ 'id' : ?0}")
List<GreetingsEntity> findGreetingsEntity(String id);
  //@Query(value = "$limit:2", sort = "{ _id : -1 }")
 // public List<MemesEntity> findFirst20OrderByIdDEntities();
}

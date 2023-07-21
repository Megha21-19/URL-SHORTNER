package com.crio.starter.controller;

import com.crio.starter.exchange.MemeResponse;
import com.crio.starter.exchange.MemesList;
import com.crio.starter.exchange.ResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.service.GreetingsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@RestController
public class GreetingsController {

  Logger logger = LoggerFactory.getLogger(GreetingsController.class);
  @Autowired
  private GreetingsService greetingsService;
 


  @PostMapping("/memes")
     public ResponseEntity<?> saveMemesPages(@RequestBody GreetingsEntity entity ) {
      logger.info("Started saveMemesPages");
      ResponseEntity<?> responseEntitiy=null;
      ResponseDto responseDto=null;
      responseEntitiy = checkDuplicate(entity,responseDto,responseEntitiy);
      if(ObjectUtils.isEmpty(responseEntitiy) && !StringUtils.isEmpty(entity.getCaption()) && 
      !StringUtils.isEmpty(entity.getName()) && !StringUtils.isEmpty(entity.getUrl())  && !ObjectUtils.isEmpty(entity)) {
        MemeResponse response = greetingsService.saveMemesPages(entity);
        logger.info("Ended saveMemesPages");
        responseEntitiy= new ResponseEntity<>(response, HttpStatus.OK);
      }
      return responseEntitiy;
    }
  
 private ResponseEntity<?>  checkDuplicate(GreetingsEntity entity,  ResponseDto responseDto, ResponseEntity<?> responseEntitiy){
  if(!ObjectUtils.isEmpty(entity) ){
    responseDto=greetingsService.findByMemeId(entity.getId());
   if(!ObjectUtils.isEmpty(responseDto.getGreetingsEntity()) && responseDto.getGreetingsEntity().getId() ==entity.getId()){
     responseEntitiy=new ResponseEntity<>(HttpStatus.CONFLICT);
   }
  }
  return  responseEntitiy;
 } 
  
   @GetMapping("/memesById/{id}")
   public ResponseEntity<?> findByMemeId(@PathVariable Integer id) {
    logger.info("Started findByMemeId"+id);
    ResponseDto responseDto= greetingsService.findByMemeId(id);
    if(!StringUtils.isEmpty(responseDto.getGreetingsEntity())){
      logger.info("Memes bydID:{}",responseDto); 
      logger.info("Ended findByMemeId");
      return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    else{
      logger.info("Ended findByMemeId");
      return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
      }
   
    @GetMapping("/getAllmemes")
    public ResponseEntity<?> getMemesDetailsList() {
      logger.info("Started getMemesDetailsList");
      MemesList responseDto= greetingsService.getMemesDetailsList();
      if(!CollectionUtils.isEmpty(responseDto.getMemesEntityList())){
        logger.info("Ended getMemesDetailsList");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
      }
      else{
        return new ResponseEntity<>(responseDto,HttpStatus.NO_CONTENT);
      }
      
   }


    

}

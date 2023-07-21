package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.MemeResponse;
import com.crio.starter.exchange.MemesList;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
@Slf4j
public class GreetingsService {

    Logger logger = LoggerFactory.getLogger(GreetingsService.class);

    @Autowired
    private GreetingsRepository greetingsRepository;
   
    @Autowired
    private NextSequenceService nextSequenceService;
    
    public MemeResponse saveMemesPages(GreetingsEntity entity) {
        logger.info("Started findByMemeId"+entity);
        entity.setId(nextSequenceService.getNextSequence("database_sequences"));
        MemeResponse responseDto = new MemeResponse();
        greetingsRepository.save(entity);
        logger.info("Ended findByMemeId");
        responseDto.setId(entity.getId());
        return responseDto;
    }

    public ResponseDto findByMemeId(Integer memeId) {
        logger.info("Started findByMemeId");
        ResponseDto responseDto = new ResponseDto();
        GreetingsEntity memesEntity = greetingsRepository.findById(memeId);
        //Optional<MemesEntity> memesEntitys = greetingsRepository.findById(memeId);
        System.out.println("Get getMemesDetailsList" + memesEntity);
        responseDto.setGreetingsEntity(memesEntity);
        return responseDto;
    }

    public MemesList getMemesDetailsList() {
        logger.info("Started getMemesDetailsList");
        MemesList memesList=new MemesList();
        List<GreetingsEntity> memesEntityList = null;
        memesEntityList = greetingsRepository.findAll();
        memesEntityList = memesEntityList.stream().limit(100)
                                            .collect(Collectors.toList());
                                            Collections.sort(memesEntityList, 
    Comparator.comparingInt(GreetingsEntity::getId).reversed());
        log.debug("Get getMemesDetailsList {}", memesEntityList);
        memesList.setMemesEntityList(memesEntityList);
        logger.info("Ended getMemesDetailsList");
        return memesList;

    }
   
}

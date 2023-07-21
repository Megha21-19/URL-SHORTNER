package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.MemesList;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;



@AutoConfigureMockMvc
@SpringBootTest
class GreetingsControllerTest {

  @Autowired
 private MockMvc mvc;

  @MockBean

  private GreetingsService greetingsService;
  
  private ResponseDto createTestDataForResponse(){
    ResponseDto responseDto=new ResponseDto();
    GreetingsEntity memesEntity=new GreetingsEntity();
    memesEntity.setId(1);
    memesEntity.setCaption("caption");
    memesEntity.setName("name");
    memesEntity.setUrl("url");
    responseDto.setGreetingsEntity(memesEntity);
    return responseDto;
  }
  private MemesList setMemesList(){
    List<GreetingsEntity> list=new ArrayList<>();
    GreetingsEntity memesEntity=new GreetingsEntity();
     memesEntity.setId(1);
     memesEntity.setCaption("caption");
     memesEntity.setName("name");
     memesEntity.setUrl("url");
    list.add(memesEntity);
    MemesList memesList=new MemesList();
    memesList.setMemesEntityList(list);
    return memesList;
  }

  //@Test
  void getMemeByIdTest() throws Exception {
    //given
    Mockito.doReturn(createTestDataForResponse())
        .when(greetingsService).findByMemeId(1);

    //When
   URI uri = UriComponentsBuilder
       .fromPath("/memesByid")
       .queryParam("id", 001)
       .build().toUri();

   MockHttpServletResponse response = mvc.perform(
       get(uri.toString()).accept(APPLICATION_JSON_VALUE)
   ).andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
   ResponseDto responseDto = mapper.readValue(responseStr, ResponseDto.class);
   //ResponseDto ref = new ResponseDto(1,"name","caption","url");
   ResponseDto ref=new ResponseDto();
   GreetingsEntity memesEntity=new GreetingsEntity();
   memesEntity.setId(1);
   memesEntity.setCaption("caption");
   memesEntity.setName("name");
   memesEntity.setUrl("url");
   ref.setGreetingsEntity(memesEntity);
    assertEquals(responseDto, ref);
    Mockito.verify(greetingsService, Mockito.times(1)).findByMemeId(1);
  }

  @Test
  void getAllMemeTest() throws Exception {
    //given
    Mockito.doReturn(setMemesList())
        .when(greetingsService).getMemesDetailsList();

    //When
   URI uri = UriComponentsBuilder
       .fromPath("/getAllmemes")
       .build().toUri();

   MockHttpServletResponse response = mvc.perform(
       get(uri.toString()).accept(APPLICATION_JSON_VALUE)
   ).andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
   MemesList responseDto = mapper.readValue(responseStr, MemesList.class);
   //ResponseDto ref = new ResponseDto(1,"name","caption","url");
   ResponseDto ref=new ResponseDto();
   GreetingsEntity memesEntity=new GreetingsEntity();
   memesEntity.setId(1);
   memesEntity.setCaption("caption");
   memesEntity.setName("name");
   memesEntity.setUrl("url");
   ref.setGreetingsEntity(memesEntity);
    assertEquals(responseDto, setMemesList());
    Mockito.verify(greetingsService, Mockito.times(1)).getMemesDetailsList();
  }
  

  
  
}

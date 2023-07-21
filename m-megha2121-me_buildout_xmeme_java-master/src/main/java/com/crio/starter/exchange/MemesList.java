package com.crio.starter.exchange;

import lombok.Data;

import java.util.List;

import com.crio.starter.data.GreetingsEntity;


@Data
public class MemesList {
    private List<GreetingsEntity> memesEntityList;  
}
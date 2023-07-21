package com.crio.starter.exchange;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.crio.starter.data.GreetingsEntity;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ResponseDto {
 

  private GreetingsEntity greetingsEntity;
  
 
}

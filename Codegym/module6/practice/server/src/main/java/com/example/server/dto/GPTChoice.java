package com.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTChoice {
    private Integer index;
    private GPTMessage message;
    private String finish_reason;
}

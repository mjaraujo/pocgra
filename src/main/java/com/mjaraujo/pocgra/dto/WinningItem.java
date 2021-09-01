package com.mjaraujo.pocgra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinningItem {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}

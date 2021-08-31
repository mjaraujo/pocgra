package com.mjaraujo.pocgra.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class WinInterval implements Serializable {
    private List<WinningItem> min = new ArrayList<>();
    private List<WinningItem> max = new ArrayList<>();
}

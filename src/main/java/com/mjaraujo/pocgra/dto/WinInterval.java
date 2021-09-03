package com.mjaraujo.pocgra.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class WinInterval implements Serializable {
    private WinningItem min;
    private WinningItem max;
}

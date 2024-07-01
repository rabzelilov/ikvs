package ru.abzelilov.ikvs.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Classes {
    BACTERIA("Bacteria"),
    ARCHAEA("Archaea");

    private String value;
}

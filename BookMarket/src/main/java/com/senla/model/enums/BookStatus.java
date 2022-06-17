package com.senla.model.enums;

import annitations.Prototype;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@Prototype
public enum BookStatus {
    INSTOCK,
    MISSING
}

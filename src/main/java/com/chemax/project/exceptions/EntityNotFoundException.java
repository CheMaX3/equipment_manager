package com.chemax.project.exceptions;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException () {
        System.out.println("Объект с указанным ID не найден");
    }
}

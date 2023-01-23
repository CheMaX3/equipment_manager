package com.chemax.project.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        System.out.println("Объект с указанным ID не найден");
    }
}

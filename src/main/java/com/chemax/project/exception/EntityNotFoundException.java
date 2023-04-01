package com.chemax.project.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        //TODO:Подумать
        System.out.println("Объект с указанным ID не найден");
    }
}

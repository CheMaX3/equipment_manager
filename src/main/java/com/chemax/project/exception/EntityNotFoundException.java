package com.chemax.project.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        //TODO:Подумать
        System.out.println("Can't find object with such Id");
    }
}

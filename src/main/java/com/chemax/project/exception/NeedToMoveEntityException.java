package com.chemax.project.exception;

public class NeedToMoveEntityException extends RuntimeException {
    public NeedToMoveEntityException() {
        System.out.println("Требуется перемещение вложенных объектов");
    }
}

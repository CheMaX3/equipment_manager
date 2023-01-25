package com.chemax.project.exceptions;

public class NeedToMoveEntityException extends RuntimeException {
    public NeedToMoveEntityException() {
        System.out.println("Требуется перемещение вложенных объектов");
    }
}

package com.chemax.project.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        System.out.println("Пользователь " + username + " уже существует");
    }
}

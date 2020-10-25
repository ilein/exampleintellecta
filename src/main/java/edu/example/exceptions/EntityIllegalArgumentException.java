package edu.example.exceptions;


/**
 * Исключение при вызове метода с неверными параметрами
 */
public class EntityIllegalArgumentException extends BaseException {
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
}

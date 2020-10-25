package edu.example.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение при ненайденном объекте
 */
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String type, Object id) {
        this(formatMessage(type, id));
    }

    public static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификато не может быть пустым");
        return String.format("%s с ключом %s не найден", type, id);
    }
}

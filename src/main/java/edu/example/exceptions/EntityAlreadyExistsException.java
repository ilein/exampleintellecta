package edu.example.exceptions;


import org.springframework.util.Assert;

/**
 * Исключение при повторном создании сущности
 */
public class EntityAlreadyExistsException extends BaseException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    public static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификато не может быть пустым");
        return String.format("%s с ключом %s уже существует", type, id);
    }
}

package edu.example.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение при удалении объекта с ссылающимися на него сущностями
 */
public class EntityHasDetailException extends BaseException {

    public EntityHasDetailException(String message) {
        super(message);
    }

    public EntityHasDetailException(String type, Object id) {
        this(formatMessage(type, id));
    }

    public static String formatMessage(String type, Object id) {
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификато не может быть пустым");
        return String.format("%s ссылается на удаляемый объект с идентификатором %s", type, id);
    }

}

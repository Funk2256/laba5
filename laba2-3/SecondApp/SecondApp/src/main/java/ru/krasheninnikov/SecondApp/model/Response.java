package ru.krasheninnikov.SecondApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /***
     * Уникальный идентификатор сообщение
     */
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /***
     * Время создания сообщения
     */
    private String systemTime;
    /***
     * Бонус ЗП
     */
    private Double annualBonus;
    /***
     * Имя системы отправителя
     */
    private Codes code;
    /***
     * Наименование ресурса
     */
    private ErrorCodes errorCode;
    /***
     * Сообщение об ошибке
     */
    private ErrorMessages errorMessage;

}

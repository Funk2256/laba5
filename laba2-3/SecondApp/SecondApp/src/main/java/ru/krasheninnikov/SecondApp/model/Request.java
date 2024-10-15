package ru.krasheninnikov.SecondApp.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {


    @NotBlank(message = "UID не может быть пустым")
    @Size(max= 32)
    /***
     * Уникальный идентификатор сообщение
     */
    private String uid;

    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max= 32)
    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /***
     * Имя системы отправителя
     */
    private SystemNameRequest systemName;

    @NotBlank(message = "systemTime не может быть пустым")
    /***
     * Время создания сообщения
     */
    private String systemTime;
    /***
     * Наименование ресурса
     */
    private String source;
    /***
     * Позиция в компании
     */
    private String position;
    /***
     * Зарплата
     */
    private double salary;
    /***
     * Бонус
     */
    private double bonus;
    /***
     * Кол-во рабочих дней
     */
    private int workDays;

    @Min(1)
    @Max(100000)
    /***
     * Уникальный идентификатор коммуникации
     */
    private int communicationId;
    /***
     * Уникальный идентификатор шаблона
     */
    private int templateId;
    /***
     * Код продукта
     */
    private int productCode;
    /***
     * Смс код
     */
    private int smsCode;

    public String getTime(){
        return systemTime;
    }

    public String toString(){
        return "{" +
                "uid='" +uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", source='" + position + '\'' +
                ", source='" + salary + '\'' +
                ", source='" + bonus + '\'' +
                ", source='" + workDays + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';

    }


}
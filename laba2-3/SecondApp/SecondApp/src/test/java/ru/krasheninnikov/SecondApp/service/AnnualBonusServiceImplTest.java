package ru.krasheninnikov.SecondApp.service;

import org.junit.jupiter.api.Test;
import ru.krasheninnikov.SecondApp.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnnualBonusServiceImplTest {

    private AnnualBonusService calculator;

    @BeforeEach
    public void setUp() {
        calculator = new AnnualBonusService() {
            @Override
            public double calculate(Positions positions, double salary, double bonus, int worksDay) {
                return 0;
            }
        }; // Инициализация перед каждым тестом
    }

    @Test
    public void testCalculateForManagerWithBonus() {
        // Данные для Team Lead, который является менеджером
        double salary = 100000;
        double bonus = 0.1; // 10% бонус
        int workDays = 250; // количество рабочих дней

        double result = calculator.calculate(Positions.TL, salary, bonus, workDays);

        // Ожидаемое значение: базовый расчет + квартальная премия (salary * bonus)
        double expectedBaseSalary = salary * bonus * 365 * Positions.TL.getPositionCoefficient() / workDays;
        double expectedBonus = salary * bonus; // квартальная премия
        double expectedTotal = expectedBaseSalary + expectedBonus;

        assertEquals(expectedTotal, result, 0.001); // Проверка с допуском
    }

    @Test
    public void testCalculateForNonManagerWithoutBonus() {
        // Данные для Junior, который не является менеджером
        double salary = 50000;
        double bonus = 0.05; // 5% бонус
        int workDays = 250;

        double result = calculator.calculate(Positions.JUN, salary, bonus, workDays);

        // Ожидаемое значение: только базовый расчет, без квартальной премии
        double expectedBaseSalary = salary * bonus * 365 * Positions.JUN.getPositionCoefficient() / workDays;
        assertEquals(expectedBaseSalary, result, 0.001);
    }

    @Test
    public void testCalculateForLeapYear() {
        // Мы можем протестировать расчет на високосный год
        double salary = 80000;
        double bonus = 0.1;
        int workDays = 250;

        // Проверка для Team Lead (высокий коэффициент и менеджер)
        double result = calculator.calculate(Positions.TL, salary, bonus, workDays);

        // Ожидаемое значение с учетом 366 дней в високосный год
        double expectedBaseSalary = salary * bonus * 366 * Positions.TL.getPositionCoefficient() / workDays;
        double expectedBonus = salary * bonus;
        double expectedTotal = expectedBaseSalary + expectedBonus;

        assertEquals(expectedTotal, result, 0.001);
    }

    @Test
    public void testCalculateForNonLeapYear() {
        // Данные для обычного года
        double salary = 90000;
        double bonus = 0.1;
        int workDays = 250;

        // Пример для разработчика (DEV)
        double result = calculator.calculate(Positions.DEV, salary, bonus, workDays);

        // Ожидаемое значение для обычного года (365 дней)
        double expectedBaseSalary = salary * bonus * 365 * Positions.DEV.getPositionCoefficient() / workDays;
        assertEquals(expectedBaseSalary, result, 0.001);
    }
}

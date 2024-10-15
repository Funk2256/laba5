package ru.krasheninnikov.SecondApp.service;

import org.springframework.stereotype.Service;
import ru.krasheninnikov.SecondApp.model.Positions;

import java.time.LocalDate;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService{
    public double calculate(Positions position, double salary, double bonus, int workDays) {
        int yearDays = getYearDays(); // получаем количество дней в году
        double baseCalculation = salary * bonus * yearDays * position.getPositionCoefficient() / workDays;

        // Проверка на управленца и добавление квартальной премии, если это менеджер
        if (position.isManager()) {
            double quarterlyBonus = calculateQuarterlyBonus(salary, bonus);
            baseCalculation += quarterlyBonus; // добавляем премию к основному расчету
        }

        return baseCalculation;
    }

    // Метод для расчета квартальной премии
    private double calculateQuarterlyBonus(double salary, double bonusPercentage) {
        // Премия = зарплата * процент премии (например, 10% от зарплаты)
        return salary * bonusPercentage;
    }

    // Метод для получения количества дней в году (учитывая високосный год)
    private int getYearDays() {
        int year = LocalDate.now().getYear(); // получаем текущий год
        return isLeapYear(year) ? 366 : 365;  // проверяем, високосный ли год
    }

    // Проверка на високосный год
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}

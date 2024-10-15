package ru.krasheninnikov.SecondApp.service;

import ru.krasheninnikov.SecondApp.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int worksDay);
}

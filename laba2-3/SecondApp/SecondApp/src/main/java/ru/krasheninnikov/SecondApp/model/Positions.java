package ru.krasheninnikov.SecondApp.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    PR(2.0, false),
    DS(1.8, false),
    JUN(1.0, false),
    TL(2.6, true);  // TL считается менеджером

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}

package models;

import java.util.List;

public interface Analysis {
    /**
     * Рассчитывает средний балл для студентов заданного возраста.
     */
    double calculateAverageScoreByAge();

    /**
     * Рассчитывает средний балл для студентов в возрастном диапазоне.
     */
    double calculateAverageScoreByAgeRange();
}
package models;

import java.util.List;

public interface Analysis {
    /**
     * Рассчитывает средний балл для студентов заданного возраста.
     *
     * @param performances список успеваемости студентов
     * @param age возраст студентов
     * @return средний балл для заданного возраста
     */
    double calculateAverageScoreByAge(List<Performance> performances, int age);

    /**
     * Рассчитывает средний балл для студентов в возрастном диапазоне.
     *
     * @param performances список успеваемости студентов
     * @param minAge минимальный возраст
     * @param maxAge максимальный возраст
     * @return средний балл для указанного возрастного диапазона
     */
    double calculateAverageScoreByAgeRange(List<Performance> performances, int minAge, int maxAge);
}
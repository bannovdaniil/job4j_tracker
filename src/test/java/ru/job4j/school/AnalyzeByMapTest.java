package ru.job4j.school;

import org.junit.jupiter.api.Test;
import ru.job4j.school.model.Label;
import ru.job4j.school.model.Pupil;
import ru.job4j.school.model.Subject;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class AnalyzeByMapTest {
    @Test
    void whenAverageScore() {
        double average = AnalyzeByMap.averageScore(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 70),
                                        new Subject("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 80),
                                        new Subject("Lang", 90),
                                        new Subject("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subject("Math", 70),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).isCloseTo(74.44, offset(0.01D));
    }

    @Test
    void whenListOfPupilAverage() {
        List<Label> average = AnalyzeByMap.averageScoreByPupil(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 80),
                                        new Subject("Lang", 90),
                                        new Subject("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subject("Math", 70),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Label("Ivanov", 80D),
                new Label("Petrov", 80D),
                new Label("Sidorov", 60D)
        ));
    }

    @Test
    void whenListOfSubjectAverage() {
        List<Label> average = AnalyzeByMap.averageScoreBySubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 70),
                                        new Subject("Lang", 90),
                                        new Subject("Philosophy", 100)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Lang", 60),
                                        new Subject("Math", 60),
                                        new Subject("Philosophy", 60)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subject("Philosophy", 50),
                                        new Subject("Lang", 60),
                                        new Subject("Math", 80)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Label("Math", 70D),
                new Label("Lang", 70D),
                new Label("Philosophy", 70D)
        ));
    }

    @Test
    void whenBestPupil() {
        Label best = AnalyzeByMap.bestStudent(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 80),
                                        new Subject("Lang", 80),
                                        new Subject("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subject("Math", 70),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new Label("Ivanov", 240D));
    }

    @Test
    void whenBestSubject() {
        Label best = AnalyzeByMap.bestSubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Lang", 90),
                                        new Subject("Math", 80),
                                        new Subject("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subject("Philosophy", 50),
                                        new Subject("Math", 70),
                                        new Subject("Lang", 60)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new Label("Math", 250D));
    }
}
package ru.job4j.lombok;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Getter
public class Category {
    @NonNull
    @EqualsAndHashCode.Include
    private final int id;
    @Setter
    @EqualsAndHashCode.Exclude
    private String name;
}

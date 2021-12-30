package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * примеры работы со stream
 */
public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    /**
     * в stream важен порядок блоков.
     * map() меняет тип стрема в цепочке.
     * елси сначала map() тогда работаем с Address
     * все что до этого работаем с Profile
     * @param profiles список профилей
     * @return возращаем адреса без дублей и сорт по улице
     */
    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        Comparator<Address> comporeCity = (a, b) ->
                a.getCity().compareTo(b.getCity());
        return profiles.stream()
                .map(Profile::getAddress)
                .sorted(comporeCity)
                .distinct()
                .collect(Collectors.toList());
    }
}

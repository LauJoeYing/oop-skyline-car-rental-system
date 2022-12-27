package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchUtils {                  //Utilities for Search Bar Filter Feature
    public static <T extends Searchable> List<T> search(List<T> searchableModelList, String searchKeys) {
        String[] searchKeyList = searchKeys.split(" ");
        List<T> matchingModelList = new ArrayList<>(searchableModelList);

        for (String searchKey : searchKeyList) {
            String searchKeyRegex = String.format("^.*%s.*$", searchKey);
            Pattern searchKeyPattern = Pattern.compile(searchKeyRegex, Pattern.CASE_INSENSITIVE);

            matchingModelList.retainAll(
                    searchableModelList.stream()
                            .filter(searchableModel ->
                                    searchableModel.getSearchableProperties()
                                            .stream()
                                            .anyMatch(property ->
                                                    searchKeyPattern.matcher(property)
                                                            .matches()
                                            )
                            )
                            .toList()
            );
        }

        return matchingModelList;
    }
}

package seedu.duke.util;

import seedu.duke.constants.EntryConstants;
import seedu.duke.entries.Category;

public class CategoryUtil {

    public static Category convertStringToCategory(String categoryString) {
        switch (categoryString) {
        case EntryConstants.CLOTHING:
            return Category.CLOTHING;
        case EntryConstants.ENTERTAINMENT:
            return Category.ENTERTAINMENT;
        case EntryConstants.FOOD:
            return Category.FOOD;
        case EntryConstants.MEDICAL:
            return Category.MEDICAL;
        case EntryConstants.OTHERS:
            return Category.OTHERS;
        case EntryConstants.PERSONAL:
            return Category.PERSONAL;
        case EntryConstants.UTILITIES:
            return Category.UTILITIES;
        case EntryConstants.TRANSPORTATION:
            return Category.TRANSPORTATION;
        default:
            throw new IllegalArgumentException();
        }
    }

}

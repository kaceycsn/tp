package seedu.duke.entrylog;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryLog {
    private List<Entry> entries;

    public EntryLog() {
        this.entries = new ArrayList<>();
    }

    public EntryLog(List<Entry> entries) {
        this.entries = entries;
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    public void delete(int entryId) {
        entries.remove(entryId);
    }

    /**
     * Filter entries by category
     *
     * @param category Category to be filtered
     * @return List of entries matching category
     */
    public List<Entry> filterCategory(Category category) {
        return filterCategory(category, entries);
    }

    /**
     * Filter entries by category
     *
     * @param category Category to be filtered
     * @param entries  List of entries to match
     * @return List of entries matching category
     */
    public static List<Entry> filterCategory(Category category, List<Entry> entries) {
        return entries
                .stream()
                .filter((entry -> entry.getCategory() == category))
                .collect(Collectors.toList());
    }

    /**
     * Filter entries by description or category of entries. Regular expressions are supported.
     *
     * @param query Regex to be filtered (case-insensitive)
     * @return List of entries matching query
     */
    public List<Entry> filterByQuery(String query) {
        return filterByQuery(query, entries);
    }

    /**
     * Filter entries by description or category of entries. Regular expressions are supported.
     *
     * @param query   Regex to be filtered (case-insensitive)
     * @param entries List of entries to match
     * @return List of entries matching query
     */
    public static List<Entry> filterByQuery(String query, List<Entry> entries) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        return entries
                .stream()
                .filter(entry -> {
                    Matcher descriptionMatcher = pattern.matcher(entry.getDescription());
                    Matcher categoryMatcher = pattern.matcher(entry.getCategoryString());
                    boolean isMatch = descriptionMatcher.find() || categoryMatcher.find();
                    return isMatch;
                })
                .collect(Collectors.toList());
    }

    /**
     * Filter items by amount.
     *
     * @param minAmount Minimum amount of entry
     * @param maxAmount Maximum amount of entry
     * @return List of entries within range of given amount
     */
    public List<Entry> filterAmount(double minAmount, double maxAmount) {
        return filterAmount(minAmount, maxAmount, entries);
    }

    /**
     * Filter items by price.
     *
     * @param minAmount Minimum amount of entry
     * @param maxAmount Maximum amount of entry
     * @param entries   List of entries to match
     * @return List of entries within range of given amount
     */
    public static List<Entry> filterAmount(double minAmount, double maxAmount, List<Entry> entries) {
        return entries
                .stream()
                .filter((entry -> {
                    boolean isBelowMin = Double.compare(entry.getAmount(), minAmount) < 0;
                    boolean isAboveMax = Double.compare(entry.getAmount(), maxAmount) > 0;
                    boolean isWithinRange = !isBelowMin && !isAboveMax;
                    return isWithinRange;
                }))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return entries.size();
    }

    public List<Entry> getEntries() {
        return entries;
    }
}

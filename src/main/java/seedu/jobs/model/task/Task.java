package seedu.jobs.model.task;

import java.util.Objects;

import seedu.jobs.commons.util.CollectionUtil;
import seedu.jobs.model.tag.UniqueTagList;

public class Task implements ReadOnlyTask {

    private Name name;
    private Time startTime;
    private Time endTime;
    private Description description;
    private boolean isCompleted;
    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time startTime, Time endTime, Description description, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.tags = new UniqueTagList(tags); // protect internal tags from
                                             // changes in the arg list
        this.isCompleted = false;
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getStartTime(), source.getEndTime(), source.getDescription(), source.getTags());
        this.isCompleted = source.isCompleted();
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    public void setStartTime(Time startTime) {
        assert startTime != null;
        this.startTime = startTime;
    }

    @Override
    public Time getStartTime() {
        return startTime;
    }

    public void setEndTime(Time endTime) {
        assert endTime != null;
        this.endTime = endTime;
    }

    @Override
    public Time getEndTime() {
        return endTime;
    }

    public void setDescription(Description description) {
        assert description != null;
        this.description = description;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setStartTime(replacement.getStartTime());
        this.setEndTime(replacement.getEndTime());
        this.setDescription(replacement.getDescription());
        this.setTags(replacement.getTags());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                        && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing
        // your own
        return Objects.hash(name, startTime, endTime, description, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }
}

package seedu.jobs.model.task;

public interface ReadOnlyTask {
		
	Name getName();
	Time getStartTime();
	Time getEndTime();
	Description getDescription();
	boolean isCompleted();
	
	
	/**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime())
                && other.isCompleted()==(this.isCompleted()));
    }
	
	/**
     * Formats the task as text, showing all the details.
     */
	default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Task: ")
                .append(getName())
                .append(" From: ")
                .append(getStartTime())
                .append(" To: ")
                .append(getEndTime())
                .append(" Status : ")
                .append(isCompleted())
                .append(" Description: ")
                .append(getDescription());
        return builder.toString();
    }
	
	
}

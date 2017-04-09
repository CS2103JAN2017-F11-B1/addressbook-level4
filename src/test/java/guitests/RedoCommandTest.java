package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.jobs.logic.commands.RedoCommand.MESSAGE_FAILURE;
import static seedu.jobs.logic.commands.RedoCommand.MESSAGE_SUCCESS;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Test;

import seedu.jobs.model.task.UniqueTaskList.IllegalTimeException;
import seedu.jobs.testutil.TestTask;
import seedu.jobs.testutil.TestUtil;

//@@author A0140055W

public class RedoCommandTest extends TaskBookGuiTest {
    private final Stack<TestTask> testStack = new Stack<TestTask>();

    @Test
    public void redo() throws IllegalArgumentException, IllegalTimeException, EmptyStackException {
        commandBox.runCommand("redo");
        assertResultMessage(MESSAGE_FAILURE);
        TestTask[] currentList = td.getTypicalTasks();
        // add a task for undoing to enable redoing
        TestTask taskToAdd = td.CS4101;
        commandBox.runCommand(td.CS4101.getAddCommand());
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);
        undoForRedo(currentList);
        // TestTask taskUndone1 = currentList[currentList.length - 1];
        currentList = TestUtil.removeTasksFromList(currentList, taskToAdd);
        assertRedoSuccess(currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);
        // add another task
        taskToAdd = td.CS4102;
        commandBox.runCommand(td.CS4102.getAddCommand());
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);
        // undo two times for upcoming double undo test
        TestTask taskUndone1 = currentList[currentList.length - 1];
        undoForRedo(currentList);
        currentList = TestUtil.removeTasksFromList(currentList, taskUndone1);
        TestTask taskUndone2 = currentList[currentList.length - 1];
        undoForRedo(currentList);
        currentList = TestUtil.removeTasksFromList(currentList, taskUndone2);
        // double redo test
        assertRedoSuccess(currentList);
        currentList = TestUtil.addTasksToList(currentList, taskUndone2);
        assertRedoSuccess(currentList);
        currentList = TestUtil.addTasksToList(currentList, taskUndone1);
        commandBox.runCommand("redo");
        assertResultMessage(MESSAGE_FAILURE);
    }

    private void undoForRedo(TestTask[] currentList)
            throws IllegalArgumentException, IllegalTimeException, EmptyStackException {
        TestTask taskUndone = currentList[currentList.length - 1];
        testStack.push(taskUndone);
        commandBox.runCommand("undo");
    }

    private void assertRedoSuccess(TestTask[] currentList)
            throws IllegalArgumentException, IllegalTimeException, EmptyStackException {
        TestTask taskToBeRedone = testStack.pop();
        TestTask[] expectedResult = TestUtil.addTasksToList(currentList, taskToBeRedone);
        commandBox.runCommand("redo");
        // confirm the resultant list after redoing matches the original
        assertTrue(taskListPanel.isListMatching(expectedResult));
        // confirm that the result message is correct
        assertResultMessage(String.format(MESSAGE_SUCCESS, taskToBeRedone));
    }
}
package ru.parsentev.start;

public class StartUITest {

	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"create stub task", "desc stub task", 
			"create stub task", "create stub task", "create new stub task", "new desc stub task",
			"create new stub task", "new comment", "yes", "create new stub task", "yes"});

		new StartUI(input).init();
	}
} 
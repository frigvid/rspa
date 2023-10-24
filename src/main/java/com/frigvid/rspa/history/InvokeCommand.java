package com.frigvid.rspa.history;

import java.util.Stack;

// Commands need to also update the sidebar.
public class InvokeCommand
{
	// These need to be static to actually be shared across all instances of InvokeCommand.
	private final static Stack<Command> undoStack = new Stack<>();
	private final static Stack<Command> redoStack = new Stack<>();
	private final static boolean DEBUG = true;
	
	public void execute(Command command)
	{
		if (DEBUG) {System.out.println("Executing command...");}
		
		command.execute();
		undoStack.push(command);
		redoStack.clear(); // clear redo stack when new command is executed.
	}
	
	public void undo()
	{
		if (!undoStack.isEmpty())
		{
			if (DEBUG) {System.out.println("Undo stack size: " + undoStack.size());}
			
			Command previousUndoCmd = undoStack.pop();
			previousUndoCmd.undo();
			redoStack.push(previousUndoCmd);
		}
		else
		{
			if (DEBUG) {System.out.println("Nothing to undo...");}
		}
	}
	
	public void redo()
	{
		if (!redoStack.isEmpty())
		{
			if (DEBUG) {System.out.println("Redo stack size: " + redoStack.size());}
			
			Command previousRedoCmd = redoStack.pop();
			previousRedoCmd.execute();
			undoStack.push(previousRedoCmd);
		}
		else
		{
			if (DEBUG) {System.out.println("Nothing to redo...");}
		}
	}
}

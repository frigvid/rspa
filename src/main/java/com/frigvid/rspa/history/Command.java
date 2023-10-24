package com.frigvid.rspa.history;

public interface Command
{
	void execute();
	void undo();
}

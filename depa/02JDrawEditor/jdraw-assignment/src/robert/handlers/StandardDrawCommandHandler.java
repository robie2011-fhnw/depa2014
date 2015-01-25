package robert.handlers;

import java.util.Stack;

import robert.commands.IScriptableCommand;
import robert.stdcontext.CommandListener;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

public class StandardDrawCommandHandler implements DrawCommandHandler {
	Stack<DrawCommand> undoCommands;
	Stack<DrawCommand> redoCommands;
	boolean isScripted = false;
	DrawCommand lastCommand;
	
	public StandardDrawCommandHandler() {
		clearHistory();
	}
	
	@Override
	public void redo() {
		if(!redoPossible()) return;
		
		DrawCommand cmd = redoCommands.pop();
		cmd.redo();
		undoCommands.add(cmd);
		CommandListener.sendUpdate();
	}

	@Override
	public void undo() {
		if(!undoPossible()) return;
		
		DrawCommand cmd = undoCommands.pop();
		cmd.undo();
		redoCommands.add(cmd);
		CommandListener.sendUpdate();
	}

	@Override
	public void addCommand(DrawCommand cmd) {
		// kontrolstruktur
		if(!isScripted) recordCommand(cmd);
		
		if(lastCommand==null) {
			lastCommand = cmd;
			return; // waiting for next iteration
		}
		
		if(lastCommand instanceof IScriptableCommand 
				&& cmd instanceof IScriptableCommand
				&& ((IScriptableCommand) lastCommand).tryToAdd((IScriptableCommand) cmd) != null ){
			lastCommand = ((IScriptableCommand) lastCommand).tryToAdd((IScriptableCommand) cmd);
		}else{			
			recordCommand(cmd);
		}
	}
	
	private void recordCommand(DrawCommand cmd){
		undoCommands.add(cmd);
		redoCommands = new Stack<DrawCommand>();
		CommandListener.sendUpdate();
	}

	@Override
	public boolean undoPossible() {
		return !undoCommands.isEmpty();
	}

	@Override
	public boolean redoPossible() {
		return !redoCommands.isEmpty();
	}

	@Override
	public void beginScript() {
		isScripted = true;
		System.out.println("beginScript");
	}

	@Override
	public void endScript() {
		isScripted = false;
		System.out.println("endScript");
		if(lastCommand != null){
			recordCommand(lastCommand);
			lastCommand = null;
		}
	}

	@Override
	public void clearHistory() {
		undoCommands = new Stack<DrawCommand>();
		redoCommands = new Stack<DrawCommand>();
		CommandListener.sendUpdate();
	}

}

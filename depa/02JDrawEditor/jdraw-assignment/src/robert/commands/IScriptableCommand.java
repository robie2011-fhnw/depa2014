package robert.commands;

import jdraw.framework.DrawCommand;

public interface IScriptableCommand extends DrawCommand {
	IScriptableCommand tryToAdd(IScriptableCommand cmd);
}

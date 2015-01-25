package robert.drawtoolfactory;

import jdraw.figures.GenericAbstractTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;

public abstract class AbstractToolFactory<T> implements DrawToolFactory {
	String name, iconName;
	Class<T> className;
	
	public AbstractToolFactory(Class<T> className) {
		this.className = className;
	}
	
	@Override
	public String getIconName() {
		return iconName;
	}

	@Override
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public DrawTool createTool(DrawContext context) {		
		return new GenericAbstractTool<T>(context, className);
	}

}

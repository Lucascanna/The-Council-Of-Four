package view;

import controller.Controller;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable implements Observer {

	public View(Controller controller){
		controller.registerObserver(this);
	}
	
	public abstract <I> void input(I input);
	
	@Override
	public abstract <C> void update(Observable o, C change);

}

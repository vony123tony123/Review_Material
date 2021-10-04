import java.awt.Color;

import javax.swing.ImageIcon;

public class Road extends GamePlaceObject {

	public Road() {
		setForeground(Color.white);

	}
	
	@Override
	protected void Changelife() {
		currentlife-=5;
		if(currentlife<0)
			currentlife=0;
	}

}

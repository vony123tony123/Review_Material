import javax.swing.ImageIcon;

public class Wall extends GamePlaceObject {

	public Wall() {
		setIcon(new ImageIcon(
						getScaledImage(new ImageIcon("./brickwall.png").getImage())
						));

	}

	@Override
	protected void Changelife() {
		currentlife-=20;
		if(currentlife<0)
			currentlife=0;
	}

}

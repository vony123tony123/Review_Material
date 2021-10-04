import javax.swing.ImageIcon;

public class Ghost extends GamePlaceObject {

	public Ghost() {
		setIcon(new ImageIcon(
				getScaledImage(new ImageIcon("./ghost.png").getImage())
				));
	}

	@Override
	protected void Changelife() {
		currentlife=-1;
	}

}

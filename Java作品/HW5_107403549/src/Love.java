import javax.swing.ImageIcon;

public class Love extends GamePlaceObject {

	public Love() {
		setIcon(new ImageIcon(
				getScaledImage(new ImageIcon("./heart.png").getImage())
				));
	}

	@Override
	protected void Changelife() {
		currentlife+=30;
		if(currentlife>100)
			currentlife=100;
	}

}

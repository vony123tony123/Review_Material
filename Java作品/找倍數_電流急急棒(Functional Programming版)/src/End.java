import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class End extends GamePlaceObject {

	public End() {
		setIcon(new ImageIcon(
				getScaledImage(new ImageIcon("./diamond.png").getImage())
				));
	}

	@Override
	protected void Changelife() {
		JOptionPane.showMessageDialog(null, "§AÄ¹¤F");
		System.exit(0);
	}

}

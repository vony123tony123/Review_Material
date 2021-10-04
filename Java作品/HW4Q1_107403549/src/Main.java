/* �@��:
 * 107403549 ��ޤT �\�ئW
 */
import java.awt.image.TileObserver;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import sun.launcher.resources.launcher;

public class Main {

	public static void main(String[] args) {
		
		JTextField inputnumberField=new JTextField();
		JTextField inputrangeField=new JTextField();
		Object[] inputMessage= {
				"��J�n�䪺���ƼƦr",inputnumberField,
				"��J�d��A��X�d�򤺪�����",inputrangeField
		};
		
		boolean inputloop=true;
		int inputNum=0;
		int inputrange_max=0;
		
		do {
			try {
				
				int stateJudge=JOptionPane.showConfirmDialog(null, inputMessage,"Enter all your values",
						JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE);
				
				if(stateJudge==JOptionPane.OK_OPTION) {
					inputNum=Integer.parseInt(inputnumberField.getText());
					inputrange_max=Integer.parseInt(inputrangeField.getText());
					inputloop=false;
				}else {
					System.exit(0);
				}
			} catch (NumberFormatException e) {
				inputnumberField.setText("");
				inputrangeField.setText("");
			}
		}while(inputloop);
		
		int multNum=1;
		Set set=new HashSet<Integer>();
		String title= inputNum+"�b"+inputrange_max+"��������";
		
		if(inputrange_max<inputNum)
		{
			JOptionPane.showMessageDialog(null, "�d���Ʀr�٤p",title,JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		
		while(true) {
			int multiple=inputNum*multNum;
			multNum++;
			
			if(multiple>inputrange_max) 
				break;
			
			set.add(multiple);
		}
	
		String message="";
		set=new TreeSet<Integer>(set);
		Iterator<Integer> iter=set.iterator();
		
		while(iter.hasNext()) {
			message+=iter.next();
			if(iter.hasNext())
				message+=", ";
		}
		
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
		
		
		
	}

}

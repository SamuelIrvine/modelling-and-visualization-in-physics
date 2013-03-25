package simulation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display<T extends State> implements Output<T>{
	
	private JFrame frame;
	private JPanel panel;
	private T state;
	
	public Display(String title){
		frame=new JFrame();
		frame.setSize(500,500);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				if (state!=null){
					state.render(g);
				}else{
					g.setColor(Color.black);
					g.drawString("Loading...", 20, 20);
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void dump(T s) {
		state=s;
		frame.repaint();
	}
	
	public JFrame getJFrame(){
		return frame;
	}
	
	public JComponent getMainComponent(){
		return panel;
	}
}

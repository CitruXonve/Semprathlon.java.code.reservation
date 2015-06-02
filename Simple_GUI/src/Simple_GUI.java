import javax.swing.*;

public class Simple_GUI {
	public static void main(String[] argc){
		JFrame frame=new JFrame();
		JButton OpenButton=new JButton("Open");
		JButton ProcessButton=new JButton("Process");
		JButton SaveTextButton=new JButton("SaveAsText");
		JButton SaveImageButton=new JButton("SaveAsImage");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(OpenButton);
		frame.getContentPane().add(ProcessButton);
		frame.getContentPane().add(SaveTextButton);
		frame.getContentPane().add(SaveImageButton);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setTitle("Simple GUI");
	}
}

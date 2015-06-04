import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Window {

	private JFrame frmDemoOfFunction;
	private JTextField txtResizewidth;
	private JTextField txtMasktext;
	private JTextField txtResizeheight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmDemoOfFunction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDemoOfFunction = new JFrame();
		frmDemoOfFunction.setTitle("Demo of function");
		frmDemoOfFunction.setBounds(100, 100, 785, 333);
		frmDemoOfFunction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmDemoOfFunction.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnOpen = new JButton("Open");
		
		JButton btnProceed = new JButton("Proceed");
		
		JButton btnSavetext = new JButton("SaveText");
		
		JButton btnSaveimage = new JButton("SaveImage");
		
		JLabel lblResize = new JLabel("Resize: W:");
		
		txtResizewidth = new JTextField();
		txtResizewidth.setText("ResizeWidth");
		txtResizewidth.setColumns(10);
		
		txtMasktext = new JTextField();
		txtMasktext.setText("MaskText");
		txtMasktext.setColumns(10);
		
		JLabel lblResizeheight = new JLabel("X H:");
		
		txtResizeheight = new JTextField();
		txtResizeheight.setText("ResizeHeight");
		txtResizeheight.setColumns(10);
		
		JLabel lblX = new JLabel("X");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(btnOpen)
					.addGap(6)
					.addComponent(btnProceed)
					.addGap(6)
					.addComponent(btnSavetext)
					.addGap(6)
					.addComponent(btnSaveimage)
					.addGap(6)
					.addComponent(txtMasktext, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblResize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtResizewidth, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblResizeheight)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtResizeheight, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblX)
					.addGap(16))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnOpen))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnProceed))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnSavetext))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnSaveimage))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMasktext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResize)
								.addComponent(txtResizewidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResizeheight)
								.addComponent(txtResizeheight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblX)))
					.addContainerGap(261, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}

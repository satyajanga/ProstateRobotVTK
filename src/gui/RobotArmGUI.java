package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import vtk.DisplaySTL;

public class RobotArmGUI {

	public static RobotArmGUI window;
	public DisplaySTL dispSTL=new DisplaySTL(this);

	private JFrame frame;	
	private JPanel centerPanel;
	private JPanel vtkPanel;
	private JPanel ctrlPanel;
	private JLabel lblCurrentStatus;
	private JButton btnGenerate;
	private JButton btnReset;	

	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblZ;	
	private JLabel lblPitch;	
	private JLabel lblRoll;	
	private JLabel lblJoint_1;
	private JLabel lblJoint_2;
	private JLabel lblJoint_3;
	private JLabel lblJoint_4;
	private JLabel lblJoint_5;
	private JLabel lblNeedleRot;
	private JLabel lblInputParameters;	
	private JLabel lblStageX;
	private JLabel lblStageY;
	private JLabel lblStageZ;
	private JLabel lblONeedle;
	private JLabel lblINeedle;
	private JLabel lblViewpointControl;
	private JLabel lblJointDemo;
	private JLabel lblJoint_6;
	private JLabel lblJoint_7;
	private JLabel lblJoint_8;
	private JLabel lblJoint_9;
	private JLabel lblJoint_10;
	private JLabel lblNeedlerot;
	private JLabel lblMriTransparency;

	public JLabel lblShowX;
	public JLabel lblShowY;
	public JLabel lblShowZ;
	public JLabel lblShowOuterneedle;
	public JLabel lblShowInnerneedle;
	public JSlider sldStageX;	
	public JSlider sldStageY;	
	public JSlider sldStageZ;	
	public JSlider sldINeedle;	
	public JSlider sldONeedle;
	public JSlider sldMRI;
	public JSlider sldNeedleRot;
	public JTextField txtStageY;
	public JTextField txtStageZ;
	public JTextField txtStageX;
	public JTextField txtINeedle;
	public JTextField txtONeedle;
	public JTextField txtNeedleRot;
	public JRadioButton rdbtnFront;
	public JRadioButton rdbtnTop;
	public JRadioButton rdbtnLeft;
	public JRadioButton rdbtnBack;
	public JRadioButton rdbtnRight;
	public JRadioButton rdbtnOriginal;

	public boolean errFlag=true;
	public long debugCount=0;
	public boolean isGenerating=false;
	private JLabel lblDegree_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new RobotArmGUI();
					window.frame.setVisible(true);
					//window.dispSTL.renWin.Render();
					//window.dispSTL.renWin.GetRenderer().GetActiveCamera().Dolly(1);
					//window.dispSTL.renWin.GetRenderer().ResetCamera();
					window.dispSTL.translateStageToPosX(0+DisplaySTL.STAGE_MAX_TRANS_X);
					window.dispSTL.translateStageToPosY(0);
					window.dispSTL.translateStageToPosZ(0);
					//window.dispSTL.renWin.Render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});

		System.out.println("Program Starts!");
	}

	/**
	 * Create the application.
	 */
	public RobotArmGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel GetContentPanel()
	{
		return centerPanel;
	}

	private void initialize() {
		frame= new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 613, 490);
		frame.setSize(980, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel);
		centerPanel.setLayout(null);		

		vtkPanel = new JPanel();
		vtkPanel.setBorder(new TitledBorder(null, "Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		vtkPanel.setBounds(10, 11, 667, 615);
		centerPanel.add(vtkPanel);
		//DisplaySTL dispSTL=new DisplaySTL();
		dispSTL.setBounds(10, 21, 647, 586);
		dispSTL.renWin.setBounds(10, 10, 630, 570);
		vtkPanel.add(dispSTL);

		ctrlPanel = new JPanel();
		ctrlPanel.setBorder(new TitledBorder(null, "Command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ctrlPanel.setBounds(687, 11, 267, 615);
		centerPanel.add(ctrlPanel);
		ctrlPanel.setLayout(null);

		lblCurrentStatus = new JLabel("Current Status:");
		lblCurrentStatus.setBounds(10, 22, 92, 14);
		ctrlPanel.add(lblCurrentStatus);

		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(29, 277, 89, 23);
		ctrlPanel.add(btnGenerate);
		btnGenerate.addMouseListener(new GenerateMouseListener(this));

		btnReset = new JButton("Reset");
		btnReset.setBounds(154, 277, 89, 23);
		ctrlPanel.add(btnReset);
		btnReset.addMouseListener(new ResetMouseListener(this));

		lblX = new JLabel("X:");
		lblX.setBounds(20, 48, 17, 14);
		ctrlPanel.add(lblX);

		lblY = new JLabel("Y:");
		lblY.setBounds(101, 48, 17, 14);
		ctrlPanel.add(lblY);

		lblZ = new JLabel("Z:");
		lblZ.setBounds(184, 48, 17, 14);
		ctrlPanel.add(lblZ);

		lblShowX = new JLabel("00.00 mm");
		lblShowX.setBounds(35, 48, 67, 14);
		ctrlPanel.add(lblShowX);

		lblShowY = new JLabel("00.00 mm");
		lblShowY.setBounds(118, 48, 63, 14);
		ctrlPanel.add(lblShowY);

		lblShowZ = new JLabel("00.00 mm");
		lblShowZ.setBounds(201, 48, 63, 14);
		ctrlPanel.add(lblShowZ);

		lblPitch = new JLabel("O-Needle:");
		lblPitch.setBounds(20, 73, 57, 14);
		ctrlPanel.add(lblPitch);

		lblShowOuterneedle = new JLabel("00.00 mm");
		lblShowOuterneedle.setBounds(80, 73, 57, 14);
		ctrlPanel.add(lblShowOuterneedle);

		lblRoll = new JLabel("I-Needle:");
		lblRoll.setBounds(148, 73, 53, 14);
		ctrlPanel.add(lblRoll);

		lblShowInnerneedle = new JLabel("00.00 mm");
		lblShowInnerneedle.setBounds(201, 73, 63, 14);
		ctrlPanel.add(lblShowInnerneedle);

		lblJoint_1 = new JLabel("Stage X:");
		lblJoint_1.setBounds(35, 120, 57, 14);
		ctrlPanel.add(lblJoint_1);

		lblJoint_2 = new JLabel("Stage Y:");
		lblJoint_2.setBounds(35, 145, 46, 14);
		ctrlPanel.add(lblJoint_2);

		lblJoint_3 = new JLabel("Stage Z:");
		lblJoint_3.setBounds(35, 170, 46, 14);
		ctrlPanel.add(lblJoint_3);

		lblJoint_4 = new JLabel("O-Needle:");
		lblJoint_4.setBounds(35, 195, 57, 14);
		ctrlPanel.add(lblJoint_4);

		lblJoint_5 = new JLabel("I-Needle:");
		lblJoint_5.setBounds(35, 220, 57, 14);
		ctrlPanel.add(lblJoint_5);

		lblNeedlerot = new JLabel("NeedleRot:");
		lblNeedlerot.setBounds(35, 245, 67, 14);
		ctrlPanel.add(lblNeedlerot);

		lblInputParameters = new JLabel("Input Parameters:");
		lblInputParameters.setBounds(10, 98, 108, 14);
		ctrlPanel.add(lblInputParameters);

		txtStageX = new JTextField();
		txtStageX.setHorizontalAlignment(SwingConstants.CENTER);
		txtStageX.setText("0.0");
		txtStageX.setColumns(10);
		txtStageX.setBounds(103, 117, 57, 20);
		ctrlPanel.add(txtStageX);
		txtStageX.addFocusListener(new TextFieldFocusListener());

		txtStageY = new JTextField();
		txtStageY.setHorizontalAlignment(SwingConstants.CENTER);
		txtStageY.setText("0.0");
		txtStageY.setBounds(103, 142, 57, 20);
		ctrlPanel.add(txtStageY);
		txtStageY.setColumns(10);
		txtStageY.addFocusListener(new TextFieldFocusListener());

		txtStageZ = new JTextField();
		txtStageZ.setHorizontalAlignment(SwingConstants.CENTER);
		txtStageZ.setText("0.0");
		txtStageZ.setColumns(10);
		txtStageZ.setBounds(103, 167, 57, 20);
		ctrlPanel.add(txtStageZ);
		txtStageZ.addFocusListener(new TextFieldFocusListener());		

		txtONeedle = new JTextField();
		txtONeedle.setHorizontalAlignment(SwingConstants.CENTER);
		txtONeedle.setText("0.0");
		txtONeedle.setColumns(10);
		txtONeedle.setBounds(103, 192, 57, 20);
		ctrlPanel.add(txtONeedle);
		txtONeedle.addFocusListener(new TextFieldFocusListener());

		txtINeedle = new JTextField();
		txtINeedle.setHorizontalAlignment(SwingConstants.CENTER);
		txtINeedle.setText("0.0");
		txtINeedle.setColumns(10);
		txtINeedle.setBounds(103, 217, 57, 20);
		ctrlPanel.add(txtINeedle);
		txtINeedle.addFocusListener(new TextFieldFocusListener());

		txtNeedleRot = new JTextField();
		txtNeedleRot.setHorizontalAlignment(SwingConstants.CENTER);
		txtNeedleRot.setText("0.0");
		txtNeedleRot.setBounds(103, 242, 57, 20);
		ctrlPanel.add(txtNeedleRot);
		txtNeedleRot.setColumns(10);
		txtNeedleRot.addFocusListener(new TextFieldFocusListener());

		lblStageX = new JLabel("mm");
		lblStageX.setBounds(181, 117, 24, 14);
		ctrlPanel.add(lblStageX);

		lblStageY = new JLabel("mm");
		lblStageY.setBounds(181, 142, 24, 14);
		ctrlPanel.add(lblStageY);

		lblStageZ = new JLabel("mm");
		lblStageZ.setBounds(181, 167, 24, 14);
		ctrlPanel.add(lblStageZ);

		lblONeedle = new JLabel("mm");
		lblONeedle.setBounds(181, 195, 24, 14);
		ctrlPanel.add(lblONeedle);

		lblINeedle = new JLabel("mm");
		lblINeedle.setBounds(181, 220, 24, 14);
		ctrlPanel.add(lblINeedle);

		lblDegree_2 = new JLabel("degree");
		lblDegree_2.setBounds(181, 245, 46, 14);
		ctrlPanel.add(lblDegree_2);

		lblViewpointControl = new JLabel("Viewpoint Control:");
		lblViewpointControl.setBounds(10, 311, 142, 14);
		ctrlPanel.add(lblViewpointControl);

		lblJointDemo = new JLabel("Joint Demo:");
		lblJointDemo.setBounds(10, 392, 92, 14);
		ctrlPanel.add(lblJointDemo);

		sldStageZ = new JSlider();
		sldStageZ.setValue(0);
		sldStageZ.setBounds(77, 458, 173, 23);
		ctrlPanel.add(sldStageZ);
		sldStageZ.addChangeListener(new SliderChangeListener(this));

		lblJoint_6 = new JLabel("Stage X:");
		lblJoint_6.setBounds(10, 408, 57, 23);
		ctrlPanel.add(lblJoint_6);

		sldStageX = new JSlider();
		sldStageX.setValue(0);
		sldStageX.setBounds(77, 408, 173, 23);
		ctrlPanel.add(sldStageX);
		sldStageX.addChangeListener(new SliderChangeListener(this));

		lblJoint_7 = new JLabel("Stage Y:");
		lblJoint_7.setBounds(10, 433, 57, 23);
		ctrlPanel.add(lblJoint_7);

		sldStageY = new JSlider();
		sldStageY.setValue(0);
		sldStageY.setBounds(77, 433, 173, 23);
		ctrlPanel.add(sldStageY);
		sldStageY.addChangeListener(new SliderChangeListener(this));

		lblJoint_8 = new JLabel("Stage Z:");
		lblJoint_8.setBounds(10, 458, 57, 23);
		ctrlPanel.add(lblJoint_8);		

		lblJoint_9 = new JLabel("O-Needle:");
		lblJoint_9.setBounds(10, 483, 57, 23);
		ctrlPanel.add(lblJoint_9);

		sldONeedle = new JSlider();
		sldONeedle.setValue(0);
		sldONeedle.setBounds(77, 483, 173, 23);
		ctrlPanel.add(sldONeedle);
		sldONeedle.addChangeListener(new SliderChangeListener(this));

		sldINeedle = new JSlider();
		sldINeedle.setValue(0);
		sldINeedle.setBounds(77, 508, 173, 23);
		ctrlPanel.add(sldINeedle);
		sldINeedle.addChangeListener(new SliderChangeListener(this));

		lblJoint_10 = new JLabel("I-Needle:");
		lblJoint_10.setBounds(10, 508, 67, 23);
		ctrlPanel.add(lblJoint_10);	

		sldNeedleRot = new JSlider();
		sldNeedleRot.setValue(0);
		sldNeedleRot.setBounds(77, 533, 173, 23);
		ctrlPanel.add(sldNeedleRot);
		sldNeedleRot.addChangeListener(new SliderChangeListener(this));

		lblNeedleRot = new JLabel("NeedleRot:");
		lblNeedleRot.setBounds(10, 533, 67, 23);
		ctrlPanel.add(lblNeedleRot);

		lblMriTransparency = new JLabel("MRI Transparency:");
		lblMriTransparency.setBounds(10, 567, 125, 14);
		ctrlPanel.add(lblMriTransparency);

		sldMRI = new JSlider();
		sldMRI.setBounds(20, 585, 223, 23);
		ctrlPanel.add(sldMRI);
		vtkPanel.setLayout(null);
		sldMRI.addChangeListener(new SliderChangeListener(this));

		rdbtnFront = new JRadioButton("Front");
		rdbtnFront.setBounds(106, 332, 69, 23);
		ctrlPanel.add(rdbtnFront);
		rdbtnFront.addActionListener(new RadioBtnActionListener());

		rdbtnTop = new JRadioButton("Top");
		rdbtnTop.setBounds(181, 358, 67, 23);
		ctrlPanel.add(rdbtnTop);
		rdbtnTop.addActionListener(new RadioBtnActionListener());

		rdbtnLeft = new JRadioButton("Left");
		rdbtnLeft.setBounds(106, 358, 67, 23);
		ctrlPanel.add(rdbtnLeft);
		rdbtnLeft.addActionListener(new RadioBtnActionListener());

		rdbtnBack = new JRadioButton("Back");
		rdbtnBack.setBounds(181, 332, 67, 23);
		ctrlPanel.add(rdbtnBack);
		rdbtnBack.addActionListener(new RadioBtnActionListener());

		rdbtnRight = new JRadioButton("Right");
		rdbtnRight.setBounds(29, 358, 78, 23);
		ctrlPanel.add(rdbtnRight);
		rdbtnRight.addActionListener(new RadioBtnActionListener());

		rdbtnOriginal = new JRadioButton("Original");
		rdbtnOriginal.setSelected(true);
		rdbtnOriginal.setBounds(29, 332, 69, 23);
		ctrlPanel.add(rdbtnOriginal);		
		rdbtnOriginal.addActionListener(new RadioBtnActionListener());



	}

	private double roundNumber(double num){
		long temp=0;
		double result=0;

		temp=Math.round(num*100); // truncates   
		result=temp/100.0;  

		return result;
	}

	/**
	 * Update the text of labels for the status of the joints
	 */
	public void updateLabels(){

		String str1;
		String str2;
		String str3;
		String str4;
		String str5;

		str1=Double.toString(roundNumber(24-window.dispSTL.stageTranslationX));
		//System.out.println(window.dispSTL.stageTranslationY+" - "+DisplaySTL.STAGE_MIN_TRANS_Y+" = "+(window.dispSTL.stageTranslationY-DisplaySTL.STAGE_MIN_TRANS_Y));
		str2=Double.toString(roundNumber(window.dispSTL.stageTranslationY-DisplaySTL.STAGE_MIN_TRANS_Y));
		if(roundNumber(window.dispSTL.stageTranslationZ)==0)
			str3=Double.toString(0);
		else
			str3=Double.toString(-roundNumber(window.dispSTL.stageTranslationZ));


		//if(roundNumber(window.dispSTL.armRotateAngle)==0)
		//	str4=Double.toString(0);
		//else
		str4=Double.toString(roundNumber(window.dispSTL.outerneedleTranslation));
		str5=Double.toString(roundNumber(window.dispSTL.innerneedleTranslation));

		lblShowX.setText(str1+" mm");
		lblShowX.paintImmediately(lblShowX.getVisibleRect());
		lblShowY.setText(str2+" mm");
		lblShowY.paintImmediately(lblShowY.getVisibleRect());
		lblShowZ.setText(str3+" mm");
		lblShowZ.paintImmediately(lblShowZ.getVisibleRect());

		lblShowOuterneedle.setText(str4+" mm");
		lblShowOuterneedle.paintImmediately(lblShowOuterneedle.getVisibleRect());
		lblShowInnerneedle.setText(str5+" mm");
		lblShowInnerneedle.paintImmediately(lblShowInnerneedle.getVisibleRect());
	}

	/**
	 * Update the textField from user raw inputs to modified inputs that can be accepted
	 * @param joint1
	 * @param joint2
	 * @param joint3
	 * @param joint4
	 * @param joint5
	 */
	public void updateTextField(double joint1,double joint2,double joint3,double joint4,double joint5){
		this.txtStageY.setText(String.valueOf(joint1));
		this.txtStageZ.setText(String.valueOf(joint2));
		this.txtStageX.setText(String.valueOf(joint3));
		this.txtINeedle.setText(String.valueOf(joint4));
		this.txtONeedle.setText(String.valueOf(joint5));
	}

	public void updateSlides(double joint1,double joint2,double joint3,double joint4,double joint5,double joint6){

		if(sldStageX.getValue() != (int) Math.round(100-joint1/24*100))
			this.sldStageX.setValue((int) Math.round(100-joint1/24*100));

		if(sldStageY.getValue() != (int) Math.round(joint2/43.7*100))
			this.sldStageY.setValue((int) Math.round(joint2/43.7*100));
		
		if(sldStageZ.getValue() != (int) Math.round(-joint3/17*100))
			this.sldStageZ.setValue((int) Math.round(-joint3/17*100));
		
		if(sldONeedle.getValue() != (int) Math.round(joint4/20*100))
			this.sldONeedle.setValue((int) Math.round(joint4/20*100));
		
		if(sldINeedle.getValue() != (int) Math.round(joint5/63.7*100))
			this.sldINeedle.setValue((int) Math.round(joint5/63.7*100));
		
		if(sldNeedleRot.getValue() != (int)Math.round(joint6/360*100))
			this.sldNeedleRot.setValue((int)Math.round(joint6/360*100));
	}
}


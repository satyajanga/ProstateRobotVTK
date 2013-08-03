/**
 * 
 */
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author RDUxps
 *
 */
public class GenerateMouseListener implements MouseListener{

	RobotArmGUI gui;
		
	public GenerateMouseListener(RobotArmGUI gui) {
		this.gui=gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		String[] str=new String[6];		
		double[] joint=new double[6];
									
		System.out.println("Generate button clicked!");
		//for(int i=0;i<50;i++){
		//	RobotArmGUI.window.dispSTL.translateStageZ(-0.1);
			//RobotArmGUI.window.dispSTL.rotateArm(0.1);
		//}		
		
		//RobotArmGUI.window.dispSTL.genArmAnimation(-20.12, 0.1, -30.13, 0.1);
		//RobotArmGUI.window.dispSTL.genStageAnimation(3, 0.1, 5, 0.1,-10, 0.1);
		//RobotArmGUI.window.dispSTL.genRobotAnimation(-10.12, 0.1, -10.13, 0.1, 3, 0.1, 5, 0.1,-5, 0.1);
		
		if(gui.errFlag==false){		
			str[0]=gui.txtStageX.getText().trim();
			str[1]=gui.txtStageY.getText().trim();
			str[2]=gui.txtStageZ.getText().trim();
			str[3]=gui.txtONeedle.getText().trim();
			str[4]=gui.txtINeedle.getText().trim();
			str[5]=gui.txtNeedleRot.getText().trim();
			
			
			joint[0]=Double.valueOf(str[0]).doubleValue();					
			joint[1]=Double.valueOf(str[1]).doubleValue();				
			joint[2]=Double.valueOf(str[2]).doubleValue();					
			joint[3]=Double.valueOf(str[3]).doubleValue();			
			joint[4]=Double.valueOf(str[4]).doubleValue();
			joint[5]=Double.valueOf(str[5]).doubleValue();
			
			//System.out.println("Joint[0]"+joint[0]);
			//System.out.println("Joint[1]"+joint[1]);
			//System.out.println("Joint[2]"+joint[2]);
			
			joint[0]=24-joint[0];	
			joint[2]=-joint[2];			
			//joint[4]=-joint[4];		
						
			RobotArmGUI.window.isGenerating=true;
			RobotArmGUI.window.dispSTL.genRobotAnimation(joint[0],0.1,joint[1],0.1,joint[2],0.1,joint[3],0.1,joint[4],0.1,joint[5],1);
			RobotArmGUI.window.updateSlides(joint[0],joint[1],joint[2],joint[3],joint[4],joint[5]);
			RobotArmGUI.window.isGenerating=false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}



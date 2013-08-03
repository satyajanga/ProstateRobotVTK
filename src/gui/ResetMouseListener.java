package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vtk.DisplaySTL;



public class ResetMouseListener implements MouseListener{

	RobotArmGUI gui;
	
	public ResetMouseListener(RobotArmGUI gui) {
		this.gui=gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Reset button clicked!");
					
		//Reset Robot position
		//RobotArmGUI.window.dispSTL.rotateArmToAngle(0);
		//RobotArmGUI.window.dispSTL.rotateBaseToAngle(0);
		RobotArmGUI.window.dispSTL.translateStageToPosX(0+DisplaySTL.STAGE_MAX_TRANS_X);
		RobotArmGUI.window.dispSTL.translateStageToPosY(0);
		RobotArmGUI.window.dispSTL.translateStageToPosZ(0);
		RobotArmGUI.window.dispSTL.translateOuterneedleToPos(0);
		RobotArmGUI.window.dispSTL.translateInnerneedleToPos(0);
		RobotArmGUI.window.dispSTL.rotateNeedleToAngle(0);
		

		//Reset sliders
		RobotArmGUI.window.sldStageX.setValue(0);
		RobotArmGUI.window.sldStageY.setValue(0);
		RobotArmGUI.window.sldStageZ.setValue(0);
		RobotArmGUI.window.sldINeedle.setValue(0);
		RobotArmGUI.window.sldONeedle.setValue(0);
		RobotArmGUI.window.sldNeedleRot.setValue(0);
		
		//RobotArmGUI.window.dispSTL.armRotateAngle=0;
		//RobotArmGUI.window.dispSTL.baseRotateAngle=0;
		RobotArmGUI.window.dispSTL.stageTranslationX=24.0;
		RobotArmGUI.window.dispSTL.stageTranslationY=54.1;
		RobotArmGUI.window.dispSTL.stageTranslationZ=0.0;
		RobotArmGUI.window.dispSTL.outerneedleTranslation=0.0;
		RobotArmGUI.window.dispSTL.innerneedleTranslation=0.0;
		RobotArmGUI.window.dispSTL.needleRotateAngle=0.0;
		//Reset view point
		//gui.dispSTL.renWin.resetStatus();
		//gui.sldVertical.setValue(10);
		//gui.sldHorizontal.setValue(50);
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

package gui;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {

	RobotArmGUI gui;
	JSlider source ;

	public SliderChangeListener(RobotArmGUI gui){
		this.gui=gui;
	}

	public void stateChanged(ChangeEvent e) {
		source = (JSlider)e.getSource(); 
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if(gui.isGenerating==false){
					if(source == RobotArmGUI.window.sldMRI){
						RobotArmGUI.window.dispSTL.MRI.GetProperty().SetOpacity(0.1+(source.getValue()-50)/500.0);
						RobotArmGUI.window.dispSTL.patient.GetProperty().SetOpacity(0.1+(source.getValue()-50)/160.0);
						RobotArmGUI.window.dispSTL.renWin.Render();
					}
					else if(source == RobotArmGUI.window.sldStageX){
						RobotArmGUI.window.dispSTL.translateStageToPosX(24-source.getValue()*24/100);
						RobotArmGUI.window.updateLabels();
					}
					else if(source == RobotArmGUI.window.sldStageY){
						RobotArmGUI.window.dispSTL.translateStageToPosY(source.getValue()*437/100);
						RobotArmGUI.window.updateLabels();
					}
					else if(source == RobotArmGUI.window.sldStageZ){
						RobotArmGUI.window.dispSTL.translateStageToPosZ(-source.getValue()*17/100);
						RobotArmGUI.window.updateLabels();
					}
					else if(source == RobotArmGUI.window.sldONeedle){
						//RobotArmGUI.window.dispSTL.rotateBaseToAngle((source.getValue()-50)*25/50);
						RobotArmGUI.window.dispSTL.translateOuterneedleToPos(source.getValue()*20/100);
						RobotArmGUI.window.updateLabels();
					}
					else if(source == RobotArmGUI.window.sldINeedle){        	
						RobotArmGUI.window.dispSTL.translateInnerneedleToPos(source.getValue()*637/100);
						RobotArmGUI.window.updateLabels();
					}
					else if(source == RobotArmGUI.window.sldNeedleRot){
						RobotArmGUI.window.dispSTL.rotateNeedleToAngle(source.getValue()*360/100);
						RobotArmGUI.window.updateLabels();
					}
				}
			}    
		};
		SwingUtilities.invokeLater(runnable);
	}
}
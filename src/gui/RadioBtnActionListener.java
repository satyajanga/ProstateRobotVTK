package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioBtnActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton source=(JRadioButton)e.getSource();
		
		if(source.isSelected()){
			
			this.changeRadioBtnGroupState(source);
			
			if(source==RobotArmGUI.window.rdbtnOriginal){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==1){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(1/3.0);
					RobotArmGUI.window.dispSTL.isZoom=0;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(-45-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(0-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=0;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=-45;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(0);
			}
			else if(source==RobotArmGUI.window.rdbtnRight){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==0){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(3.0);
					RobotArmGUI.window.dispSTL.isZoom=1;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(90-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(0-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=0;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=90;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(0);
			}
			else if(source==RobotArmGUI.window.rdbtnFront){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==0){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(3.0);
					RobotArmGUI.window.dispSTL.isZoom=1;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(0-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(0-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=0;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=0;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(0);
			}
			else if(source==RobotArmGUI.window.rdbtnBack){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==0){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(3.0);
					RobotArmGUI.window.dispSTL.isZoom=1;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(180-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(0-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=0;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=180;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(0);
			}
			else if(source==RobotArmGUI.window.rdbtnTop){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==0){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(3.0);
					RobotArmGUI.window.dispSTL.isZoom=1;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(0-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(90-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=90;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=0;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(89);
			}
			else if(source==RobotArmGUI.window.rdbtnLeft){
				RobotArmGUI.window.dispSTL.renWin.resetStatus();
				if(RobotArmGUI.window.dispSTL.isZoom==0){
					RobotArmGUI.window.dispSTL.renWin.GetRenderer().GetActiveCamera().Zoom(3.0);
					RobotArmGUI.window.dispSTL.isZoom=1;
				}
				RobotArmGUI.window.dispSTL.setHorizontalView(-90-RobotArmGUI.window.dispSTL.currentHorizontalDegree);
				RobotArmGUI.window.dispSTL.setVerticalView(0-RobotArmGUI.window.dispSTL.currentVerticalDegree);
				RobotArmGUI.window.dispSTL.currentVerticalDegree=0;
				RobotArmGUI.window.dispSTL.currentHorizontalDegree=-90;
				RobotArmGUI.window.dispSTL.renWin.setElevationBound(0);
			}	
			//RobotArmGUI.window.debugCount++;
			//System.out.println("Pressed button is selected!"+RobotArmGUI.window.debugCount);
		}else{
			source.setSelected(true);
		}
	}

	private void changeRadioBtnGroupState(JRadioButton source){
		
		RobotArmGUI.window.rdbtnOriginal.setSelected(false);
		RobotArmGUI.window.rdbtnRight.setSelected(false);
		RobotArmGUI.window.rdbtnFront.setSelected(false);
		RobotArmGUI.window.rdbtnBack.setSelected(false);
		RobotArmGUI.window.rdbtnTop.setSelected(false);
		RobotArmGUI.window.rdbtnLeft.setSelected(false);
		
		source.setSelected(true);		
	}
}

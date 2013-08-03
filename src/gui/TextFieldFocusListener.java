package gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vtk.DisplaySTL;



public class TextFieldFocusListener implements FocusListener {

	private double getValueFromStr(String str){
		double result=0.0;
		long num=0;
		
		num=Math.round((Double.valueOf(str).doubleValue()*10));
		
		result=num/10.0;
		
		return result;
	}	
	
	/**
	 * Check whether the given string is valid to be converted to a number
	 * @param str
	 * @return
	 */
	private boolean isValidNumber(String str){
		boolean result;
					
		/*Pattern p1=Pattern.compile( "([0-9]*)\\.([0-9]*)" );
		Matcher m1=p1.matcher(str);		
		  
		Pattern p2=Pattern.compile( "([0-9]*)" );
		Matcher m2=p2.matcher(str);	
		
		Pattern p3=Pattern.compile( "(\\-)([0-9]*)\\.([0-9]*)" );
		Matcher m3=p3.matcher(str);
		
		Pattern p4=Pattern.compile( "\\-([0-9]*)" );
		Matcher m4=p4.matcher(str);	
		
		Pattern p5=Pattern.compile( "\\+([0-9]*)\\.([0-9]*)" );
		Matcher m5=p5.matcher(str);		
		
		Pattern p6=Pattern.compile( "\\+([0-9]*)" );
		Matcher m6=p6.matcher(str);			
		
		if(m1.matches() || m2.matches() || m3.matches() || m4.matches() || m5.matches() || m6.matches())
			result=true;
		else
			result=false;*/
		
		Pattern p1=Pattern.compile("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
		Matcher m1=p1.matcher(str);		
		
		if(m1.matches())
			result=true;
		else
			result=false;
		
		return result; 		
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {		
		JTextField source = (JTextField)e.getSource(); 	
		String str;
    	double joint=0.0;
    	
		if(source.getText().equals("")  || this.isValidNumber(source.getText())==false){
			JOptionPane.showMessageDialog(null,
				    "Please input a valid number!",
				    "Input Error",
				    JOptionPane.ERROR_MESSAGE);
			source.setText("0.0");
			RobotArmGUI.window.errFlag=true;
		}
						
		//Check the source of the event and check the validity of the input

		if(source==RobotArmGUI.window.txtStageX){
				str=RobotArmGUI.window.txtStageX.getText().trim();
				joint=this.getValueFromStr(str);
				
				if(joint>DisplaySTL.STAGE_MAX_TRANS_X || joint<DisplaySTL.STAGE_MIN_TRANS_X){
					JOptionPane.showMessageDialog(null,
						    "The input is out of range for the Stage_X!\nRange: 0~24",
						    "Out of range!",
						    JOptionPane.WARNING_MESSAGE);
					RobotArmGUI.window.txtStageX.setText("0.0");
					RobotArmGUI.window.errFlag=true;
				}
				else{
					RobotArmGUI.window.txtStageX.setText(String.valueOf(joint));
					RobotArmGUI.window.errFlag=false;
				}				
			}
		else if(source==RobotArmGUI.window.txtStageY){
				str=RobotArmGUI.window.txtStageY.getText().trim();
				joint=this.getValueFromStr(str);

				if(joint>DisplaySTL.STAGE_MAX_TRANS_Y-DisplaySTL.STAGE_MIN_TRANS_Y || joint<0){
					JOptionPane.showMessageDialog(null,
						    "The input is out of range for the Stage_Y!\nRange: 0~43.6",
						    "Out of range!",
						    JOptionPane.WARNING_MESSAGE);
					RobotArmGUI.window.txtStageY.setText("0.0");
					RobotArmGUI.window.errFlag=true;
				}
				else{
					RobotArmGUI.window.txtStageY.setText(String.valueOf(joint));
					RobotArmGUI.window.errFlag=false;
				}
			}
		else if(source==RobotArmGUI.window.txtStageZ){
			str=RobotArmGUI.window.txtStageZ.getText().trim();
			joint=this.getValueFromStr(str);
			if(joint<-DisplaySTL.STAGE_MAX_TRANS_Z || joint>-DisplaySTL.STAGE_MIN_TRANS_Z){
				JOptionPane.showMessageDialog(null,
					    "The input is out of range for the Stage_Z!\nRange: 0~17",
					    "Out of range!",
					    JOptionPane.WARNING_MESSAGE);
				RobotArmGUI.window.txtStageZ.setText("0.0");
				RobotArmGUI.window.errFlag=true;
			}
			else{
				RobotArmGUI.window.txtStageZ.setText(String.valueOf(joint));
				RobotArmGUI.window.errFlag=false;
			}
		}
		else if(source==RobotArmGUI.window.txtONeedle){
			str=RobotArmGUI.window.txtONeedle.getText().trim();
			joint=this.getValueFromStr(str);

			if(joint>DisplaySTL.OUTER_MAX_TRANS || joint<DisplaySTL.OUTER_MIN_TRANS){
				JOptionPane.showMessageDialog(null,
					    "The input is out of range for the Outer_Needle!\nRange: 0~20",
					    "Out of range!",
					    JOptionPane.WARNING_MESSAGE);
				RobotArmGUI.window.txtONeedle.setText("0.0");
				RobotArmGUI.window.errFlag=true;
			}
			else{
				RobotArmGUI.window.txtONeedle.setText(String.valueOf(joint));
				RobotArmGUI.window.errFlag=false;
			}
		}
		else if(source==RobotArmGUI.window.txtINeedle){
				str=RobotArmGUI.window.txtINeedle.getText().trim();
				joint=this.getValueFromStr(str);

				if(joint>DisplaySTL.INNER_MAX_TRANS || joint<DisplaySTL.INNER_MIN_TRANS){
					JOptionPane.showMessageDialog(null,
						    "The input is out of range for the Inner_Needle!\nRange: 0~63.7",
						    "Out of range!",
						    JOptionPane.WARNING_MESSAGE);
					RobotArmGUI.window.txtINeedle.setText("0.0");
					RobotArmGUI.window.errFlag=true;
				}
				else{
					RobotArmGUI.window.txtINeedle.setText(String.valueOf(joint));
					RobotArmGUI.window.errFlag=false;
				}
			}
		else if(source==RobotArmGUI.window.txtNeedleRot){
				str=RobotArmGUI.window.txtNeedleRot.getText().trim();
				joint=this.getValueFromStr(str);
				RobotArmGUI.window.txtNeedleRot.setText(String.valueOf(joint));
				RobotArmGUI.window.errFlag=false;
			}		
	}

}

package vtk;

import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MyVTKPanel extends vtkPanel{
	private int [] azimuthValue = new int [100];
	private int [] elevationValue = new int [100];
	private int currentElecationValue = 0;
	private double [] zoomValue = new double [100];
	private int elevationMaxBound=0;
	private int elevationMinBound=-10;//will change to 0
	private double [] camPosition0 = new double [100];
	private double [] camPosition1 = new double [100];
	private double [] camPosition2 = new double [100];
	private double [] camFocal0 = new double [100];
	private double [] camFocal1 = new double [100];
	private double [] camFocal2 = new double [100];
	private int index = 0;
	private int mode=0;
	
	public MyVTKPanel(){
		super();
		for (int i=0;i<100;i++)
		{
			azimuthValue[i]=0;
			elevationValue[i]=0;
			zoomValue[i]=1.0;
			camPosition0[i]=0;
			camPosition1[i]=0;
			camPosition2[i]=0;
			camFocal0[i]=0;
			camFocal1[i]=0;
			camFocal2[i]=0;
		}
	}
	
	public void mouseDragged(MouseEvent e){
	    if (ren.VisibleActorCount() == 0) return;
	    int x = e.getX();
	    int y = e.getY();
	   
	 // rotate
	    if (this.InteractionMode == 1){
	    	if(mode!=1){
	    		//System.out.println("elevationMaxBound = " + elevationMaxBound+"elevationMinBound = " + elevationMinBound);
	    		index++;
	    		mode=1;
				azimuthValue[index]=0;
				elevationValue[index]=0;
				zoomValue[index]=1.0;
				camPosition0[index]=0;
				camPosition1[index]=0;
				camPosition2[index]=0;
				camFocal0[index]=0;
				camFocal1[index]=0;
				camFocal2[index]=0;
	    	}
	    	if(elevationValue[index] + y - lastY>=elevationMinBound && elevationValue[index] + y - lastY<=elevationMaxBound){
             if(elevationValue[index] == elevationMinBound){
            	 if(y - lastY>0){
        	         cam.Elevation(y - lastY);
        	         elevationValue[index] = elevationValue[index] + (y - lastY);
            	 }
             }else if (elevationValue[index] == elevationMaxBound){
            	 if(y - lastY<0){
        	         cam.Elevation(y - lastY);
        	         elevationValue[index] = elevationValue[index] + (y - lastY);
            	 }
             }else if(elevationValue[index] + y - lastY>=elevationMinBound && elevationValue[index] + y - lastY<=elevationMaxBound){
            	 cam.Elevation(y - lastY);  
            	 elevationValue[index] = elevationValue[index] + (y - lastY);
             }
	       }else if(elevationValue[index] + y - lastY>elevationMaxBound){
	    	   cam.Elevation(elevationMaxBound-elevationValue[index]);
	    	   elevationValue[index]=elevationMaxBound;   
	       }else if(elevationValue[index] + y - lastY<elevationMinBound){
	    	   cam.Elevation(elevationMinBound-elevationValue[index]);
	    	   elevationValue[index]=elevationMinBound;
	       }
	      cam.Azimuth(lastX - x);
	      azimuthValue[index] = azimuthValue[index] + (lastX - x);
	      resetCameraClippingRange();
	      if (this.LightFollowCamera == 1)
	          {
	            lgt.SetPosition(cam.GetPosition());
	            lgt.SetFocalPoint(cam.GetFocalPoint());
	          }
	       //System.out.println(azimuthValue[index]+"   --"+elevationValue[index]+"    ");
	      }

	    // translate
	    if (this.InteractionMode == 2){
	        double  FPoint[];
	        double  PPoint[];
	        double  APoint[] = new double[3];
	        double  RPoint[];
	        double focalDepth;
	        
	    	if(mode!=2){
	    		//System.out.println("mode chage to 2");
	    		index++;
	    		mode=2;
				azimuthValue[index]=0;
				elevationValue[index]=0;
				zoomValue[index]=1.0;
				camPosition0[index]=0;
				camPosition1[index]=0;
				camPosition2[index]=0;
				camFocal0[index]=0;
				camFocal1[index]=0;
				camFocal2[index]=0;
				currentElecationValue = elevationValue[index-1]+currentElecationValue;
				setElevationBound(currentElecationValue);
	    	}
	        
	        // get the current focal point and position
	        FPoint = cam.GetFocalPoint();
	        PPoint = cam.GetPosition();

	        // calculate the focal depth since we'll be using it a lot
	        ren.SetWorldPoint(FPoint[0],FPoint[1],FPoint[2],1.0);
	        ren.WorldToDisplay();
	        focalDepth = ren.GetDisplayPoint()[2];
	        
	        APoint[0] = rw.GetSize()[0]/2.0 + (x - lastX);
	        APoint[1] = rw.GetSize()[1]/2.0 - (y - lastY);
	        APoint[2] = focalDepth;
	        ren.SetDisplayPoint(APoint);
	        ren.DisplayToWorld();
	        RPoint = ren.GetWorldPoint();
	        if (RPoint[3] != 0.0){
	            RPoint[0] = RPoint[0]/RPoint[3];
	            RPoint[1] = RPoint[1]/RPoint[3];
	            RPoint[2] = RPoint[2]/RPoint[3];
	        }
	        
	        if(camPosition0[index] == 0){
	        	camPosition0[index] = (FPoint[0]-RPoint[0])/2.0 + FPoint[0];
	        	camPosition1[index] = (FPoint[1]-RPoint[1])/2.0 + FPoint[1];
	        	camPosition2[index] = (FPoint[2]-RPoint[2])/2.0 + FPoint[2];
	        	camFocal0[index] = (FPoint[0]-RPoint[0])/2.0 + PPoint[0];
	        	camFocal1[index] = (FPoint[1]-RPoint[1])/2.0 + PPoint[1];
	        	camFocal2[index] = (FPoint[2]-RPoint[2])/2.0 + PPoint[2];
	        }
	        
	        /*
	         * Compute a translation vector, moving everything 1/2 
	         * the distance to the cursor. (Arbitrary scale factor)
	        */
	        
	        cam.SetFocalPoint((FPoint[0]-RPoint[0])/2.0 + FPoint[0],
	                          (FPoint[1]-RPoint[1])/2.0 + FPoint[1],
	                          (FPoint[2]-RPoint[2])/2.0 + FPoint[2]);
	        cam.SetPosition((FPoint[0]-RPoint[0])/2.0 + PPoint[0],
	                        (FPoint[1]-RPoint[1])/2.0 + PPoint[1],
	                        (FPoint[2]-RPoint[2])/2.0 + PPoint[2]);
	        resetCameraClippingRange();
	        //System.out.println(azimuthValue[index]+"   --"+elevationValue[index]+"    ");
	    }
	    // zoom
	    if (this.InteractionMode == 3){
	        double zoomFactor;
		//  double clippingRange[];
	        
	    	if(mode!=3){
	    		//System.out.println("mode chage to 3");
	    		index++;
	    		mode=3;
				azimuthValue[index]=0;
				elevationValue[index]=0;
				zoomValue[index]=1.0;
				camPosition0[index]=0;
				camPosition1[index]=0;
				camPosition2[index]=0;
				camFocal0[index]=0;
				camFocal1[index]=0;
				camFocal2[index]=0;
				currentElecationValue = elevationValue[index-1]+currentElecationValue;
				setElevationBound(currentElecationValue);
	    	}
	        
	        zoomFactor = Math.pow(1.02,(y - lastY));
	        if (cam.GetParallelProjection() == 1){
	            cam.SetParallelScale(cam.GetParallelScale()/zoomFactor);
	        }
	        else{
	            cam.Dolly(zoomFactor);
	            resetCameraClippingRange();
	        }
	        zoomValue[index] = zoomValue[index] * zoomFactor;
	    }
	    lastX = x;
	    lastY = y;
	    this.Render();
	}
	  
	public void setElevationBound(int elevationBound){
		this.elevationMaxBound = 89-elevationBound;
		this.elevationMinBound = -elevationBound;
	}

	public void resetStatus() {
		//System.out.println(index + "   ");
		for (int i = index; i > 0; i--) {
			if (azimuthValue[i] != 0) {
				cam.Azimuth(-azimuthValue[i]);
				cam.Elevation(-elevationValue[i]);
				resetCameraClippingRange();
				azimuthValue[i] = 0;
				elevationValue[i] = 0;
				//System.out.println(" mode1  ");
			}
			if (zoomValue[i] != 1) {
				cam.Dolly(1 / zoomValue[i]);
				zoomValue[i] = 1;
				resetCameraClippingRange();
				//System.out.println(" mode2  ");
			}
			if (camPosition0[i] != 0) {
				cam.SetFocalPoint(camPosition0[i], camPosition1[i],
						camPosition2[i]);
				cam.SetPosition(camFocal0[i], camFocal1[i], camFocal2[i]);
				resetCameraClippingRange();
				camPosition0[i] = 0;
				camPosition1[i] = 0;
				camPosition2[i] = 0;
				camFocal0[i] = 0;
				camFocal1[i] = 0;
				camFocal2[i] = 0;
				//System.out.println(" mode3  ");
			}
		}
		index = 0;
		mode = 0;
		currentElecationValue=0;
		if (this.LightFollowCamera == 1) {
			lgt.SetPosition(cam.GetPosition());
			lgt.SetFocalPoint(cam.GetFocalPoint());
		}
		this.Render();
	}
}

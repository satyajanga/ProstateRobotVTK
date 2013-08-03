package vtk;

import java.io.IOException;

import gui.RobotArmGUI;
import javax.swing.JPanel;

import com.neuronrobotics.loader.CopySTL;
import com.neuronrobotics.loader.NRNativeLoader;
import com.neuronrobotics.loader.NativeResourceException;

import vtk.vtkActor;
import vtk.vtkAssembly;
import vtk.vtkAxesActor;
import vtk.vtkPolyDataMapper;
import vtk.vtkSTLReader;
import vtk.vtkTextProperty;

public class DisplaySTL extends JPanel{

	private static final long serialVersionUID = 1L;
	RobotArmGUI gui;
	
	/**
	 * Load VTK Library
	 */
	static {
		/*System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
        System.loadLibrary("vtkChartsJava");
        System.loadLibrary("vtkGenericFilteringJava");
        System.loadLibrary("vtkGeovisJava");
        System.loadLibrary("vtkViewsJava");
        System.loadLibrary("vtkWidgetsJava");
        System.loadLibrary("vtkVolumeRenderingJava");
        System.loadLibrary("vtkInfovisJava");
        System.loadLibrary("vtkHybridJava");*/

		try {
			if(NRNativeLoader.OSUtil.isWindows()){
				NRNativeLoader.loadFromJar("msvcr110");
				NRNativeLoader.loadFromJar("msvcp110");
			}
			NRNativeLoader.loadFromJar("vtksys");
			NRNativeLoader.loadFromJar("vtkCommon");
			NRNativeLoader.loadFromJar("vtkCommonJava");
			NRNativeLoader.loadFromJar("vtkFiltering");
			NRNativeLoader.loadFromJar("vtkFilteringJava");
			NRNativeLoader.loadFromJar("vtkDICOMParser");
			NRNativeLoader.loadFromJar("vtkNetCDF");
			NRNativeLoader.loadFromJar("vtkNetCDF_cxx");
			NRNativeLoader.loadFromJar("vtkzlib");
			NRNativeLoader.loadFromJar("vtkmetaio");
			NRNativeLoader.loadFromJar("vtkjpeg");
			NRNativeLoader.loadFromJar("vtktiff");
			NRNativeLoader.loadFromJar("vtkexpat");
			NRNativeLoader.loadFromJar("vtkpng");
			NRNativeLoader.loadFromJar("vtkIO");
			NRNativeLoader.loadFromJar("vtkIOJava");
			NRNativeLoader.loadFromJar("vtkImaging");
			NRNativeLoader.loadFromJar("vtkImagingJava");
			NRNativeLoader.loadFromJar("vtkverdict");
			NRNativeLoader.loadFromJar("vtkGraphics");
			NRNativeLoader.loadFromJar("vtkGraphicsJava");
			NRNativeLoader.loadFromJar("vtkfreetype");
			NRNativeLoader.loadFromJar("vtkftgl");
			NRNativeLoader.loadFromJar("vtkRendering");
			NRNativeLoader.loadFromJar("mpistubs");
			NRNativeLoader.loadFromJar("MapReduceMPI");
			NRNativeLoader.loadFromJar("vtkhdf5");
			NRNativeLoader.loadFromJar("vtklibxml2");
			NRNativeLoader.loadFromJar("vtkalglib");
			NRNativeLoader.loadFromJar("vtkInfovis");
			NRNativeLoader.loadFromJar("vtkexoIIc");
			NRNativeLoader.loadFromJar("vtkHybrid");
			NRNativeLoader.loadFromJar("vtkWidgets");
			NRNativeLoader.loadFromJar("vtkViews");
			NRNativeLoader.loadFromJar("vtkRenderingJava");
			NRNativeLoader.loadFromJar("vtkViewsJava");
			NRNativeLoader.loadFromJar("vtkCharts");
			NRNativeLoader.loadFromJar("vtkChartsJava");
			NRNativeLoader.loadFromJar("vtkGenericFiltering");
			NRNativeLoader.loadFromJar("vtkGenericFilteringJava");
			NRNativeLoader.loadFromJar("vtkInfovisJava");
			NRNativeLoader.loadFromJar("vtkproj4");
			NRNativeLoader.loadFromJar("vtkGeovis");
			NRNativeLoader.loadFromJar("vtkWidgetsJava");
			NRNativeLoader.loadFromJar("vtkGeovisJava");
			NRNativeLoader.loadFromJar("vtkHybridJava");
			NRNativeLoader.loadFromJar("vtkVolumeRendering");
			NRNativeLoader.loadFromJar("vtkVolumeRenderingJava");
			NRNativeLoader.addDir();
			System.out.println(System.getProperty("java.library.path"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NativeResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**
	 * Default construction function
	 */
	public DisplaySTL(RobotArmGUI gui){
		this.gui=gui;
		this.renWin.setElevationBound(0);
		this.initActor();
		this.armAssembly();
		this.initVisualization();
		
		this.add(renWin);
	}
		
	//Create vtk objects
	private String vtkFolderPath;
	private vtkSTLReader reader;
	private vtkPolyDataMapper mapper = new vtkPolyDataMapper();
	public MyVTKPanel renWin = new MyVTKPanel();
	
	//private vtkActor headactor = new vtkActor();
	private vtkActor outerneedle = new vtkActor();
	private vtkActor outer_needle_holder = new vtkActor();
	private vtkActor inner_needle = new vtkActor();
	private vtkActor AssemConnecter = new vtkActor();
	private vtkActor assem1_stage = new vtkActor();
	private vtkActor assem2_stage = new vtkActor();
	private vtkActor assem3_stage = new vtkActor();
	private vtkActor assem4_stage = new vtkActor();
	private vtkActor assem5_stage = new vtkActor();
	private vtkActor assem6_stage = new vtkActor();
	private vtkActor assem7_stage = new vtkActor();
	private vtkActor z_frame = new vtkActor();
	private vtkActor Baseforall = new vtkActor();
	public vtkActor MRI = new vtkActor();
	public vtkActor patient = new vtkActor();
	
	public int currentHorizontalDegree = -45;
	public int currentVerticalDegree=0;
	public int isZoom = 0;

	private vtkAxesActor tip_axes = new vtkAxesActor(); 	
	private vtkAxesActor world_axes = new vtkAxesActor(); 
	
	private vtkAssembly assemblyHead = new vtkAssembly();
	private vtkAssembly assemblyIntermediate = new vtkAssembly();
	private vtkAssembly assemblyArmBase = new vtkAssembly();	
	private vtkAssembly assemblyArm = new vtkAssembly();
	private vtkAssembly assemblyYpart = new vtkAssembly();
	private vtkAssembly assemblyXpart = new vtkAssembly();
	private vtkAssembly assemblyZpart = new vtkAssembly();
	private vtkAssembly assemblyAll = new vtkAssembly();
	private vtkAssembly assemblyBase = new vtkAssembly();
	
	//Record the status of the joints
	public double innerneedleTranslation = 0.0;
	public double outerneedleTranslation=0.0;
	public double needleRotateAngle=0.0;
	public double stageTranslationX=0.0;
	public double stageTranslationY=54.1;
	public double stageTranslationZ=0.0;
	
	public static double INNER_MAX_TRANS = 63.7+0.01;
	public static double INNER_MIN_TRANS = 0.0-0.01;
	public static double OUTER_MAX_TRANS = 20+0.01;
	public static double OUTER_MIN_TRANS = 0.0-0.01;
	public static double STAGE_MAX_TRANS_X=24+0.01;
	public static double STAGE_MIN_TRANS_X=0-0.01;
	public static double STAGE_MAX_TRANS_Y=97.8+0.01;
	public static double STAGE_MIN_TRANS_Y=54.1-0.001;
	public static double STAGE_MAX_TRANS_Z=0+0.01;
	public static double STAGE_MIN_TRANS_Z=-17-0.01;	
	
	
	/**
	 * Initialize the original position and orientation of all actors
	 */
	private void initActor(){
		try {
			CopySTL.copyFromJar("outer_needle.STL");
			CopySTL.copyFromJar("outer_needle_holder.STL");
			CopySTL.copyFromJar("inner_needle.STL");
			CopySTL.copyFromJar("AssemConnecter.STL");
			CopySTL.copyFromJar("Assem1_stage_v2.STL");
			CopySTL.copyFromJar("Assem2_stage_v2.STL");
			CopySTL.copyFromJar("Assem3_stage_v2.STL");
			CopySTL.copyFromJar("Assem4_stage_v2.STL");
			CopySTL.copyFromJar("Assem5_stage_v2.STL");
			CopySTL.copyFromJar("Assem6_stage_v2.STL");
			CopySTL.copyFromJar("Assem7_stage_v2.STL");
			CopySTL.copyFromJar("z_frame.STL");
			CopySTL.copyFromJar("Baseforall.STL");
			CopySTL.copyFromJar("MRI.STL");
			CopySTL.copyFromJar("laying_in_mri1.STL");
			vtkFolderPath = CopySTL.getTempDir();
			System.out.println(vtkFolderPath);
		} catch (NativeResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/outer_needle.STL");
		//String path = getClass().getResource("/stl/outer_needle.STL").getPath();
		//path = path.substring(6, path.length());
		//System.out.println(path);
		//reader.SetFileName(path);
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		outerneedle.SetMapper(mapper);
		outerneedle.GetProperty().SetOpacity(0.3);
		//outerneedle.SetOrigin(0,0,37.55);
		outerneedle.SetPosition(-0.75, -0.75, 0);

		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/outer_needle_holder.STL");
		//reader.SetFileName(getClass().getResource(""));
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		outer_needle_holder.SetMapper(mapper);
		outer_needle_holder.SetPosition(0,-33.5,119.3);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/inner_needle.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		inner_needle.SetMapper(mapper);
		//inner_needle.SetOrigin(0.4,7.12,0);
		inner_needle.SetPosition(-7.31,-19.4,50.6);
		inner_needle.GetProperty().SetColor(0.5,0.25,0.25);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/AssemConnecter.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		AssemConnecter.SetMapper(mapper);
		//AssemConnecter.SetOrigin(7.55,0,0);
		AssemConnecter.SetPosition(-28,-61.75,139.2);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem1_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem1_stage.SetMapper(mapper);
		assem1_stage.SetOrigin(0,54.6+7.11,0);
		assem1_stage.SetPosition(0,-123.6,154.7+0.2);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem2_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem2_stage.SetMapper(mapper);
		assem2_stage.SetOrigin(0,7.11,7.11);
		assem2_stage.SetPosition(0,-74.95-6.4+12.35,31-6.65+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem3_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem3_stage.SetMapper(mapper);
		assem3_stage.SetOrigin(0,0,0);
		assem3_stage.SetPosition(0,-128.2+12.35,-30.5+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem4_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem4_stage.SetMapper(mapper);
		//assem4_stage.SetOrigin(-139.64,129.05+6.9,30.5);
		assem4_stage.SetPosition(0-5,-118.2+12.35,47.2+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem5_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem5_stage.SetMapper(mapper);
		//assem5_stage.SetOrigin(-139.64,129.05+6.9,30.5);
		assem5_stage.SetPosition(0-5.3,-118.2+12.35,91.5+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem6_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem6_stage.SetMapper(mapper);
		//assem6_stage.SetOrigin(-139.64,129.05+6.9,30.5);
		assem6_stage.SetPosition(84.64-139.64,-153.35+12.35,23.55+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Assem7_stage_v2.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		assem7_stage.SetMapper(mapper);
		//assem7_stage.SetOrigin(-139.64,129.05+6.9,30.5);
		assem7_stage.SetPosition(89.69-139.64,-162.30+12.35,-67.45+185.4);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/z_frame.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		z_frame.SetMapper(mapper);
		z_frame.SetPosition(-31.44+13.2,-147.2,78.9);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/Baseforall.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		Baseforall.SetMapper(mapper);
		Baseforall.SetPosition(-255+14,-158.5,-93.2);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/MRI.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		MRI.SetMapper(mapper);
		MRI.SetOrigin(0,0,0);
		MRI.SetPosition(-873.76+27,-930.76,-765.09);
		MRI.GetProperty().SetOpacity(0.1);
		
		reader = new vtkSTLReader();
		reader.SetFileName(vtkFolderPath+"/laying_in_mri1.STL");
		reader.Update();		
		mapper = new vtkPolyDataMapper();
		mapper.SetInput(reader.GetOutput());		
		patient.SetMapper(mapper);
		patient.SetOrigin(0,0,0);
		patient.SetPosition(0,-50,-380);
		patient.GetProperty().SetColor(0.96,0.83,0.76);
		patient.GetProperty().SetOpacity(0.1);
		
		tip_axes.SetTotalLength(20, 20, 20);
		vtkTextProperty tipaxes_tpro = new vtkTextProperty();
		tipaxes_tpro.SetFontSize(1);
		tip_axes.GetXAxisCaptionActor2D().SetCaptionTextProperty(tipaxes_tpro);
		tip_axes.GetYAxisCaptionActor2D().SetCaptionTextProperty(tipaxes_tpro);
		tip_axes.GetZAxisCaptionActor2D().SetCaptionTextProperty(tipaxes_tpro);
		vtkTransform tipaxes_transform=new vtkTransform();
		//transform.Translate(13.2,-147.2,78.9);
		tipaxes_transform.RotateY(180);
		tip_axes.SetUserTransform(tipaxes_transform);
		
		world_axes.SetTotalLength(50, 50, 50);
		vtkTextProperty tpro = new vtkTextProperty();
		tpro.SetFontSize(1);
		world_axes.GetXAxisCaptionActor2D().SetCaptionTextProperty(tpro);
		world_axes.GetYAxisCaptionActor2D().SetCaptionTextProperty(tpro);
		world_axes.GetZAxisCaptionActor2D().SetCaptionTextProperty(tpro);
		vtkTransform transform=new vtkTransform();
		transform.Translate(13.2,-147.2,78.9);
		transform.RotateY(180);
		world_axes.SetUserTransform(transform);
	}
	
	/**
	 * Assembly different parts of the robot
	 */
	private void armAssembly(){
		
		assemblyHead.AddPart(outerneedle);
		assemblyHead.AddPart(tip_axes);
		//assemblyHead.SetPosition(0, 0, -157.45);
		//assemblyHead.SetOrigin(0, 0, 157.45);
        //assemblyHead.RotateX(90);
        //assemblyHead.RotateZ(-90);
		
		assemblyIntermediate.AddPart(assemblyHead);		
		assemblyIntermediate.AddPart(outer_needle_holder);
		
		assemblyArm.AddPart(assemblyIntermediate);
		assemblyArm.AddPart(inner_needle);		
		
		assemblyArmBase.AddPart(assemblyArm);
		assemblyArmBase.AddPart(AssemConnecter);
		
		assemblyYpart.AddPart(assemblyArmBase);
		assemblyYpart.AddPart(assem1_stage);
		assemblyYpart.AddPart(assem2_stage);
		assemblyYpart.AddPart(assem3_stage);
		assemblyYpart.AddPart(assem4_stage);
		
		assemblyXpart.AddPart(assemblyYpart);
		assemblyXpart.AddPart(assem5_stage);
		
		assemblyZpart.AddPart(assemblyXpart);
		assemblyZpart.AddPart(assem6_stage);
		
		assemblyBase.AddPart(assemblyZpart);
		assemblyBase.AddPart(z_frame);
		assemblyBase.AddPart(assem7_stage);
		assemblyBase.AddPart(Baseforall);
		
		assemblyAll.AddPart(assemblyBase);
		assemblyAll.AddPart(MRI);
	}
	
	/**
	 * Visualization of the robot arm
	 */
	private void initVisualization(){			
		
		renWin.GetRenderer().SetBackground(0.1, 0.2, 0.4 );
		renWin.GetRenderer().AddActor(assemblyAll);		
		//renWin.GetRenderer().AddActor(assemblyMRI);
		renWin.GetRenderer().AddActor(patient);
		renWin.GetRenderer().AddActor(world_axes);
		
		renWin.GetRenderer().ResetCamera();
		renWin.GetRenderer().GetActiveCamera().Zoom(1.5);
		renWin.GetRenderer().GetActiveCamera().Azimuth(-45);



	}
	
	/**
	 * @param length for the outer needle's translation
	 */
	public void translateOuterneedle(double length){
		
		//length=-length; //Because the direction of x axis in VTK is different from that of the Robot
		if(outerneedleTranslation+length<=OUTER_MAX_TRANS && outerneedleTranslation+length>=OUTER_MIN_TRANS){
			assemblyArm.AddPosition(0, 0, -length);
			renWin.Render();
			
			outerneedleTranslation+=length;
		}
		else{
			System.out.println("The outer needle cannot move forward any more");
			System.out.println("The position of the outer needle:"+outerneedleTranslation);
		}
	}
	
	/**
	 * @param position Translate the outer needle to the desired position
	 */
	public void translateOuterneedleToPos(double position){
		long trans=0;		
		trans=Math.round (position-outerneedleTranslation);
		this.translateOuterneedle(trans);
	}
	
	/**
	 * @param length for the inner needle's translation
	 */
	public void translateInnerneedle(double length){
		
		//length=-length; //Because the direction of x axis in VTK is different from that of the Robot
		if(innerneedleTranslation+length<=INNER_MAX_TRANS && innerneedleTranslation+length>=INNER_MIN_TRANS){
			inner_needle.AddPosition(0, 0, -length);
			renWin.Render();
			
			innerneedleTranslation+=length;
		}
		else{
			System.out.println("The inner needle cannot move forward any more");
			System.out.println("The position of the inner needle:"+innerneedleTranslation);
		}
	}
	
	/**
	 * @param position Translate the inner needle to the desired position
	 */
	public void translateInnerneedleToPos(double position){
		double trans=0;	
		position=((long)position)/10.0;
		trans=position-innerneedleTranslation;
		this.translateInnerneedle(trans);
	}
	
	/**
	 * @param degree for the needle rotation
	 */
	public void rotateNeedle(double degree){
		assemblyHead.RotateZ(degree);
		renWin.Render();
		needleRotateAngle+=degree;
	}
	
	/**
	 * @param angle Rotate the needle to the desired angle
	 */
	public void rotateNeedleToAngle(double angle){
		long needleRotate=0;
		needleRotate=Math.round (angle-needleRotateAngle);
		this.rotateNeedle(needleRotate);		
	}
	
	/**
	 * @param length for which the stage is expected to translate along X axis
	 */
	public void translateStageX(double length){
		
		//length=-length; //Because the direction of x axis in VTK is different from that of the Robot
		if(stageTranslationX+length<=STAGE_MAX_TRANS_X && stageTranslationX+length>=STAGE_MIN_TRANS_X){
			assemblyXpart.AddPosition(length, 0, 0);
			renWin.Render();
		//	System.out.println("stage Translation X"+ length);
			stageTranslationX+=length;
		}
		else{
			System.out.println("The stage cannot move along axis X any more");
			System.out.println("The position of the stage along axis X:"+stageTranslationX);
		}
	}
	
	
	/**
	 * @param position Translate the stage to the desired position on x axis 
	 */
	public void translateStageToPosX(double position){
		long transX=0;
		
		transX=Math.round (position-stageTranslationX);
		//System.out.println("position-stageTranslationX: "+transX);
		this.translateStageX(transX);
	}
	
	/**
	 * @param length for which the stage is expected to translate along Y axis
	 */
	public void translateStageY(double length){
		if(stageTranslationY+length<=STAGE_MAX_TRANS_Y && stageTranslationY+length>=STAGE_MIN_TRANS_Y){
			
			assemblyArmBase.AddPosition(0, length, 0);
			assem1_stage.AddPosition(0, length, 0);
			assem2_stage.AddPosition(0, length, 0);
			
			double degree = (Math.acos(stageTranslationY/100)-Math.acos((stageTranslationY+length)/100))/Math.PI*180;
			double moving_length = Math.sqrt(100*100-(stageTranslationY)*(stageTranslationY)) - Math.sqrt(100*100-(stageTranslationY+length)*(stageTranslationY+length));
			
			assem1_stage.RotateX(degree);
		    assem2_stage.RotateX(degree);
		    assem3_stage.RotateX(-degree);
		    assem4_stage.AddPosition(0, 0,-moving_length);
			renWin.Render();
			
			stageTranslationY+=length;
			//temp=Math.round((stageTranslationY+length)*10);
			//stageTranslationY=temp/10.0;
		}
		else{
			System.out.println("The stage cannot move along axis Y any more");
			System.out.println("The position of the stage along axis Y:"+stageTranslationY);
		}
	}
	
	/**
	 * @param position Translate the stage to the desired position on Y axis 
	 */
	public void translateStageToPosY(double position){
		double transY=0;
		
		position=((long)position)/10.0+STAGE_MIN_TRANS_Y;
		
		transY=(position-stageTranslationY);
		this.translateStageY(transY);
	}
	
	/**
	 * @param length for which the stage is expected to translate along Z axis
	 */
	public void translateStageZ(double length){		
		
		if(stageTranslationZ+length<=STAGE_MAX_TRANS_Z && stageTranslationZ+length>=STAGE_MIN_TRANS_Z){
			assemblyZpart.AddPosition(0, 0, length);
			renWin.Render();
			
			stageTranslationZ+=length;
		}
		else{
			System.out.println("The stage cannot move along axis Z any more");
			System.out.println("The position of the stage along axis Z:"+stageTranslationZ);
		}
	}		
	
	/**
	 * @param position Translate the stage to the desired position on z axis 
	 */
	public void translateStageToPosZ(double position){
		long transZ=0;
		transZ=Math.round(position-stageTranslationZ);
		//System.out.println("position-stageTranslationZ: "+transZ);
		this.translateStageZ(transZ);
	}
	
	/**
	 * Generate Animation of the stage
	 * @param lengthX the length for which the stage is expected to translate along X axis
	 * @param lengthX_step the step length of the translation
	 * @param lengthY the length for which the stage is expected to translate along Y axis
	 * @param lengthY_step the step length of the translation
	 * @param lengthZ the length for which the stage is expected to translate along Z axis
	 * @param lengthZ_step the step length of the translation
	 */
	public void genStageAnimation(double lengthX,double lengthX_step,double lengthY,double lengthY_step,double lengthZ,double lengthZ_step){
		long transX=0;
		long transY=0;
		long transZ=0;
		long xCount=0;
		long yCount=0;		
		long zCount=0;		
		
		long max=0;
		long count=0;
		
		transX=Math.round(((lengthX-stageTranslationX)/lengthX_step));
		transY=Math.round(((lengthY+STAGE_MIN_TRANS_Y-stageTranslationY)/lengthY_step));
		transZ=Math.round(((lengthZ-stageTranslationZ)/lengthZ_step));
		xCount=Math.abs(transX);
		yCount=Math.abs(transY);
		zCount=Math.abs(transZ);
		System.out.println("Stage translation along x:"+transX);
		System.out.println("Stage translation along y:"+transY);
		System.out.println("Stage translation along z:"+transZ);
		
		if(transX<0)
			lengthX_step=-lengthX_step;
		if(transY<0)
			lengthY_step=-lengthY_step;
		if(transZ<0)
			lengthZ_step=-lengthZ_step;
		
		System.out.println("lengthZ_step:"+lengthZ_step);
		
		if(Math.abs(xCount)>=Math.abs(yCount))
			max=xCount;
		else
			max=yCount;
		if(max<Math.abs(zCount))
			max=zCount;
		
		count=max;
		System.out.println("Count Stage:"+count);
		
		for(int i=0;i<count;i++){
			if(xCount>0){
				this.translateStageX(lengthX_step);
				xCount--;
			}
			if(yCount>0){
				this.translateStageY(lengthY_step);
				yCount--;
			}
			if(zCount>0){
				this.translateStageZ(lengthZ_step);
				zCount--;
			}
			gui.updateLabels();
		}	
		
	}	
	
	/**
	 * Generate Animation of the whole robot
	 * @param arm_degree the degree that the arm is expected to rotate
	 * @param arm_step the step length of the rotation
	 * @param base_degree the degree that the base is expected to rotate
	 * @param base_step the step length of the rotation
	 * @param lengthX the length for which the stage is expected to translate along X axis
	 * @param lengthX_step the step length of the translation
	 * @param lengthY the length for which the stage is expected to translate along Y axis
	 * @param lengthY_step the step length of the translation
	 * @param lengthZ the length for which the stage is expected to translate along Z axis
	 * @param lengthZ_step the step length of the translation
	 */
	public void genRobotAnimation(double lengthX,double lengthX_step,double lengthY,double lengthY_step,double lengthZ,double lengthZ_step,double outerNeedle,double outerNeedle_step,double innerNeedle,double innerNeedle_step,double needleRot,double needleRot_step){
		long needleRotate=0;		
		long needleRotateCount=0;
		
		long transX=0;
		long transY=0;
		long transZ=0;
		long transOuter=0;
		long transInner=0;
		long xCount=0;
		long yCount=0;		
		long zCount=0;	
		long outCount=0;
		long inCount=0;
		
		long max=0;
		long count=0;
										
		//System.out.println("Length "+lengthX+"stageTrans "+stageTranslationX+" Minus "+(lengthX-stageTranslationX)+" Multiplication: "+(lengthX-stageTranslationX)/lengthX_step);
		transX=Math.round(((lengthX-stageTranslationX)/lengthX_step));
		transY=Math.round(((lengthY+STAGE_MIN_TRANS_Y-stageTranslationY)/lengthY_step));
		transZ=Math.round(((lengthZ-stageTranslationZ)/lengthZ_step));
		transOuter=Math.round((outerNeedle-outerneedleTranslation)/outerNeedle_step);
		transInner=Math.round((innerNeedle-innerneedleTranslation)/innerNeedle_step);
		xCount=Math.abs(transX);
		yCount=Math.abs(transY);
		zCount=Math.abs(transZ);
		outCount=Math.abs(transOuter);
		inCount=Math.abs(transInner);
		
		needleRotate=Math.round((needleRot/needleRot_step));;
		needleRotateCount=Math.abs(needleRotate);
				
		System.out.println("Stage translation along x:"+transX+"  "+stageTranslationX);
		System.out.println("Stage translation along y:"+transY+"  "+stageTranslationY);
		System.out.println("Stage translation along z:"+transZ+"  "+stageTranslationZ);
		//System.out.println("Arm rotation degree:"+armRotate+"  "+armRotateAngle);
		//System.out.println("Base rotation degree:"+baseRotate+"  "+baseRotateAngle);
							
		if(transX<0)
			lengthX_step=-lengthX_step;
		if(transY<0)
			lengthY_step=-lengthY_step;
		if(transZ<0)
			lengthZ_step=-lengthZ_step;
		if(transOuter<0)
			outerNeedle_step=-outerNeedle_step;
		if(innerNeedle<0)
			innerNeedle_step=-innerNeedle_step;	
		if(needleRotate<0)
			needleRot_step=-needleRot_step;
		
		//Get the greatest number among armCount, baseCount, xCount, yCount and zCount
		if(max<xCount)
			max=xCount;
		if(max<yCount)
			max=yCount;
		if(max<zCount)
			max=zCount;
		
		count=max+outCount+inCount+needleRotateCount;
		System.out.println("Total count:"+count);
		
		for(int i=0;i<max;i++){
			if(xCount>0){
				this.translateStageX(lengthX_step);
				xCount--;
			}
			if(yCount>0){
				this.translateStageY(lengthY_step);
				yCount--;
			}
			if(zCount>0){
				this.translateStageZ(lengthZ_step);
				zCount--;
			}			
			gui.updateLabels();
		}
		for(int i=0;i<needleRotateCount;i++){
			this.rotateNeedle(needleRot_step);
			gui.updateLabels();
		}
		for(int i=0;i<outCount;i++){
			this.translateOuterneedle(outerNeedle_step);
			gui.updateLabels();
		}
		for(int i=0;i<inCount;i++){
			this.translateInnerneedle(innerNeedle_step);
			gui.updateLabels();
		}
		
		gui.updateLabels();
		
		System.out.println("After generation: ");
		System.out.println("Stage translation along x:"+transX+"  "+stageTranslationX);
		System.out.println("Stage translation along y:"+transY+"  "+stageTranslationY);
		System.out.println("Stage translation along z:"+transZ+"  "+stageTranslationZ);
		//System.out.println("Arm rotation degree:"+armRotate+"  "+armRotateAngle);
		//System.out.println("Base rotation degree:"+baseRotate+"  "+baseRotateAngle);
		
	}
	
	public void setHorizontalView(double degree){
		renWin.GetRenderer().GetActiveCamera().Azimuth(degree);
		//renWin.GetRenderer().GetActiveCamera().OrthogonalizeViewUp();
        renWin.resetCameraClippingRange();
        if (renWin.LightFollowCamera == 1)
          {
            renWin.lgt.SetPosition(renWin.cam.GetPosition());
            renWin.lgt.SetFocalPoint(renWin.cam.GetFocalPoint());
          }
        renWin.Render();
	}
	
	public void setVerticalView(double degree){
		renWin.GetRenderer().GetActiveCamera().Elevation(degree);
		//renWin.GetRenderer().GetActiveCamera().OrthogonalizeViewUp();
        renWin.resetCameraClippingRange();
        if (renWin.LightFollowCamera == 1)
          {
            renWin.lgt.SetPosition(renWin.cam.GetPosition());
            renWin.lgt.SetFocalPoint(renWin.cam.GetFocalPoint());
          }
        renWin.Render();
	}
}

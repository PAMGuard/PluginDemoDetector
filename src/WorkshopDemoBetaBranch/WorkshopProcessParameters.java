package WorkshopDemoBetaBranch;

import java.awt.Color;
import java.io.Serializable;

import PamView.PamSymbol;
import PamView.PamSymbolType;

/**
 * for each module, it's best to keep all parameters controlling that module
 * in a single class which must be serialisable so that the Pamguard settings 
 * manager can save it between runs. 
 * @author Doug
 * @see PamController.PamSettingManager
 *
 */
public class WorkshopProcessParameters implements Serializable, Cloneable {

	static final long serialVersionUID = 1;
	
	/**
	 * use the first (0th) fft datablock in the model.
	 */
	int fftDataBlock = 0;  
	
	/**
	 * Low frequency for energy summation
	 */
	int lowFreq = 1000;

	/**
	 * High frequency for energy summation
	 */
	int highFreq = 10000;
	
	/**
	 * time constant for background noise measurement. 
	 */
	double backgroundTimeConstant = 10; 
	
	/**
	 * Detection threshold in dB. 
	 */
	double threshold = 6;
	
	/**
	 * Bitmap of channels to be used - use all available. 
	 */
	int channelList = 0xFFFF;
	

	@Override
	/**
	 * overriding the clone function enables you to clone (copy) 
	 * these parameters easily in your code without having to 
	 * continually cast to (WorkshopProcessParameters) or handle
	 * the exception CloneNotSupportedException.
	 */
	protected WorkshopProcessParameters clone() {
		try {
			return (WorkshopProcessParameters) super.clone();
		}
		catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}

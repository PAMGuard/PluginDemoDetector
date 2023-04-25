/*
 *  PAMGUARD - Passive Acoustic Monitoring GUARDianship.
 * To assist in the Detection Classification and Localisation
 * of marine mammals (cetaceans).
 *
 * Copyright (C) 2006
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */


package workshopdemodetector;

import PamModel.PamDependency;
import PamModel.PamPluginInterface;
import fftManager.FFTDataUnit;

/**
 * This is the class that makes this module available as a PAMGuard plugin which can 
 * be added to an existing PAMGuard configuration. <p> 
 * When developing, it's easiest to copy PamMode.MamModel and add the detector as for
 * other modules, linking to a complete PAMGuard project code. This makes debugging easy across
 * both the plugin and across PAMGuard. 
 * <p>
 * Once developed though, export a jar file without including PamModel in the export and put
 * the jar in your Program Files/Pamguard/plugins folder and it should work with any PAMGuard installation. 
 * @author dg50
 *
 */
public class WorkshopPlugin implements PamPluginInterface {
	
	String jarFile;

	@Override
	public String getClassName() {
		return "WorkshopDemoBetaBranch.WorkshopController";
	}

	@Override
	public String getDefaultName() {
		return "Workshop Demo Detector";
	}

	@Override
	public String getDescription() {
		return "Workshop Demo Detector";
	}

	@Override
	public String getMenuGroup() {
		return "Detectors";
	}

	@Override
	public String getToolTip() {
		return "Simple demo detector for programmers";
	}

	@Override
	public PamDependency getDependency() {
		return new PamDependency(FFTDataUnit.class, "fftManager.PamFFTControl");
	}

	@Override
	public int getMinNumber() {
		return 0;
	}

	@Override
	public int getMaxNumber() {
		return 1;
	}

	@Override
	public int getNInstances() {
		return 1;
	}

	@Override
	public boolean isItHidden() {
		return false;
	}

	@Override
	public String getHelpSetName() {
		return null;
	}

	@Override
	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}

	@Override
	public String getJarFile() {
		return jarFile;
	}

	@Override
	public String getDeveloperName() {
		return "Doug Gillespie";
	}

	@Override
	public String getContactEmail() {
		return "support@pamguard.org";
	}

	@Override
	public String getVersion() {
		return "2.0.0";
	}

	@Override
	public String getPamVerDevelopedOn() {
		return "Developed for the 2007 PAMGuard Workshop";
	}

	@Override
	public String getPamVerTestedOn() {
		return "2.02.07";
	}

	@Override
	public String getAboutText() {
		String desc = "Simple detector designed to demonstrate main Pamguard developer " +
				"environment, using as many Pamguard features as possible, but in " +
				"a really simple way." + System.lineSeparator() +
				"The detector is a very simple in-band energy detector. It will " +
				"subscribe to a block of FFT (spectrogram) data and measure " +
				"the background noise in a given frequency band over some time period " + 
				"and compare the signal to that background measure. If the SNR is >" +
				"threhsold a detection starts, if it's below threshold it stops again. ";
		return desc;
	}

	/* (non-Javadoc)
	 * @see PamModel.PamPluginInterface#allowedModes()
	 */
	@Override
	public int allowedModes() {
		return PamPluginInterface.ALLMODES;
	}

}

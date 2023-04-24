package WorkshopDemoBetaBranch;

import generalDatabase.PamDetectionLogging;
import generalDatabase.PamTableDefinition;
import generalDatabase.PamTableItem;
import generalDatabase.SQLTypes;

import java.sql.Types;

import PamguardMVC.PamDataBlock;
import PamguardMVC.PamDataUnit;

public class WorkshopSQLLogging extends PamDetectionLogging {

	WorkshopController workshopController;
	
	PamTableDefinition tableDefinition;
	
//	PamTableItem dateItem, durationItem, lowFreqItem, highFreqItem, energyItem, channelItem;
	PamTableItem energyItem;
	
	public WorkshopSQLLogging(WorkshopController workshopController, PamDataBlock pamDataBlock) {
		// call the super constructor. 
		super(pamDataBlock, UPDATE_POLICY_WRITENEW);
		
		// hold a reference to the Controller. 
		this.workshopController = workshopController;
		
		// create the table definition. 
		tableDefinition = (PamTableDefinition) getTableDefinition();
		PamTableItem tableItem;

		// add additional table items not included in PamDetectionLogging 
		tableDefinition.addTableItem(energyItem = new PamTableItem("energyDB", Types.DOUBLE));
		tableDefinition.setUseCheatIndexing(true);
	}


	@Override
	/*
	 * This gets called back from the database manager whenever a new dataunit is
	 * added to the datablock. All we have to do is set the data values for each 
	 * field and they will be inserted into the database. 
	 * If formats are incorrect, the SQL write statement is likely to fail !
	 */
	public void setTableData(SQLTypes sqlTypes, PamDataUnit pamDataUnit) {
		super.setTableData(sqlTypes, pamDataUnit);

		WorkshopDataUnit wdu = (WorkshopDataUnit) pamDataUnit;
		energyItem.setValue(wdu.getAmplitudeDB());
	}


	@Override
	protected PamDataUnit createDataUnit(SQLTypes sqlTypes, long timeMilliseconds, int databaseIndex) {
		
		WorkshopDataUnit wdu = new WorkshopDataUnit(timeMilliseconds, 0, 0, 0);	// correct values for channel, start sample and duration set in call to fillDataUnit 
		fillDataUnit(sqlTypes, wdu);
		wdu.setCalculatedAmlitudeDB(sqlTypes.makeDouble(energyItem.getValue()));
		return wdu;
	}

}

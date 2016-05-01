/*
 * @author Tanapon Meesat
 * UnitFactory is singleton class for invoking to return the unit
 * that user wants
 */
public class UnitFactory {
	//singleton here
	private static UnitFactory UnitFactory = new UnitFactory();
	/* A private Constructor prevents any other 
	    * class from instantiating.
	    */
	private UnitFactory(){}
	/* Static 'instance' method */
	public static UnitFactory getInstance(){
		return UnitFactory;
	}
		
	public static UnitType[] getUnitTypes(){
		return UnitType.values();
	}
	/* method for using by type of Unit */
	protected static Unit[] getUnits(UnitType utype){
		if(utype.toString().equals("Length")){
			return Length.values();
		}else if(utype.toString().equals("Area")){
			return Area.values();
		}else if(utype.toString().equals("Weight")){
			return Weight.values();
		}else if(utype.toString().equals("Time")){
			return Time.values();
		}else return Length.values();
	
	}
}

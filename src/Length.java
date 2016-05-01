/* @author Tanapon Meesat
 *  Length (enum) that implements from interface Unit
 *  that contains all of unit about the Length and
 *  the value of any unit 
 */
public enum Length implements Unit{
	METER("Meter",1.0),
	KILOMETER("Kilometer",0.001),
	CENTIMETER("Centimeter",100),
	NANOMETER("Nanometer",1000000000),
	MILE("Mile",0.000621),
	FEET("Feet",3.2809),
	INCH("Inch",39.37),
	YARD("Yard",1.09);
	public final String name;
	public final double value;
	/*
	 * contructor of Length
	 */
	private Length(String name , double value){
		this.name = name;
		this.value = value;
	}
	@Override
	public double getValue(){
		return value;
	}
	@Override
	public String toString(){
		return name;
	}
}
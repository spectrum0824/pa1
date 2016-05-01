/* @author Tanapon Meesat
 *  Area (enum) that implements from interface Unit
 *  that contains all of unit about the Area and
 *  the value of any unit 
 */
public enum Area implements Unit{
	METER("Meter^2",1.0),
	KILOMETER("Kilometer^2",0.000001),
	YARD("Yard^2",1.195990),
	CENTIMETTER("Centimeter^2",10000),
	FEET("Feet^2",10.7639),
	INCH("Inch^2",1550.0031);
	public final String name;
	public final double value;
	/*
	 * contructor of Area
	 */
	private Area(String name , double value){
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
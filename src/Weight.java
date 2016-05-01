/*
 * @author Tanapon Meesat
 *  Weight (enum) that implements from interface Unit
 *  that contains all of unit about the weight and
 *  the value of any unit 
 */
public enum Weight implements Unit{
	KILOGRAM("Kilogram",1.0),
	GRAM("Gram",1000),
	TONNE("Tonne",0.001),
	MILLIGRAM("Milligram",1000000),
	POUND("Pound",2.2046),
	OUNCE("Ounce",35.2739);
	public final String name;
	public final double value;
	/*
	 * contructor of Weight
	 */
	private Weight(String name , double value){
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
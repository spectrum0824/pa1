/* @author Tanapon Meesat
 *  Time (enum) that implements from interface Unit
 *  that contains all of unit about the Time and
 *  the value of any unit 
 */
public enum Time implements Unit{
	SECOND("Second",1.0),
	MINUTE("Minute",0.0166667),
	HOUR("Hour",0.0002777778);
	public final String name;
	public final double value;
	/*
	 * contructor of Time
	 */
	private Time(String name , double value){
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
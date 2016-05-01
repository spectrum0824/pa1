/*
 * @author Tanapon Meesat
 * for converting value by input from GUI that will have only 2 decimals point by
 * converting from string to double and returned out
 */
public class UnitConverter {
	public double convert(double amount , Unit fromUnit , Unit toUnit){
		return Double.parseDouble(String.format("%.2f", (amount/fromUnit.getValue())*toUnit.getValue()));
	}

}
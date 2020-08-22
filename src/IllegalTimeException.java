/**
 * This Exception class handles exceptions when the time is entered in invalid
 * format.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
public class IllegalTimeException extends Exception{
	public IllegalTimeException() {
		super("\nTrain not added: Invalid arrival time.\n");
	}
}

/**
 * This Exception class handles exceptions when the Track cursor is pointing
 * to an null object.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
public class NoSelectedTrainException extends Exception{
	public NoSelectedTrainException() {
		super("\nNo Selected Train: No train on the track\n");
	}
}

/**
 * This Exception class handles exceptions when an already existed train or 
 * track is added to the list.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
public class AlreadyExistsException extends Exception {
	public AlreadyExistsException() {
		super(" Not Added: Already Exists!");
	}
	public AlreadyExistsException(String x ) {
		super(x + " Not Added: Already Exists!\n");
	}
}

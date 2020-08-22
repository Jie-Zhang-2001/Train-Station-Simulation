/**
 * This Exception class handles exceptions when the new train's arrival time
 * conflicts with an already existed train in the list.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
public class TrainScheduledException extends Exception {
	public TrainScheduledException() {
		super("\nTrain not added: There is a Train already scheduled on Track"
		  + "  at that time!\n");
	}
	public TrainScheduledException(int num) {
		super("\nTrain not added: There is a Train already scheduled on Track "
		  + num + " at that time!\n");
	}
}

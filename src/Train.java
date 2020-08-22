/**
 * The Train class creates a Train object that stores the train number,
 * destination, arrival time, and transfer time.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 *
 */
public class Train {
	private int trainNumber;
	private String trainDestination;
	private int arrivalTime;
	private int transferTime;
	private Train prevTrain;
	private Train nextTrain;

/**
 * Returns an instance of Train.
 */
	public Train() {

	}

/**
 * Returns the value of trainNumber variable.
 * @return
 * 		the value of trainNumber variable.
 */
	public int getTrainNumber() {
		return trainNumber;
	}

/**
 * Returns the value of arrivalTime variable.
 * @return
 * 		the value of arrivalTime variable.
 */
	public int getArrivalTime() {
		return arrivalTime;
	}

/**
 * Returns the value of transferTime variable.
 * @return
 * 		the value of transferTime variable.
 */
	public int getTransferTime() {
		return transferTime;
	}

/**
 * Returns the value of trainDestination variable.
 * @return
 * 		the value of trainDestination variable.
 */
	public String getTrainDestination() {
		return trainDestination;
	}

/**
 * Creates an instance of Train object with specified values.
 * @param trainNumber
 * 		the train number of the Train object to be created.
 * @param trainDestination
 * 		the train destination of the Train object to be created.
 * @param arrivalTime
 * 		the arrival time of the Train object to be created.
 * @param transferTime
 * 		the transfer time of the Train object to be created.
 */
	public Train(int trainNumber, String trainDestination, int arrivalTime, 
	  int transferTime) {
		this.trainNumber = trainNumber;
		this.trainDestination = trainDestination;
		this.arrivalTime = arrivalTime;
		this.transferTime = transferTime;
		prevTrain = null;
		nextTrain = null;
	}

/**
 * Sets the reference of nextTrain variable to nextTrain.
 * @param nextTrain
 * 		the Train object to be set as the next train of this instance.
 */
	public void setNext(Train nextTrain) {
		this.nextTrain = nextTrain;
	}

/**
 * Sets the reference of prevTrain variable to prevTrain.
 * @param prevTrain
 * 		the Train object to be set as the previous train of this instance.
 */
	public void setPrev(Train prevTrain) {
		this.prevTrain = prevTrain;
	}

/**
 * Returns a reference of the nextTrain variable
 * @return
 * 		the reference of the nextTrain variable
 */		
	public Train getNext() {
		return nextTrain;
	}

/**
 * Returns a reference of the prevTrain variable
 * @return
 * 		the reference of the prevTrain variable
 */
	public Train getPrev() {
		return prevTrain;
	}

/**
 * Compares the value of trainNumber variables of two train objects.
 * @return
 * 		a boolean value: true if the objects are equal, false otherwise.
 */
	public boolean equals(Object o) {
		if (o instanceof Train) {
			Train p = (Train) o;
			if (p.trainNumber == this.trainNumber) {
				return true;
			}
		}
		return false;
	}

/**
 * Returns a String representation of this Train object with all the values
 * of the variables.
 * @returns
 * 		A String representation of this Train object.
 */
	public String toString() {
		String aTime, dTime;
		int min, hr;
		int tTime = this.getTransferTime() + this.getArrivalTime();
		aTime = this.getArrivalTime() + "";
		min = (this.getArrivalTime() % 100) + this.getTransferTime();
		hr = this.getArrivalTime() / 100;
		hr += (min / 60);
		tTime = (hr * 100) + (min % 60);
		dTime = tTime + "";
		while (aTime.length() < 4) {
			aTime = "0" + aTime;
		}
		while (dTime.length() < 4) {
			dTime = "0" + dTime;
		}
		String trainInfo = "\n     Train number: " + this.trainNumber + "\n" + 
		  "     Train Destination: " + this.trainDestination + "\n" + 
		  "     Arrival Time: " + aTime + "\n" + "     Departure Time: " + 
		  dTime + "\n";
		return trainInfo;
	}
}

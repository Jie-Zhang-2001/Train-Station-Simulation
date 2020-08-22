/**
 *The Track class creates a Track object that stores a list of Train objects
 *in a linked list.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
public class Track {
	private Train head;
	private Train tail;
	private Train cursor;
	private Track next;
	private Track prev;
	private double untilizationRate;
	private int trackNumber;
	private Train nodePtr;
	private String time;
	private int waitTime = 0, numberTrain = 0;

/**
 * Returns an instance of Track.
 */
	public Track() {

	}

/**
 * Returns an instance of Track with trackNumber.
 * @param trackNumber
 * 		the track number of the Track object
 */
	public Track(int trackNumber) {
		this.trackNumber = trackNumber;
	}

/**
 * Returns a reference of the next variable
 * @return
 * 		the reference of the next variable
 */	
	public Track getNext() {
		return next;
	}

/**
 * Returns a reference of the prev variable
 * @return
 * 		the reference of the prev variable
 */	
	public Track getPrev() {
		return prev;
	}

/**
 * Returns a reference of the cursor variable
 * @return
 * 		the reference of the cursor variable
 */	
	public Train getCursor() {
		return cursor;
	}

/**
 * Returns a value of the trackNumber variable
 * @return
 * 		the value of the trackNumber variable
 */	
	public int getTrackNumber() {
		return trackNumber;
	}

/**
 * Returns a reference of the head variable
 * @return
 * 		the reference of the head variable
 */	
	public Train getHead() {
		return head;
	}

/**
 * Returns a reference of the tail variable
 * @return
 * 		the reference of the tail variable
 */	
	public Train getTail() {
		return tail;
	}

/**
 * Sets the value of next variable to next.
 * @param next
 * 		the Track object to be set as the next track of this instance.
 */
	public void setNext(Track next) {
		this.next = next;
	}

/**
 * Sets the value of prev variable to prev.
 * @param prev
 * 		the Track object to be set as the previous track of this instance.
 */
	public void setPrev(Track prev) {
		this.prev = prev;
	}

/**
 * Returns a value of the numberTrain variable
 * @return
 * 		the value of the numberTrain variable
 */		
	public int getNumberTrain() {
		return numberTrain;
	}

/**
 * Adds a train object into the linked list according to its arrival time
 * @param newTrain
 * 		The train object to be added to this Track instance
 * @throws AlreadyExistsException
 * 		thrown if the train already exists in the list.
 * @throws IllegalTimeException
 * 		thrown if the time is entered in wrong format( > 24 hr or > 60 min)
 * @throws TrainScheduledException
 * 		thrown if the time of newTrain conflicts with an already existing 
 * 		train
 */
	public void addTrain(Train newTrain) throws AlreadyExistsException, 
	  IllegalTimeException, TrainScheduledException {
		if (!trainScheduled(newTrain)) {
			throw new TrainScheduledException(this.getTrackNumber());
		}
		time = newTrain.getArrivalTime() + "";
		while (time.length() < 4) {
			time = "0" + time;
		}
		if (time.length() > 4 || Integer.parseInt(time.substring(0, 2)) >= 24
		  || Integer.parseInt(time.substring(2)) >= 60) {
			throw new IllegalTimeException();
		}
		if (!exists(newTrain)) {
			numberTrain++;
			waitTime += newTrain.getTransferTime();
			if (head == null) {
				head = newTrain;
				cursor = newTrain;
				tail = newTrain;
			} else {
				if (head.getNext() == null) {
					if (head.getArrivalTime() > newTrain.getArrivalTime()) {
						head.setPrev(newTrain);
						newTrain.setNext(head);
						tail = head;
						head = newTrain;
						cursor = newTrain;
					} else {
						head.setNext(newTrain);
						newTrain.setPrev(head);
						tail = newTrain;
						cursor = newTrain;
					}
				} else {
					nodePtr = head;
					while (nodePtr != null) {
						if (nodePtr.getArrivalTime() > newTrain.getArrivalTime
						  ()) {
							if (nodePtr == head) {
								head.setPrev(newTrain);
								newTrain.setNext(head);
								head = newTrain;
								cursor = newTrain;
								break;
							} else {
								nodePtr.getPrev().setNext(newTrain);
								newTrain.setPrev(nodePtr.getPrev());
								nodePtr.setPrev(newTrain);
								newTrain.setNext(nodePtr);
								cursor = newTrain;
								if (cursor.getNext() == null) {
									tail = newTrain;
								}
								break;
							}
						}
						if (nodePtr == tail) {
							tail.setNext(newTrain);
							newTrain.setPrev(tail);
							tail = newTrain;
							cursor = newTrain;
							break;
						}
						nodePtr = nodePtr.getNext();
					}
				}
			}
		} else {
			throw new AlreadyExistsException("\nTrain " 
		      + newTrain.getTrainNumber());
		}
	}

/**
 * Checks to see whether the newTrain's time conflicts with an existing train
 * or not
 * @param newTrain
 * 		the Train object to be tested for conflict times
 * @return
 * 		a boolean value: true if newTrain's time doesn't conflict with an
 * 		existing train, false otherwise;
 */
	public boolean trainScheduled(Train newTrain) {
		nodePtr = head;
		while (nodePtr != null) {
			if ((newTrain.getArrivalTime() >= nodePtr.getArrivalTime()
			  && newTrain.getArrivalTime() <= nodePtr.getArrivalTime() +
			  nodePtr.getTransferTime())|| (newTrain.getArrivalTime() 
			  + newTrain.getTransferTime() >= nodePtr.getArrivalTime()
			  && newTrain.getArrivalTime() + newTrain.getTransferTime() <=
			  nodePtr.getArrivalTime()+ nodePtr.getTransferTime())) {
				return false;
			}
			nodePtr = nodePtr.getNext();
		}
		return true;
	}

/**
 * Returns the value of utilizationRate variable
 * @return
 * 		the value of utilizationRate variable
 */
	public double getUtilizationRate() {
		untilizationRate = waitTime / 1440.0 * 100;
		return (double) Math.round(untilizationRate * 100) / 100;

	}

/**
 * Prints out a string representation of the train object referenced by
 * cursor
 * @throws NoSelectedTrainException
 * 		thrown if the cursor is null
 */
	public void printSelectedTrain() throws NoSelectedTrainException{
		if(cursor == null) {
			throw new NoSelectedTrainException();
		}
		System.out.println(cursor.toString());
	}

	public boolean exists(Train newTrain) {
		nodePtr = head;
		while (nodePtr != null) {
			if (nodePtr.getTrainNumber() == newTrain.getTrainNumber()
			  || nodePtr.getArrivalTime() == newTrain.getArrivalTime()) {
				return true;
			}
			nodePtr = nodePtr.getNext();
		}
		return false;
	}

/**
 * Removes the Train object referenced by cursor
 * @return
 * 		the reference of the removed train object
 */		
	public Train removeSelectedTrain() {
		Train selectedTrain = cursor;
		if (cursor == null) {
			return null;
		} else if (cursor.getNext() == null && cursor.getPrev() == null) {
			head = null;
			cursor = null;
		} else if (cursor.getPrev() == null) {
			cursor.getNext().setPrev(null);
			cursor = cursor.getNext();
		} else if (cursor.getNext() == null) {
			cursor.getPrev().setNext(null);
			cursor = cursor.getPrev();
		} else {
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			cursor = cursor.getNext();
		}
		waitTime = waitTime - selectedTrain.getTransferTime();
		numberTrain--;
		return selectedTrain;
	}

/**
 * Moves the cursor to the next Train object in the linked list.
 * @return
 * 		a boolean value: true if cursor is moved to the next Train object,
 * 		false otherwise
 * @throws NoSelectedTrainException
 * 		thrown if there's no Train object on Track
 */
	public boolean selectNextTrain() throws NoSelectedTrainException { 
		if (cursor == null) {
			throw new NoSelectedTrainException();
		}
		if (cursor.getNext() == null) {
			return false;
		} else {
			cursor = cursor.getNext();
			return true;
		}
	}

/**
 * Moves the cursor to the previous Train object in the linked list.
 * @return
 * 		a boolean value: true if cursor is moved to the previous Train object,
 * 		false otherwise
 * @throws NoSelectedTrainException
 * 		thrown if there's no Train object on Track
 */
	public boolean selectPrevTrain() throws NoSelectedTrainException {
		if (cursor == null) {
			throw new NoSelectedTrainException();
		}
		if (cursor.getPrev() == null) {
			return false;
		} else {
			cursor = cursor.getPrev();
			return true;
		}
	}

/**
 * Returns a string representation of the Track object: an ordered list of
 * Train objects in a neatly formatted table
 */
	public String toString() {
		nodePtr = head;
		String x = "";
		String aTime;
		String dTime;
		int tTime, min, hr;
		while (nodePtr != null) {
			min = (nodePtr.getArrivalTime() % 100) + nodePtr.getTransferTime();
			hr = nodePtr.getArrivalTime() / 100;
			hr += (min / 60);
			tTime = (hr * 100) + (min % 60);
			aTime = nodePtr.getArrivalTime() + "";
			dTime = tTime + "";
			while (aTime.length() < 4) {
				aTime = "0" + aTime;
			}
			while (dTime.length() < 4) {
				dTime = "0" + dTime;
			}
			if (nodePtr == cursor) {
				x += String.format("%-15s%-25s%-30s%-30s%-30s", "   *", 
				  nodePtr.getTrainNumber(),nodePtr.getTrainDestination(), 
				  aTime, dTime);
			} else {
				x += String.format("%-15s%-25s%-30s%-30s%-30s", "", 
				  nodePtr.getTrainNumber(),
				  nodePtr.getTrainDestination(), aTime, dTime);
			}
			x += "\n";
			nodePtr = nodePtr.getNext();
		}
		return x;
	}

}

/**
 * The Station class creates an object that stores a list of Track objects
 * in a linked list.
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 2 R09
 * 
 *
 */
import java.util.Scanner;
public class Station {
	private Track head;
	private Track tail;
	private Track cursor;
	private Track nodePtr;

/**
 * Creates an instance of Station object
 */
	public Station() {

	}

/**
 * Prints out the Track object referenced by cursor variable.
 */
	public void printSelectedTrack() {
		System.out.println(String.format("%-15s%-25s%-30s%-30s%-30s", 
		  "Selected", "Train Number", "Train Destination","Arrival Time", 
		  "Departure Time"));
		System.out.println(
		  "----------------------------------------------------------"
		  + "-------------------------------------------------------");
		System.out.println(cursor.toString());
	}

/**
 * Prints out all the Track objects in this Station
 */
	public void printAllTracks() {
		System.out.println(this.toString());
	}

	public boolean selectTrack(int trackToSelect) {
		nodePtr = head;
		while (nodePtr != null) {
			if (nodePtr.getTrackNumber() == trackToSelect) {
				cursor = nodePtr;
				return true;
			}
			nodePtr = nodePtr.getNext();
		}
		return false;
	}

/**
 * Checks to see if the Track object already exists in the Stations
 * @param newTrack
 * 		the Track object to be tested for existence in the Station.
 * @return
 * 		a boolean value: true if the track number already exists, false
 * 		otherwise.
 */
	public boolean exists(Track newTrack) {
		nodePtr = head;
		while (nodePtr != null) {
			if (nodePtr.getTrackNumber() == newTrack.getTrackNumber()) {
				return true;
			}
			nodePtr = nodePtr.getNext();
		}
		return false;
	}

/**
 * Prints out the Track information in the Station object: track number,
 * amount of trains on the track, and the utilization rate of each track.
 */
	public void SI() {
		nodePtr = head;
		while (nodePtr != null) {
			System.out.print("      Track " + nodePtr.getTrackNumber() + ": " 
		      + +nodePtr.getNumberTrain()+ " trains arriving (" + 
			  nodePtr.getUtilizationRate() + " Utilization Rate)\n");
			nodePtr = nodePtr.getNext();
		}
	}

/**
 * Returns a string representation of Station Object: an ordered list of Track
 * objects neatly formatted in a table.
 */
	public String toString() {
		nodePtr = head;
		String x = "";
		while (nodePtr != null) {
			if (nodePtr == cursor) {
				x += "Track " + nodePtr.getTrackNumber() + "*" + " (" +
			      nodePtr.getUtilizationRate() + "% Utilization Rate)\n";
			} else {
				x += "Track " + nodePtr.getTrackNumber() + "  (" + 
			      nodePtr.getUtilizationRate() + "% Utilization Rate)\n";
			}

			x += String.format("%-15s%-25s%-30s%-30s%-30s", "Selected", 
			  "Train Number", "Train Destination","Arrival Time", "Departure "
			  + "Time");
			x += "\n";
			x += "----------------------------------------------------------"
			  + "-------------------------------------------------------";
			x += "\n";
			x += nodePtr.toString();
			x += "\n";
			nodePtr = nodePtr.getNext();
		}
		return x;
	}

/**
 * Adds a Track object into Station linked list, ordered according to track
 * number
 * @param newTrack
 * 		the Track object to be added.
 * @throws AlreadyExistsException
 * 		thrown if the track number already exists in the list
 */
	public void addTrack(Track newTrack) throws AlreadyExistsException {
		if (!exists(newTrack)) {
			if (head == null) {
				head = newTrack;
				cursor = newTrack;
				tail = newTrack;
			} else {
				if (head.getNext() == null) {
					if (head.getTrackNumber() > newTrack.getTrackNumber()) {
						head.setPrev(newTrack);
						newTrack.setNext(head);
						tail = head;
						head = newTrack;
						cursor = newTrack;
					} else {
						head.setNext(newTrack);
						newTrack.setPrev(head);
						tail = newTrack;
						cursor = newTrack;
					}
				} else {
					nodePtr = head;
					while (nodePtr != null) {
						if (nodePtr.getTrackNumber() > newTrack.getTrackNumber
						  ()) {
							if (nodePtr == head) {
								head.setPrev(newTrack);
								newTrack.setNext(head);
								head = newTrack;
								cursor = newTrack;
								break;
							} else {
								nodePtr.getPrev().setNext(newTrack);
								newTrack.setPrev(nodePtr.getPrev());
								nodePtr.setPrev(newTrack);
								newTrack.setNext(nodePtr);
								cursor = newTrack;
								if (cursor.getNext() == null) {
									tail = newTrack;
								}
								break;
							}
						}
						if (nodePtr == tail) {
							tail.setNext(newTrack);
							newTrack.setPrev(tail);
							tail = newTrack;
							cursor = newTrack;
							break;
						}
						nodePtr = nodePtr.getNext();
					}
				}
			}
		} else {
			throw new AlreadyExistsException("\nTrack " + newTrack.
			  getTrackNumber());
		}
	}

/**
 * Removes the Track object referenced by cursor
 * @return
 * 		the reference of the removed track object
 */	
	public Track removeSelectedTrack() {
		Track selectedTrack = cursor;
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
		return selectedTrack;
	}

/**
 * Prints out the menu for user to pick
 */
	public static void printMenu() {
		System.out.println("-----------------------------------------------"
		  + "--------------------------------------");
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", 
		  "Train Options", "|", "Track Options", "|"));
		System.out.println(
		  String.format("%-2s%-40s%-2s%-40s%-2s", "|", "     A. Add new Train"
		  , "|", "     TA. Add Track", "|"));
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", "    "
		  + " N. Select next Train", "|","     TR. Remove selected Track", "|"
		  ));
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", "     "
		  + "V. Select previous Train", "|","     TS. Switch Track", "|"));
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", "    "
		  + " R. Remove selected Train", "|","     TPS. Print selected Track", 
		  "|"));
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", "     "
		  + "P. Print selected Train", "|",
		  "     TPA. Print all Track", "|"));
		System.out.println("------------------------------------------------"
		  + "-------------------------------------");
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", 
		  "Station Options", "", "", "|"));
		System.out.println(
		  String.format("%-2s%-40s%-2s%-40s%-2s", "|", "     SI. Print Station"
		  + " Information", "", "", "|"));
		System.out.println(String.format("%-2s%-40s%-2s%-40s%-2s", "|", "     "
		  + "Q. Quit", "", "", "|"));
		System.out.println("-------------------------------------------------"
		  + "------------------------------------");
		System.out.println();
	}

/**	
 * Works as a test to test the Station, Track, Train classes and the methods
 * within them.
 * @param args
 */
	public static void main(String[] args) {
		String choice, destination;
		int trainNumber = 0, arrivalTime, transferTime, trackNumber = 0;
		Scanner stdin = new Scanner(System.in);
		boolean condition = true;
		Station s = new Station();
		Track tr = new Track();
		int numberOfTrack = 0;
		do {
			try {
				printMenu();
				System.out.print("Choose an operation: ");
				choice = stdin.next();
				choice = choice.toUpperCase();
				switch (choice) {
				case "A":
					System.out.print("\nEnter train number: ");
					trainNumber = stdin.nextInt();
					stdin.nextLine();
					System.out.print("Enter train destination: ");
					destination = stdin.nextLine();
					System.out.print("Enter train arrival time: ");
					arrivalTime = stdin.nextInt();
					System.out.print("Enter train transfer time: ");
					transferTime = stdin.nextInt();
					Train t = new Train(trainNumber, destination, arrivalTime,
					  transferTime);
					if (s.cursor == null) {
						System.out.println("\nTrain not added: There is no "
						  + "Track to add the Train to!\n");
					} else {
						s.cursor.addTrain(t);
						System.out.println("\nTrain No. " + t.getTrainNumber()
						  + " to " + t.getTrainDestination()
						  + " added to Track " + s.cursor.getTrackNumber()
						  + "\n");
					}
					break;
				case "TA":
					System.out.print("\nEnter track number: ");
					trackNumber = stdin.nextInt();
					tr = new Track(trackNumber);
					s.addTrack(tr);
					numberOfTrack++;
					System.out.println("\nTrack " + trackNumber + " added to "
					  + "the Station.\n");
					break;
				case "N":
					if (s.cursor.selectNextTrain()) {
						System.out.println("\nCursor has been moved to next "
						  + "train.\n");
					} else {
						System.out.println("\nSelected train not updated: "
						  + "Already at end of Track list.\n");
					}
					break;
				case "V":
					if (s.cursor.selectPrevTrain()) {
						System.out.println("\nCursor has been moved to "
						  + "previous train.\n");
					} else {
						System.out.println("\nSelected train not updated: "
						  + "Already at head of Track list.\n");
					}
					break;
				case "R":
					Train removed = s.cursor.removeSelectedTrain();
					System.out.println("\nTrain No." + removed.getTrainNumber()
					  + " to " + removed.getTrainDestination() + " has been"
					  + " removed from Track " + s.cursor.getTrackNumber() + 
					  "\n");
					break;
				case "P":
					System.out.print("\nSelected Train:");
					s.cursor.printSelectedTrain();
					break;
				case "TPS":
					System.out.print("\nTrack " + s.cursor.getTrackNumber() 
					  + " (" + s.cursor.getUtilizationRate() + "% Utilization"
					  + " Rate)\n");
					s.printSelectedTrack();
					break;

				case "TPA":
					s.printAllTracks();
					break;
				case "SI":
					System.out.println("\nStation(" + numberOfTrack + " tracks)"
				      + ":");
					s.SI();
					System.out.println();
					break;
				case "TR":
					System.out.println("\nTrack No." + s.removeSelectedTrack()
					  .getTrackNumber() + " closed.\n");
					numberOfTrack--;
					break;
				case "TS":
					System.out.print("\nEnter the track number: ");
					trackNumber = stdin.nextInt();
					if (s.selectTrack(trackNumber)) {
						System.out.println("\nSwitched to Track " + 
					      trackNumber + "\n");
					} else {
						System.out.println("\nCould not switch to Track " 
					      + trackNumber + ": Track " + trackNumber
						  + " does not exist.\n");
					}
					break;
				case "Q":
					System.out.println("\nProgram terminating normally...");
					condition = false;
					break;
				default:
					System.out.println("\nEnter a valid choice!\n");
					break;
				}
			} catch (AlreadyExistsException ex) {
				System.out.println(ex.getMessage());
			} catch (IllegalTimeException ex1) {
				System.out.println(ex1.getMessage());
			} catch (TrainScheduledException ex3) {
				System.out.println(ex3.getMessage());
			} catch(NoSelectedTrainException ex4) {
				System.out.println(ex4.getMessage());
			} 
		} while (condition);
		stdin.close();

	}
}

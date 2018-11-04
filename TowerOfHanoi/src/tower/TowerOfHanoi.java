package tower;
import java.util.Stack;
class TowerOfHanoi {
	// The 3 stack objects will represent our poles
	private static final Stack<Integer> left = new Stack<>();
	private static final Stack<Integer> center = new Stack<>();
	private static final Stack<Integer> right = new Stack<>();
	// All relevant methods depend on the value of numDisks
	private static final int numDisks = 10;
	public static void main(String[] args) {
		// Put rings on the first stack
		fillLeftStack();
		// 1. Calculate the total number of moves required.
		// i.e. "pow(2, n) - 1" here n is number of disks.
		int movesRequired = (int) (Math.pow(2, numDisks) - 1);
		// 2. If number of disks is even, then interchange right and center pole (not
		// used in this project).
		// 3. for i = 1 to total number of moves:
		for (int i = 0; i <= movesRequired; ++i) {
			System.out.println("Turn: " + i);
			nextMove(i);
			printState();
		}
	}

	private static void fillLeftStack() {
		for (int i = numDisks; i > 0; i--) {
			left.push(i);
		}
	}

	private static String spacePaddedPole() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= numDisks; i++) {
			sb.append(' ');
		}
		return sb.toString() + '|' + sb.reverse();
	}

	private static String lineBreak() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 3 * 2 * (numDisks + 1) + 3; ++i) {
			sb.append('-');
		}
		return sb.toString();
	}

	private static String DiskToString(int size) {
		StringBuilder sb = new StringBuilder();

		for (int i = numDisks; i >= 0; i--) {
			if (i >= size) {
				sb.append(' ');
			} else {
				sb.append('=');
			}
		}
		return sb.toString() + '|' + sb.reverse();
	}

	private static void printState() {
		try {
			int index;
			for (int i = numDisks; i > 0; i--) {
				StringBuilder row = new StringBuilder();
				index = i - 1;
				if (left.size() < i) {
					row.append(spacePaddedPole());
				} else {
					row.append(DiskToString(left.get(index)));
				}
				if (center.size() < i) {
					row.append(spacePaddedPole());
				} else {
					row.append(DiskToString(center.get(index)));
				}
				if (right.size() < i) {
					row.append(spacePaddedPole());
				} else {
					row.append(DiskToString(right.get(index)));
				}
				System.out.println(row);
			}
		} catch (Exception e) {
			System.out.println("error, you probably tried to grab an empty stack");
			e.printStackTrace();
		}
		System.out.println(lineBreak());
	}

	private static void nextMove(int i) {
		// if i%3 == 1: legal movement of top disk between left and right stacks
		if (i % 3 == 1) {
			if (!left.empty() && (right.empty() || left.peek() < right.peek())) {
				right.push(left.pop());
			} else if (!right.empty() && (left.size() == 0 || right.peek() < left.peek())) {
				left.push(right.pop());
			}
		}
		// if i%3 == 2: legal movement top disk between left and center
		else if (i % 3 == 2) {
			if (!left.empty() && (center.size() == 0 || left.peek() < center.peek())) {
				center.push(left.pop());
			} else if (!center.empty() && (left.size() == 0 || center.peek() < left.peek())) {
				left.push(center.pop());
			}
		}
		// if i%3 == 0: legal movement top disk between center and right
		else if (i % 3 == 0) {
			if (!center.empty() && (right.size() == 0 || center.peek() < right.peek())) {
				right.push(center.pop());
			} else if (!right.empty() && (center.size() == 0 || right.peek() < center.peek())) {
				center.push(right.pop());
			}
		}
	}
}
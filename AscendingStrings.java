package acsl;

import java.util.ArrayList;
import java.util.Scanner;

public class AscendingStrings {
	public static final Scanner typed = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Please enter input:");
		String output = "";
		String input = typed.nextLine();
		ArrayList<String> array = stringArray(input);
		ArrayList<String> outputCount = new ArrayList<String>();
		String numberFirst = "";
		numberFirst = firstNumber(array);
		array = delFront(array, numberFirst);
		output = output + numberFirst;
		outputCount.add(numberFirst);
		if (array.size() > 0) {
			String numberSecond = secondNumber(array, numberFirst);
			array = delBack(array, numberSecond);
			numberSecond = delZero(numberSecond);
			output = output + " " + numberSecond;
			outputCount.add(numberSecond);
			while (array.size() > 0) {
				String nextNum = numFromFront(array, findPrevNum(outputCount));
				array = delFront(array, nextNum);
				nextNum = delZero(nextNum);
				boolean ifTerm2 = ifTerminate(findPrevNum(outputCount), nextNum);
				if (ifTerm2 == true) {
					output = delFinal(output);
					System.out.println(output);
					return;
				}
				output = output + " " + nextNum;
				outputCount.add(nextNum);
				if (array.size() > 0) {
					String newNum = numFromBack(array, findPrevNum(outputCount));
					array = delBack(array, newNum);
					newNum = delZero(newNum);
					boolean ifTerm = ifTerminate(findPrevNum(outputCount), newNum);
					if (ifTerm == true) {
						output = delFinal(output);
						System.out.println(output);
						return;
					}
					output = output + " " + newNum;
					outputCount.add(newNum);
				}
			}
		} else {
			output = delFinal(output);
			System.out.println(output);
			return;
		}
	}

	public static String findPrevNum(ArrayList<String> currentOutput) {
		String result = currentOutput.get(currentOutput.size() - 1);
		return result;
	}

	public static ArrayList<String> delFront(ArrayList<String> input, String count) {
		ArrayList<String> result = input;
		for (int i = 0; i < count.length(); i++) {
			for (int q = 0; q < result.size() - 1; q++) {
				result.set(q, result.get(q + 1));
			}
		}
		for (int i = 0; i < count.length(); i++) {
			result.remove(result.size() - 1);
		}
		result.trimToSize();
		return result;
	}

	public static ArrayList<String> delBack(ArrayList<String> input, String count) {
		ArrayList<String> result = input;

		for (int i = 0; i < count.length(); i++) {
			result.remove(result.size() - 1);
			result.trimToSize();
		}
		result.trimToSize();
		return result;
	}

	public static String delZero(String input) {
		char temp = input.charAt(0);
		String temp2 = Character.toString(temp);
		String result = "";
		if (temp2.equals("0")) {
			ArrayList<String> thing = stringArray(input);
			for (int p = 0; p < thing.size(); p++) {
				if (thing.get(p).equals("0")) {
					thing = delFront(thing, "1");
				}
			}
			for (int q = 0; q < thing.size(); q++) {
				result = result + thing.get(q);
			}
			return result;
		} else {
			return input;
		}
	}

	public static String delFinal(String input) {
		String result = "";
		ArrayList<String> array = stringArray(input);
		firstloop: for (int i = 1; i < input.length(); i++) {
			if (array.get(i - 1).equals("0") && i - 1 != 0) {
				i--;
			}
			String temp2 = array.get(i);
			if (temp2.equals(" ")) {
			}
			if (temp2.equals("0")) {
				for (int p = i; p < array.size() - 1; p++) {
					array.set(p, array.get(p + 1));
				}
				array.trimToSize();
				array.remove(array.size() - 1);
			}
			if (i == array.size() - 1) {
				break firstloop;
			}
		}
		if (array.get(0).equals("0")) {
			array = delFront(array, "0");
		}
		for (int p = 0; p < array.size(); p++) {
			result = result + array.get(p);
		}
		return result;
	}

	public static ArrayList<String> stringArray(String input) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			char temp = input.charAt(i);
			String thing = Character.toString(temp);
			result.add(thing);
		}
		return result;
	}

	public static String firstNumber(ArrayList<String> input) {
		String result = "";
		if (input.get(0).equals("0")) {
			for (int i = 0; i < input.size(); i++) {
				if (input.get(i).equals("0")) {
					result = result + input.get(i);
				} else {
					result = result + input.get(i);
					return result;
				}
			}
		} else {
			result = input.get(0);
		}
		return result;
	}

	public static String secondNumber(ArrayList<String> input, String firstNum) {
		String result = "";
		int oldNum = Integer.parseInt(firstNum);
		for (int i = 0; i < input.size(); i++) {
			result = result + input.get(input.size() - i - 1);
			int newNum = Integer.parseInt(result);
			if (newNum > oldNum) {
				break;
			}
		}
		return result;

	}

	public static String numFromFront(ArrayList<String> input, String prevNum) {
		String result = "";
		int oldNum = Integer.parseInt(prevNum);
		for (int i = 0; i < input.size(); i++) {
			result = result + input.get(i);
			int newNum = Integer.parseInt(result);
			if (newNum > oldNum) {
				break;
			}
		}
		return result;
	}

	public static String numFromBack(ArrayList<String> input, String prevNum) {
		String result = "";
		int oldNum = Integer.parseInt(prevNum);
		int count = 1;
		for (int i = 0; i < input.size(); i++) {
			result = result + input.get(input.size() - count);
			int newNum = Integer.parseInt(result);
			if (newNum > oldNum) {
				return result;
			}
			if (input.size() == count) {
				return result;
			}
			count++;
		}
		return result;
	}

	public static boolean ifTerminate(String old, String news) {
		boolean result = false;
		int num1 = Integer.parseInt(old);
		int num2 = Integer.parseInt(news);
		if (num1 >= num2) {
			result = true;
		}
		return result;
	}
}

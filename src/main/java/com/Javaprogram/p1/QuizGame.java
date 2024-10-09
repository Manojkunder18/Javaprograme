package com.Javaprogram.p1;

import java.util.Scanner;

public class QuizGame {
	private static boolean used;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	

	System.out.println("Enter your name:"); 
	String name = scanner.next();

	System.out.println("Enter your age:"); 
	String age = scanner.next();

	System.out.println("Enter your place:"); 
	String place = scanner.next();

	System.out.println("Enter your qualification:");
	String qualification = scanner.next();

	boolean fiftyfifty = false; 
	boolean audiencepoll = false; int score = 0;

	String[] questions= {

	"What is the size of the 'int' in Java?",
	
	"which of the following is an oop concept in java?",
	
	"Which Cricketer has highest Centuries in ODI formate?",

	"Which of the following is not an primitive data type?",
	
	"which IPL Team does VIRAT KOHLI plays for?",

	"Which keyword is used to import a package from java API library?",

	"Which key word is used to return value inside the method?",

	"Which statement is used to stop a loop?",
	
	"Who is the author of MAHABHARATHA",
	
	"What is the highest-grossing film of all time?"

	};

	String[][] options = {

			{"A) 8 Bits", "B) 16 Bits", "C) 32 Bits", "D) 64 Bits"},
			{"A) Compilation", "B) Data structure", "C) Inheritance", "D) Web development"},
			{"A) Rohit", "B) Virat", "C) Sachin", "D) smith"},
			{"A)int", "B) double", "C) char", "D) String"},
			{"A) MI", "B) RCB", "C) SRH", "D) GT"},
			{"A)package", "B) lib","C) getlib", "D) import"}, 
			{"A)void", "B) break", "C) return", "D) get"}, 
			{"A) break", "B) return", "C) exit", "D) stop"},
			{"A) Karna", "B) Arjuna", "C) Valmiki", "D) Vedha vyasa"},
			{"A) Avengers:Endgame", "B) Titanic", "C) Avatar", "D) Batman"}

	};

	char[] correctOption = {'C','C','B','D','B','D','C','A','D','A'};

	int[][] audiencePoll= {

	{20, 10, 60, 10},
	{10, 20, 50, 20},
	{10, 50, 20, 20},
	{10, 20, 10, 60},
	{30, 40, 10, 20},
	{30, 20, 10, 40},
	{30, 20, 40, 10},
	{50, 10, 30, 10},
	{10, 10, 30, 50},
	{60, 10, 20, 10}

	};

	System.out.println("Dear " + name + ", welcome to the game! Your questions are given below:");

	for (int i = 0; i < questions.length; i++) { 
	System.out.println(questions[i]); 
	System.out.println("Options:");
	System.out.println(options[i][0]);

	System.out.println(options[i][1]);
	System.out.println(options[i][2]); 
	System.out.println(options[i][3]);

	System.out.println("Are you willing to use a lifeline? (1. 50-50, 2. Audience Poll, 3. No lifeline)"); 
	int lifelineChoice = scanner.nextInt(); scanner.nextLine();
	// Consume newline

	if (lifelineChoice == 1){
		if (fiftyfifty) {
			System.out.println("You have already used the 50-50 lifeline. Please enter your answer."); } 
		else { 
			fiftyfifty	= true; 
			int correctOptionIndex = getOptionIndex(correctOption[i]); 
			int incorrectOptionIndex1 = (correctOptionIndex + 1) % 4; 
			int incorrectOptionIndex2 = (correctOptionIndex + 2) % 4;

	System.out.println("Eliminated options:  "+ options[i][incorrectOptionIndex1] + ", " + options[i][incorrectOptionIndex2]);

	}

	} else if (lifelineChoice == 2) {
		if(audiencepoll) {
			System.out.println("You have already used the audience poll lifeline. Please enter your answer.");

	} else{
		audiencepoll= true;
		System.out.println("Audience poll results:"); 
		for (int j=0;j< options[i].length; j++) {

	System.out.println(options[i][j] + ":" + audiencePoll[i][j] +"%");

	}

	}
}

	System.out.print("Enter your answer: ");

	String userAnswer = scanner.nextLine().toUpperCase();
	if (getOptionIndex(userAnswer.charAt(0))== getOptionIndex(correctOption[i])) { 
		score += 10;
		System.out.println("Correct answer! You earn 10 points."); 
		} else {

			System.out.println("Incorrect answer. The correct answer is " +options[i][getOptionIndex(correctOption[i])]);
			
           
                System.out.println("Exiting the quiz.");
                break;
		}
	}
	System.out.println("Your final score is " + score + " points.");
	}
	private static int getOptionIndex(char option) {

	switch (option) { case 'A': return 0; case 'B': return 1; case 'C': return 2; case 'D': return 3; default: return -1;
	}
	}
	private static String getOptionLetter(int optionIndex) {
		 switch (optionIndex) { 
		 case 0: return "A"; case 1: return "B"; case 2: return "C";case 3: return "D";

	default: return "";
	}
}
}
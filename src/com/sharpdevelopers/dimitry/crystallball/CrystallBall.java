package com.sharpdevelopers.dimitry.crystallball;

import java.util.Random;

public class CrystallBall {
	public String[] mAnswers = { "It is certain", "It is OK", "Don't go!",
			"I am OK", "Drink coffee", "Meet friends", "My reply is NO",
			"You will be relaxing", "No problem, maan", "Yo yo yo, WTF?",
			"Very nice!", "Good Morning, and have a nice day", "Cheers!",
			"Don't worry, be happy!", "The stars are with you today"

	};

	public String getAnAnswer() {

		String answer = "";

		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(mAnswers.length);

		answer = mAnswers[randomNumber];

		return answer;
	}
}

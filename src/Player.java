
public class Player extends Participant{

	public Player(int token) {
		super(token);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getScore() {
		int totalScore = 0;
		boolean isAceIncluded = false;
		for(Card card : cards) {
			int number = card.getNumber();
			if(number == 11 || number == 12 || number == 13) {
				totalScore += 10;
			} else {
				totalScore += number;
			}
			if(number == 1) isAceIncluded = true;
		}
		if(isAceIncluded) {
			if(totalScore + 10 <= 21) totalScore += 10; //Ace is counted as 11.
		}
		return totalScore;
	}

	@Override
	public boolean isBusted() {
		//for debug
		System.out.println("player score:" + getScore());
		return (getScore() > 21) ? true : false;
	}
}

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
	List<Card> cards;
	int token;
	
	public Participant(int token) {
		this.token = token;
	}
	
	public void initCards() {
		cards = new ArrayList<>();
	}
	
	public void drawCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void addToken(int token) {
		this.token += token;
	}
	
	public void removeToken(int token) {
		this.token -= token;
	}
	
	public int getToken() {
		return token;
	}

	public abstract int getScore();
	
	public abstract boolean isBusted();
}

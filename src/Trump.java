import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trump {
	private List<Card> deck;
	
	public Trump() {
		deck = new ArrayList<>();
		initTrump();
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card getCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}
	
	public void initTrump() {
		deck.clear();
		for(int i = 1; i <= 52; i++) deck.add(new Card(i));
	}
}

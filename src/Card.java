import java.util.ArrayList;
import java.util.List;

public class Card {
	public static String DIAMOND = "diamond";
	public static String HEART = "heart";
	public static String CLUB = "club";
	public static String SPADE = "spade";
	
	private int serialNum = 0;
	public Card(int serialNum) {
		this.serialNum = serialNum;
	}
	
	public String getType() {
		switch(serialNum % 4) {
		case 0:
			return DIAMOND;
		case 1:
			return HEART;
		case 2:
			return CLUB;
		case 3:
			return SPADE;
		default:
			throw new IllegalStateException();
		}
	}
	
	public int getNumber() {
		return (serialNum % 13 == 0) ? 13 : serialNum % 13;
	}

}

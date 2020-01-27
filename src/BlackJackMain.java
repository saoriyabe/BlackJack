import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class BlackJackMain {

	public static void main(String[] args) {
		Trump deck = new Trump();
		Player player = new Player(10);
		House house = new House(50);
		BlackJackMain blackJack = new BlackJackMain(player, house);
		
		Scanner scanner = new Scanner(System.in);		
		while(true) {
			System.out.println("---------Game Start!-----------");
			
			// return cards
			player.initCards();
			house.initCards();

			// show how many tokens player / house has
			System.out.println("Player tokens:" + player.getToken());
			System.out.println("House tokens:" + house.getToken());
			
			// shuffle trump cards
			deck.shuffle();
			
			// draw 2 cards
			for(int i = 0; i < 2; i++) {
				player.drawCard(deck.getCard());
				house.drawCard(deck.getCard());
			}
			
			// check players cards
			System.out.println("-----Player has these cards-----");			
			blackJack.printAllCards(player.getCards().iterator());
			
			//for debug
			System.out.println("-----[Debug] House has these cards-----");			
			blackJack.printAllCards(house.getCards().iterator());
			
			// bet tokens
			System.out.println("How many tokens do you wanna bet?");
			int betValue = 0;
			while(true) {
				try {
					betValue = scanner.nextInt();
				} catch(InputMismatchException e) {
					System.out.println("Please type NUMBER");
				}
				
				if(betValue == 0) {
					System.out.println("Please bet at least 1 token");
					continue;
				}
				
				if(betValue > player.getToken()) {
					System.out.println("Please bet <= " + player.getToken());
				} else break;
			}
			
			// stick or draw
			while(true) {
				// check player score
				if(!player.isBusted()) {
					System.out.println("Choose Stick or Draw.\n Stick:s\n Draw:d");
					String nextStep = scanner.next().toUpperCase();
					if(nextStep.equals("S")) {
						break;
					} else if(nextStep.equals("D")) {
						Card card = deck.getCard();
						blackJack.printCard(card);
						player.drawCard(card);
						break;
					} 
				}
			}
			
			// check house score
			if(!house.isBusted()) {
				Card card = deck.getCard();
				blackJack.printCard(card);
				house.drawCard(card);
			}
						
			// exchange tokens
			blackJack.exchangeTokens(betValue);
			
			// end or continue
			if(player.getToken() == 0) {
				System.out.println("[GAME OVER] Player lose");
				break;
			} else if (house.getToken() == 0) {
				System.out.println("[CLEAR] Player win");
				break;
			}
		}
		scanner.close();
	}
	
	private Player player;
	private House house;
	
	public BlackJackMain(Player player, House house) {
		this.player = player;
		this.house = house;
	}
	
	private boolean isPlayerWin() {
		if(player.isBusted()) {
			return false;
		} else if(house.isBusted()) {
			return true;
		}
		return (player.getScore() > house.getScore()) ? true : false;
	}
	
	private void printCard(Card card) {
		System.out.println("Card:" + card.getType() + " Number:" + card.getNumber());
	}
	
	private void printAllCards(Iterator<Card> iterator) {
		while(iterator.hasNext()) {
			printCard(iterator.next());
		}
	}
	
	private void exchangeTokens(int betValue) {
		if(isPlayerWin()) {
			System.out.println("Player win!");
			System.out.println("-----Player has these cards-----");
			printAllCards(player.getCards().iterator());
			System.out.println("-----House has these cards-----");
			printAllCards(house.getCards().iterator());
			System.out.println("Player gets " + betValue + "token");
			player.addToken(betValue);
			house.removeToken(betValue);
		} else {
			System.out.println("House win!");
			System.out.println("-----Player has these cards-----");
			printAllCards(player.getCards().iterator());
			System.out.println("-----House has these cards-----");
			printAllCards(house.getCards().iterator());
			System.out.println("House gets " + betValue + "token");
			player.removeToken(betValue);
			house.addToken(betValue);
		}
	}
}

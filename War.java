import java.util.ArrayList;
import java.util.Scanner;

public class War {

	public static void main(String[] args) {
		Scanner scanRound = new Scanner(System.in);
		int rounds = 0;
		int roundsPassed = 0;
		System.out.println("How many rounds?");
		rounds = scanRound.nextInt();
		//Initialize 
		MultiDS<Card> deck = new MultiDS<Card>(52);
		MultiDS<Card> p1 = new MultiDS<Card>(26);
		MultiDS<Card> p2 = new MultiDS<Card>(26);
		ArrayList<Card> discard1 = new ArrayList<Card>(52);
		ArrayList<Card> discard2 = new ArrayList<Card>(52);
		
		//Load Deck of Cards
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<13; j++) {
				deck.addItem(new Card(Card.Suits.values()[i], Card.Ranks.values()[j]));
			}
		}
		
		//Shuffles Deck
		deck.shuffle();
		
		//Distributes cards
		for(int i = 0; i < deck.size()/2; i++) {
			p1.addItem(deck.removeItem());//go to p1
			p2.addItem(deck.removeItem());//go to p2
		}
		
		System.out.println("Here is player 1's hand: \n");
		System.out.println(p1.toString() + "\n");
		System.out.println("Here is player 2's hand: \n");
		System.out.println(p2.toString() + "\n");
		System.out.println("Time to play!");
		
		//Main Loop

		while((!p1.empty())&&(!p2.empty())&&roundsPassed!=rounds) {
			if(p1.empty()) {
				for(int i = 0; i<discard1.size(); i++) {
					p1.addItem(discard1.get(i));
					discard1.remove(i);
				}
				System.out.println("Player 1's cards were refilled from discard");
			}
			if(p2.empty()) {
				for(int i = 0; i<discard2.size(); i++) {
					p1.addItem(discard2.get(i));
					discard2.remove(i);
				}
				System.out.println("Player 2's cards were refilled from discard");

			}
			Card CardPlay1 = p1.removeItem();
			Card CardPlay2 = p2.removeItem();
		

			int num = 0, num2 = 0;
			num = CardPlay1.compareTo(CardPlay2);
			if(num>0) {
				//Player 1 win
				System.out.println("Player 1 Wins Rnd "+roundsPassed+ ": " + CardPlay1 + " loses to "+ CardPlay2 +" : 2 cards");
				discard1.add(CardPlay2);
				discard1.add(CardPlay1);
				roundsPassed++;
			}
			if(num<0) {
				//Player 2 win
				System.out.println("Player 2 Wins Rnd "+roundsPassed+ ": " + CardPlay2 + " loses to "+ CardPlay1 +" : 2 cards");
				discard2.add(CardPlay2);
				discard2.add(CardPlay1);
				roundsPassed++;
			}
			if(num == 0) {
				if(p1.empty()) {
					for(int i = 0; i<discard1.size(); i++) {
						p1.addItem(discard1.get(i));
						discard1.remove(i);
					}
					System.out.println("Player 1's cards were refilled from discard");
					break;
				}
				if(p2.empty()) {
					for(int i = 0; i<discard2.size(); i++) {
						p1.addItem(discard2.get(i));
						discard2.remove(i);
					}
					System.out.println("Player 2's cards were refilled from discard");
					break;
				}
				System.out.println("WAR TIME!!! \n " +CardPlay1 +" ties " + CardPlay2);
				Card CardPlayed1num1 = p1.removeItem();
				Card CardPlayed2num1 = p2.removeItem();
				Card CardPlayed1num2 = p1.removeItem();
				Card CardPlayed2num2 = p2.removeItem();
				if(p1.empty()) {
					for(int i = 0; i<discard1.size(); i++) {
						p1.addItem(discard1.get(i));
						discard1.remove(i);
					}
					System.out.println("Player 1's cards were refilled from discard");
					continue;
				}
				if(p2.empty()) {
					for(int i = 0; i<discard2.size(); i++) {
						p1.addItem(discard2.get(i));
						discard2.remove(i);
					}
					System.out.println("Player 2's cards were refilled from discard");
					continue;
				}
				do {
				ArrayList<Card> cardsInWar = new ArrayList<Card>();
				cardsInWar.add(CardPlayed2num2);
				cardsInWar.add(CardPlayed2num1);
				cardsInWar.add(CardPlayed1num2);
				cardsInWar.add(CardPlayed1num1);
				cardsInWar.add(CardPlay2);
				cardsInWar.add(CardPlay1);

				//do war
				num2 = CardPlayed1num2.compareTo(CardPlayed2num2);
				if(num2>0) {
					//Player 1 win
					System.out.println("Player 1 Wins Rnd "+roundsPassed+ ": " + CardPlayed1num2 + " loses to "+ CardPlayed2num2 +" : 6 cards");
					discard1.add(CardPlay2);
					discard1.add(CardPlay1);
					roundsPassed++;
					for(int i = 0; i<cardsInWar.size(); i++) {
						p1.addItem(cardsInWar.get(i));
						cardsInWar.remove(i);
					}
					continue;
				}
				if(num2<0) {
					//Player 2 win
					System.out.println("Player 2 Wins Rnd "+roundsPassed+ ": " +CardPlayed2num2 + " loses to "+ CardPlayed1num2 +" : 6 cards");
					discard2.add(CardPlay2);
					discard2.add(CardPlay1);
					roundsPassed++;
					for(int i = 0; i<cardsInWar.size(); i++) {
						p2.addItem(cardsInWar.get(i));
						cardsInWar.remove(i);
					}
					continue;
				}
				
				}while((CardPlayed1num2==CardPlayed2num2)&&(!p1.empty())&&(!p2.empty()) );
				continue;
				}
			}
		}
}

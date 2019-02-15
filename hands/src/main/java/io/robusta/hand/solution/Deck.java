package io.robusta.hand.solution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.interfaces.IDeck;

public class Deck extends LinkedList<Card> implements IDeck{

	
	private static final long serialVersionUID = -4686285366508321800L;
	
	public Deck() {

	}
	
	@Override
	public Card pick() {
		Collections.shuffle(this);
		Card card = this.getFirst();
		this.remove(card);
		return card;
		
	}


	

	@Override
	public TreeSet<Card> pick(int number) {
		TreeSet<Card> result = new TreeSet<Card>();
		for (int i = 1; i <= number; i++) {
			Card card = this.pick();
			result.add(card);			
		}
		// reuse pick()
		return result;
	}

	@Override
	public Hand giveHand() {
		// A hand is a **5** card TreeSet
		Hand result = new Hand();
		result.addAll(this.pick(5));
		return result;
	}
	

	
	
}

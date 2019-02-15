package io.robusta.hand.solution;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import java.util.Iterator;

import io.robusta.hand.Card;
import io.robusta.hand.HandClassifier;
import io.robusta.hand.HandValue;
import io.robusta.hand.interfaces.IHand;
import io.robusta.hand.interfaces.IHandResolver;

public class Hand extends TreeSet<Card> implements IHand {

	private static final long serialVersionUID = 7824823998655881611L;

	/**
	 * beats is the same than compareTo, but with a nicer name. The problem is that
	 * it does not handle equality :(
	 * 
	 * @param villain
	 * @return
	 */
	@Override
	public boolean beats(IHand villain) {
		int result = this.getValue().compareTo(villain.getValue());
		return result > 0;
	}

	@Override
	public IHand getHand() {
		return this;
	}

	@Override
	public HandClassifier getClassifier() {

		return this.getValue().getClassifier();
	}

	@Override
	public boolean isStraight() {
		Card previous = null;
		if (this.hasAce()) {
			this.remove(this.last());
			if (this.first().getValue() == 2 || this.first().getValue() == 10) {
				for (Card c : this) {
					if (previous != null) {						
						if (c.getValue() != (previous.getValue() + 1)) {
							return false;
						}
					}
		
					previous = c;
					
				}
			} else return false;
		}else for (Card c : this) {
				if (previous != null) {
	
					if (c.getValue() != (previous.getValue() + 1)) {
						return false;
					}
				}
	
				previous = c;
			}
		return true;
	}

	@Override
	public boolean isFlush() {
		Card previous = null;
		for (Card c : this) {
			if (previous == null) {
				previous = c;
			} else {
				if (c.getColor() != previous.getColor()) {
					return false;
				} else
					previous = c;
			}
		}
		return true;
	}

	/**
	 * Returns number of identical cards 5s5cAd2s3s has two cardValue of 5
	 */
	@Override
	public int number(int cardValue) {
		int result = 0;
		for (Card current : this) {
			if (current.getValue() == cardValue) {
				result++;
			}
		}
		return result;
	}

	/**
	 * The fundamental map Check the tests and README to understand
	 */
	@Override
	public Map<Integer, List<Card>> group() {
		HashMap<Integer, List<Card>> map = new HashMap<>();
		for (Card c : this) {
			List<Card> liste = map.get(c.getValue());
			if (liste == null) {
				liste = new ArrayList<Card>();
				map.put(c.getValue(), liste);
			}
			map.get(c.getValue()).add(c);
		}
		// fill the map

		return map;
	}

	// different states of the hand
	// Using stateful variables. We need to fill this, then use it before.
	int levelValue = 0;

	int getLevelValue() {
		Map<Integer, List<Card>> map = this.group();
		int result = 0;
		if (this.isPair() || this.isDoublePair()) {
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				if (entry.getValue().size() == 2) {
					result = entry.getKey();
				}
			}
		} else if (this.isTrips() || this.isFull()) {
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				if (entry.getValue().size() == 3) {
					result = entry.getKey();
				}
			}
		} else if (this.isFourOfAKind()) {
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				if (entry.getValue().size() == 4) {
					result = entry.getKey();
				}
			}
		} else if (this.isHighCard() || this.isFlush() || this.isStraight() || this.isStraightFlush()) {
			result = this.last().getValue();
		}
		return result;
	}

	// Needed with two pairs or full
	int secondValue = 0;

	int getSecondValue() {
		Map<Integer, List<Card>> map = this.group();
		int result = 0;
		if (this.isDoublePair() || this.isFull()) {
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				if (entry.getValue().size() == 2 && entry.getValue().size() != this.getLevelValue()) {
					result = entry.getKey();
				}
			}
		}
		return result;
	}

	// Put all cards for flush or highCard ;
	TreeSet<Card> singleCards = new TreeSet<>();

	/**
	 * return all single cards not used to build the classifier
	 *
	 * @param map
	 * @return
	 */
	TreeSet<Card> getSingleCards(Map<Integer, List<Card>> map) {
		// method is done, DO NOT TOUCH !
		TreeSet<Card> singleCards = new TreeSet<>();
		// May be adapted at the end of project:
		// if straight or flush : return empty
		// If High card, return 4 cards
		for (List<Card> group : map.values()) {
			if (group.size() == 1) {
				singleCards.add(group.get(0));
			}
		}
		return singleCards;
	}

	@Override
	public boolean isPair() {
		Map<Integer, List<Card>> map = this.group();
		if (map.size() == 4) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDoublePair() {
		Map<Integer, List<Card>> map = this.group();
		boolean result = true;
		if (map.size() != 3) {
			return false;
		}else for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
			if (entry.getValue().size() > 2) {
				return false;
			}
		}return true;
	}

	@Override
	public boolean isHighCard() {
		Map<Integer, List<Card>> map = this.group();
		return map.size() == 5 && !this.isFlush() && !this.isStraight();
	}

	@Override
	public boolean isTrips() {
		Map<Integer, List<Card>> map = this.group();
		return map.size() == 3 && !this.isDoublePair();
	}

	@Override
	public boolean isFourOfAKind() {
		Map<Integer, List<Card>> map = this.group();
		return map.size() == 2 && !this.isFlush();
	}

	@Override
	public boolean isFull() {
		Map<Integer, List<Card>> map = this.group();
		boolean result = true;
		if (map.size() == 2) {
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				if (entry.getValue().size() < 2) {
					result = false;
				}
			}
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean isStraightFlush() {
		return this.isFlush() && this.isStraight();
	}

	@Override
	public HandValue getValue() {
		HandValue handValue = new HandValue();

		// Exemple for FourOfAKind ; // do for all classifiers
		if (this.isFourOfAKind()) {
			handValue.setClassifier(HandClassifier.FOUR_OF_A_KIND);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}
		if (this.isPair()) {
			handValue.setClassifier(HandClassifier.PAIR);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		if (this.isDoublePair()) {
			handValue.setClassifier(HandClassifier.TWO_PAIR);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group()));// or this.getsingleCards()
			handValue.setSecondLevel(this.getSecondValue());
			return handValue;
		}

		if (this.isHighCard()) {
			handValue.setClassifier(HandClassifier.HIGH_CARD);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		if (this.isTrips()) {
			handValue.setClassifier(HandClassifier.TRIPS);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		if (this.isStraight() && !this.isFlush()) {
			handValue.setClassifier(HandClassifier.STRAIGHT);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		if (this.isFlush() && !this.isStraight()) {
			handValue.setClassifier(HandClassifier.FLUSH);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		if (this.isFull()) {
			handValue.setClassifier(HandClassifier.FULL);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			handValue.setSecondLevel(this.getSecondValue());
			return handValue;
		}

		if (this.isStraightFlush()) {
			handValue.setClassifier(HandClassifier.STRAIGHT_FLUSH);
			handValue.setLevelValue(this.getLevelValue());
			handValue.setSingleCards(this.getSingleCards(this.group())); // or this.getsingleCards()
			return handValue;
		}

		// For the flush, all singleCards are needed

		return handValue;
	}

	@Override
	public boolean hasCardValue(int level) {
		Map<Integer, List<Card>> map = this.group();
		return map.get(level) != null;
	}

	@Override
	public boolean hasAce() {
		Map<Integer, List<Card>> map = this.group();
		return map.get(14) != null;
	}

	@Override
	public int highestValue() {
		// ace might be the highest value
		return this.last().getValue();
	}

	@Override
	public int compareTo(IHandResolver o) {
		// You should reuse HandValue.compareTo()
		return 0;
	}

}

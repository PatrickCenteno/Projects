from card import Card
from constants import suits
from constants import values
import random

class Deck:
	""" Cards in a deck. Shuffle, deals randomly, counts, prints the cards """
	def __init__(self):
		self.cards = self.load_deck()
		self.shuffle_deck()

	def load_deck(self):
		temp = []
		for s in suits:
			for v in values:
				temp.append(Card(s, v))
		return temp

	def deal_card(self):
		return self.cards.pop()

	def shuffle_deck(self):
		return random.shuffle(self.cards)

	def length_of_deck(self):
		return len(self.cards)

	def print_the_deck(self):
		for c in self.cards:
			print(c.suit + " " + c.value)

	def is_done(self):
		if not self.cards:
			return True
		else:
			return False

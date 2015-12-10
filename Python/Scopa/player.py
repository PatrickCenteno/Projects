from constants import HAND_ERROR
from constants import values

class Player:
	""" Name of player, # cards, tricks, scopas prints hand """
	def __init__(self, name):
		self.name = name
		self.hand = []
		self.coins = 0
		self.scopa = 0
		self.total_tricks = 0
		self.sevens = 0
		self.trick_cards = []
		self.score = 0

	def add_card(self, card):
		self.hand.append(card)

	def print_hand(self):
		print(self.name + "'s hand: ")
		if not self.hand:
			print HAND_ERROR
		else:
			for i in range(0, len(self.hand)):
				print(str(i) + ": " + self.hand[i].suit + " " + self.hand[i].value)
		print "\n"

	def make_trick(self, table, hand_index, table_index):
		# Only called if its a valid trick
		self.add_trick(self.hand[hand_index])
		self.add_trick(table[table_index])
		self.total_tricks += 1
		""" Only need to check the hand for whether is a seven or a cois
			Because the funciton is only called when its a valid trick """

		# check if its a seven
		if values[self.hand[hand_index].value] == 7:
			self.sevens += 1

		# check if its a coin
		if self.hand[hand_index].suit == "COINS":
			self.coins += 1

		del table[table_index]
		del self.hand[hand_index]

	def make_scopa(self, table, hand_index):
		# Only called if its a valid scopa
		for t in table:
			self.add_trick(t)
		self.add_trick(self.hand[hand_index])

		del table[:]
		del self.hand[hand_index]

		self.scopa += 1
		self.total_tricks += 1


	def print_stats(self):
		print "Score: " + str(self.score)
		print "Coins: " + str(self.coins)
		print "Scopas: " + str(self.scopa)
		print "Total Tricks: " + str(self.total_tricks)
		print "Seven Cards: " + str(self.sevens)
		print "Trick Cards: "
		for t in self.trick_cards:
			print t.suit + " " + t.value

	def add_trick(self, card):
		self.trick_cards.append(card);

	# TODO: calculate statistics for each player
	# I.E. how many coins or scopas they have

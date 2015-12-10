from card import Card
from deck import Deck
from player import Player

import constants
import random

def game():
	"""The game starts here."""
	dealer = Player("Dealer")
	print constants.WELCOME
	player = Player(raw_input(constants.NAME))
	deck = Deck()
	table = []

	deal_the_cards(dealer, player, deck)
	cards_on_table(deck, table)
	turn = 0
	playing = True

	while playing:
		# TO DO: Add more winning conditions
		if deck.is_done():
			print "Deck empty"
			#This will check for who wins
			check_winner(dealer, player)
			playing = False
		if len(dealer.hand) == 0 and len(player.hand) == 0:
			deal_the_cards(dealer, player, deck)

		# PLAYER'S TURN
		if turn % 2 == 0:
			# Print hand and table cards
			begin_turn(player, table, True)
			#select card
			select_again = True
			while select_again:
				input = raw_input(constants.PLAYER_INPUT)
				if input == 't':
					#select card from hand, then select card from table
					hand_index, table_index = select_trick()
					if hand_index >= len(player.hand) or table_index >= len(table):
						# Out of bounds
						print constants.OUT_OF_BOUNDS
					elif values_equal(player.hand[hand_index], table[table_index]):
						# valid trick
						# Desposit selected cards into trick cards
						player.make_trick(table, hand_index, table_index)

						print_table(table)
						player.print_hand()
						print constants.PLAYER_TRICK
						player.print_stats()
						turn += 1
						select_again = False
					else:
						# Invalid Trick
						print constants.TRICK_SELECTION_ERROR
				elif input == 'd':
					hand_input = int(raw_input(constants.PLAYER_PUT_DOWN))
					if hand_input < len(player.hand):
						table.append(player.hand[hand_input])
						del player.hand[hand_input]
						select_again = False
						turn += 1
					else:
						# Out of bounds
						print constants.OUT_OF_BOUNDS
				elif input == 's':
					hand_index = int(raw_input(constants.PLAYER_PUT_DOWN))
					if check_scopa_valid(player, table, hand_index):
						print constants.P_SCOPA
						select_again = False
						turn += 1
					else:
						print constants.TRICK_SELECTION_ERROR
				else:
					print constants.KNIFE 
					select_again = False
					playing = False

		# DEALER'S TURN
		elif turn %2 == 1:
			#dont show hand, just table
			begin_turn(dealer, table, False)
			#dealer checks for trick
			dealers_trick = check_for_trick(dealer, table, dealer.hand)
			if dealers_trick == 0:
				# dealer discards random card on table
				discard_index = random.randint(0, len(dealer.hand)-1)
				table.append(dealer.hand[discard_index])
				del dealer.hand[discard_index]
				print "The dealer added card to the table"
			elif dealers_trick == -1:
				turn += 1
			else:
				dealer.make_trick(table, dealers_trick[1], dealers_trick[0])
				print constants.DEALER_TRICK
				dealer.print_stats()
				# dealer.add_trick(table[dealers_trick[0]])
				# dealer.add_trick(dealer.hand[dealers_trick[1]])
				# del table[dealers_trick[0]]
				# del dealer.hand[dealers_trick[1]]
			turn += 1

def check_winner(dealer, player):
	# Check 7's
	if player.sevens > dealer.sevens and player.sevens != dealer.sevens:
		player.score += 1
	else:
		dealer.score += 1

	# Check coins
	if player.coins > dealer.coins and player.coins != dealer.coins:
		player.score += 1
	else:
		dealer.score += 1
	
	# Check total tricks
	if player.total_tricks > dealer.total_tricks and player.total_tricks != dealer.total_tricks:
		player.score += 1
	else:
		dealer.score += 1

	# Add each scopa to the total score
	player.score += player.scopa
	dealer.score += dealer.scopa

	print "\n"
	dealer.print_stats()
	player.print_stats()

	if dealer.score > player.score:
		print constants.LOSE 
	elif player.score > dealer.score:
		print constants.WIN 
	else:
		print constants.TIE 


def begin_turn (player, table, show):
	if show:
		print player.name + "'s: turn "
		player.print_hand()
		print constants.TABLE
		print_table(table)
	else:
		print constants.DEALERS_TURN
		print_table(table)


def select_trick():
	hand_input = int(raw_input("Select index of card in your hand for trick:\n"))
	table_input = int(raw_input("Select index of card on table for trick:\n"))
	print "Hand index: " + str(hand_input) + " " + " table: " + str(table_input)
	return hand_input, table_input


def deal_the_cards(p1, p2, deck):
	for i in range(0, constants.HAND_SIZE):
		dealt_card  = deck.deal_card()
		dealt_card.index = i
		p1.add_card(dealt_card)

		dealt_card  = deck.deal_card()
		dealt_card.index = i
		p2.add_card(dealt_card)

def cards_on_table(deck, table):
	for i in range(0, constants.TABLE_SIZE):
		dealt_card  = deck.deal_card()
		dealt_card.index = i
		table.append(dealt_card)

def print_table(table):
	i = 0
	for t in table:
		print str(i) + ": " + t.suit + " " + t.value
		i += 1

def check_for_trick(player, table, hand):
	indices = []
	# First check for scopa
	scopa_index = scopa(table, hand)
	if scopa_index > -1:
		print constants.D_SCOPA
		player.make_scopa(table, scopa_index)
		return -1
	# table index first, then hand index
	for i in range(0, len(table)):
		for j in range(0, len(hand)):
			if constants.values[table[i].value] == constants.values[hand[j].value]:
				indices.append(i)
				indices.append(j)
	if not indices:
		print "no tricks found"
		return 0
	elif len(indices) <= 2:
		return indices #theres only one possible trick
	else:
		return find_greatest_pair(indices, table, hand)


def find_greatest_pair(indices, table, hand):
	#find best possible trick
	top_indices = []
	max_value = 0
	max_index = 0
	max_index2 = 0
	for i in range(0, len(indices), 2):
		if constants.values[table[indices[i]].value] > max_value:
			max_value = constants.values[table[indices[i]].value]
			max_index = indices[i]
			max_index2 = indices[i + 1]
	top_indices.append(max_index)
	top_indices.append(max_index2)
	return top_indices

def check_scopa_valid(player, table, hand_index):
	table_sum = find_table_sum(table)

	if constants.values[player.hand[hand_index].value] == table_sum:
		return True
	else:
		return False

def scopa(table, hand):
	# Add sum of table
	table_sum = find_table_sum(table)

	""" Iterate through hand to check if card in hand
		has value equal to table_sum """
	for i in range(0 , len(hand)):
		if constants.values[hand[i].value] == table_sum:
			return i

	# No scopa found
	return -1

def find_table_sum(table):
	table_sum = 0
	for t in table:
		table_sum += constants.values[t.value] 
	return table_sum

def values_equal(c1, c2):
	if constants.values[c1.value] == constants.values[c2.value]:
		return True
	else:
		return False



if __name__ == '__main__':
	main()

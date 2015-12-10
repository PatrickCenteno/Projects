from card import Card
from deck import Deck
from player import Player
from game import game

import constants
import random

def main():
	""" Request input from player """

	while True:
		input = raw_input(constants.ENTER)
		if input == 's':
			game()
			break
		
	# Need text module

if __name__ == '__main__':
	main()

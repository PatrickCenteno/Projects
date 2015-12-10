""" Size of player's hand and the amount of cards on table """
HAND_SIZE = 3
TABLE_SIZE = 4

# suits and values declaration
suits = ["CUPS", "COINS", "SWORDS", "CLUBS"]
values = {"ACE":1, "TWO":2, "THREE":3, "FOUR":4, \
          "FIVE":5, "SIX":6, "SEVEN":7, "FANTE":8, \
		  "HORSE":9, "KING":10}

# String constants
HAND_ERROR = "Hand not dealt."
ENTER = "Press 's' to start the game!\n"
WELCOME = "Welcome to Scopa!\n"
NAME = "Please enter your name: "
PLAYERS_TURN = "Player's turn: "
DEALERS_TURN = "Dealer's turn: "
WIN = "You win!"
LOSE = "Dealer wins and you lose!"
TIE = "Tie Game! What are the odds?"
TABLE = "Here's the table \n"
PLAYER_INPUT = "Press 't' to make a trick, 'd' to put card on table, press s to try and Scopa!\n"
PLAYER_PUT_DOWN = "Select index of card in hand to place on table "
PLAYER_TRICK = "You made a trick!"
DEALER_TRICK = "Dealer made a trick!"
TRICK_SELECTION_ERROR = "This is not a valid trick, please select again "
OUT_OF_BOUNDS = "Your selection was out of bounds, please try again\n"
D_SCOPA = "Dealer made a scopa! "
P_SCOPA = "Player made a scopa! "
KNIFE = "THATS IT! You took out the knife from under the table!\n You autmatically win!"

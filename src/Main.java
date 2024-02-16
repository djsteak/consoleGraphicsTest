import java.util.Scanner;
/*
The program needs a major rework, as a lot of parts are unused and/or not optimized.
This is due to me second guessing some of the logic or just changing the way the game is played halfway through making it.
when the game starts, blue goes first (i recommend making blue someone who does not know the layout of the board)
the game will say 'blue' and then you input an action (listed below) into the console
then the game will print a large space to hide what the blue player did (red must not see what blue is doing, and vise-versa)
the game will say 'red' and then its red's turn.
once red goes than the process will repeat once more without showing the board. that process is called a cycle (or whatever you would like to call it)
every two cycles the game board is shown
the two numbers shown every turn are the block values (gonna change later as it was intended for debugging)
actions are as follows:
"up" = moves up
"down" = moves down
"left" = moves left
"right" = moves right
"block" or "defend" = grants invulnerability that cycle and the one after, breaks when attacked
"attack" = attacks
 */
public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sizeX = 7;
        int sizeY = 7;
        boolean debug = false;
        Board idk = new Board(sizeX,sizeY);
        idk.changeCell(0,0,CellTypes.playerSpec, CellTypes.hash, 2, 1); //walls
        idk.changeCell(1,0,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(2,0,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(1,2,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(2,2,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(4,6,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(5,6,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(4,4,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(5,4,CellTypes.baseSpec, CellTypes.wall, 1, 0);
        idk.changeCell(sizeX - 1, sizeY - 1,"","",4,1);

        //System.out.println(idk.getCellType(sizeX - 1, sizeY - 1, 1));
        idk.display(debug);// ---the method for displaying the game board
        int playerX = 0; // player positions are kept here, not the most reliable but its simple and good enough so im not changing it yet
        int playerY = 0;
        int playerBlock = 0;
        int player2X = sizeX - 1;
        int player2Y = sizeY - 1;
        int player2Block = 0;
        int duration = 0;// how long the game has been, in cycles. also used in the calculation to show the board every two cycles
        boolean turn = true;// whos turn is it


        while (true) { // every itteration of this loop is a cycle, as both players move once during this.
            boolean plr1atk = false;// the way attacks are calculated is these booleans, the booleans are set to true and at the of the cycle if one is true then that player attacks
            boolean plr2atk = false;// its like that because if say blue attacks, since blue inputs first, red is given a chance to respond
            if (playerBlock > 0) {playerBlock -= 1;} if (player2Block > 0){player2Block -= 1;}
            System.out.println(playerBlock + " " + player2Block);
            System.out.println("blue");// says whos turn it is
            String input = in.nextLine(); // then gets the input
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");// just hides the last players input
            switch (input) {
                case "up" -> { // if up
                    int targetY = playerY - 1; // then the y is decreased by one, as the game array starts at 0,0 in the top left

                    if (targetY >= 0) { // prevents the player from going out of bounds
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(playerX, targetY, 0));
                        if (idk.getCellType(playerX, targetY, 0) == 3 && idk.getCellType(playerX, targetY, 1) != 4) {// player cannot move into cells that have certain states (see CellTypes)
                            //System.out.println(idk.getCellType(playerX, targetY, 0));
                            idk.changeCell(playerX, targetY, CellTypes.playerSpec, CellTypes.hash, 2, 1); // adds player type to the new cell
                            idk.changeCell(playerX, playerY, CellTypes.baseSpec, CellTypes.hash, 0, 1); // removes player type from old cell
                            playerY -= 1;
                        }
                    }
                }
                case "down" -> {
                    int targetY = playerY + 1;
                    if (targetY < sizeY) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(playerX, targetY, 0));
                        if (idk.getCellType(playerX, targetY, 0) == 3 && idk.getCellType(playerX, targetY, 1) != 4) {
                            //System.out.println(idk.getCellType(playerX, targetY, 0));
                            idk.changeCell(playerX, targetY, CellTypes.playerSpec, CellTypes.hash, 2, 1);
                            idk.changeCell(playerX, playerY, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            playerY += 1;
                        }
                    }
                }
                case "left" -> {
                    int targetX = playerX - 1;
                    if (targetX >= 0) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(targetX, playerY, 0));
                        if (idk.getCellType(targetX, playerY, 0) == 3 && idk.getCellType(targetX, playerY, 1) != 4) {
                            //System.out.println(idk.getCellType(targetX, playerY, 0));
                            idk.changeCell(targetX, playerY, CellTypes.playerSpec, CellTypes.hash, 2, 1);
                            idk.changeCell(playerX, playerY, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            playerX -= 1;
                        }
                    }
                }
                case "right" -> {
                    int targetX = playerX + 1;
                    if (targetX < sizeX) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(targetX, playerY, 0));
                        if (idk.getCellType(targetX, playerY, 0) == 3 && idk.getCellType(targetX, playerY, 1) != 4) {
                            //System.out.println(idk.getCellType(targetX, playerY, 0));
                            idk.changeCell(targetX, playerY, CellTypes.playerSpec, CellTypes.hash, 2, 1);
                            idk.changeCell(playerX, playerY, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            playerX += 1;
                        }
                    }
                }
                case "debug" -> debug = !debug;
                case "attack" -> plr1atk = true;
                case "block", "defend" -> {
                    idk.setBlock(playerX, playerY);
                    playerBlock = 2;
                }
                case "mine" -> idk.mine(false, true, playerX, playerY);
            }

            System.out.println("red");
            input = in.nextLine();

            switch (input) {
                case "up" -> {
                    int targetY = player2Y - 1;
                    if (targetY >= 0) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(player2X, targetY, 0));
                        if (idk.getCellType(player2X, targetY, 0) == 3 && idk.getCellType(player2X, targetY, 1) != 4) {
                            //System.out.println(idk.getCellType(player2X, targetY, 0));
                            idk.changeCell(player2X, targetY, CellTypes.playerSpec, CellTypes.hash, 4, 1);
                            idk.changeCell(player2X, player2Y, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            player2Y -= 1;
                        }
                    }
                }
                case "down" -> {
                    int targetY = player2Y + 1;
                    if (targetY < sizeY) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(player2X, targetY, 0));
                        if (idk.getCellType(player2X, targetY, 0) == 3 && idk.getCellType(player2X, targetY, 1) != 4) {
                            //System.out.println(idk.getCellType(player2X, targetY, 0));
                            idk.changeCell(player2X, targetY, CellTypes.playerSpec, CellTypes.hash, 4, 1);
                            idk.changeCell(player2X, player2Y, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            player2Y += 1;
                        }
                    }
                }
                case "left" -> {
                    int targetX = player2X - 1;
                    if (targetX >= 0) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(targetX, player2Y, 0));
                        if (idk.getCellType(targetX, player2Y, 0) == 3 && idk.getCellType(targetX, player2Y, 1) != 4) {
                            //System.out.println(idk.getCellType(targetX, player2Y, 0));
                            idk.changeCell(targetX, player2Y, CellTypes.playerSpec, CellTypes.hash, 4, 1);
                            idk.changeCell(player2X, player2Y, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            player2X -= 1;
                        }
                    }
                }
                case "right" -> {
                    int targetX = player2X + 1;
                    if (targetX < sizeX) {
                        //System.out.println(funnyConsoleExtras.ANSI_GREEN + input + funnyConsoleExtras.ANSI_RESET);
                        //System.out.println(idk.getCellType(targetX, player2Y, 0));
                        if (idk.getCellType(targetX, player2Y, 0) == 3 && idk.getCellType(targetX, player2Y, 1) != 4) {
                            //System.out.println(idk.getCellType(targetX, player2Y, 0));
                            idk.changeCell(targetX, player2Y, CellTypes.playerSpec, CellTypes.hash, 4, 1);
                            idk.changeCell(player2X, player2Y, CellTypes.baseSpec, CellTypes.hash, 0, 1);
                            player2X += 1;
                        }
                    }
                }
                case "debug" -> debug = !debug;
                case "attack" -> plr2atk = true;
                case "block", "defend" -> {
                    idk.setBlock(player2X, player2Y);
                    player2Block = 2;
                }
                case "mine" -> idk.mine(false, true, player2X, player2Y);
            }
            if (plr1atk) {
                boolean hit = idk.attack(playerX,playerY,playerBlock,player2Block,turn);
                if (hit) {
                    System.out.println(hit);
                    player2Block = 0;
                    idk.setBlock(player2X,player2Y);
                }
            }
            if (plr2atk) {
                boolean hit = idk.attack(player2X,player2Y,playerBlock,player2Block,!turn);
                if (hit) {
                    System.out.println(hit);
                    playerBlock = 0;
                    idk.setBlock(playerX,playerY);
                }
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");// hides the last move (gonna change later)
            if ((duration % 2) == 1) { // every two cycles it displays the board
                idk.display(debug);
            }
            duration += 1;


        }

    }
}
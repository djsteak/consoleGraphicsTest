import java.util.ArrayList;

public class Board {
    private final ArrayList<ArrayList<Cell>> boardArray;
    private int xSize = 0;
    private int ySize = 0;
    

    public Board(int x, int y){ // the only contructor
        xSize = x;
        ySize = y;
        ArrayList<ArrayList<Cell>> toBeAdded = new ArrayList<>();// added it a light term, it replaces the old arraylist, which is blank at the time of this excecuting
        for (int x1 = 0; x1 < y; x1++){
            ArrayList<Cell> toBeAdded2 = new ArrayList<>();// added to the 'toBeAdded' arraylist
            for (int y1 = 0; y1 < x; y1++){
                String specsNew = funnyConsoleExtras.ANSI_BLUE;
                String charNew = CellTypes.hash;
                String specsNewL2 = "";
                String charNewL2 = "";
                Cell newCell = new Cell(specsNew,charNew,3,specsNewL2,charNewL2,0);// new cell and adds it

                toBeAdded2.add(newCell);
            }
            toBeAdded.add(toBeAdded2);
        }
        boardArray = toBeAdded;
    }
    // changes cell properties

    public void changeCell(int x,int y,String spec, String cha, int c, int l){// x and y is the cell to change. cha and spec are optional and will override the type (to not do them, just do "" for each). c is the type and l is the layer to apply the type to
        try {
            Cell toChange = boardArray.get(y).get(x);
            if (!spec.equals("")) {
                toChange.setSpecs(spec,l);
            }
            if (!cha.equals("")) {
                toChange.setChar(cha,l);
            }
            toChange.setTypeID(c, l);
        } catch (Exception e) {

        }
    }

    public String getCellChar(int y,int x, int l) {// self explanitory (and unused)
        if (l == 0) {
            return boardArray.get(x).get(y).getChar();
        } else
        if (l == 1) {
            return boardArray.get(x).get(y).getCharL2();
        } else {
            return "";
        }
    }

    public boolean[] getMine(int x, int y) {// is there a mine at x,y
        boolean[] bruh = {false,false};
        if (boardArray.get(x).get(y).isBlueMine()) {
            bruh[0] = true;
        }
        if (boardArray.get(x).get(y).isRedMine()) {
            bruh[1] = true;
        }
        return bruh;
    }
    public void setMine(int x, int y, boolean team, boolean v) {// sets a mine at x,y that belongs to team, true being blue, and v is if the mine is getting removed
        boardArray.get(x).get(y).setMine(team,v);
    }

    public String getCellSpec(int x,int y, int l) {// gets the spec of x,y and of layer l
        if (l == 0) {
            return boardArray.get(x).get(y).getSpecs();
        } else
        if (l == 1) {
            return boardArray.get(x).get(y).getSpecsL2();
        } else {
            return "";
        }
    }
    public int getCellType(int x,int y, int l) {// gets cell type of x,y and of layer l
        try {
            if (l == 0) {
                return boardArray.get(y).get(x).getTypeID();
            } else
            if (l == 1) {
                return boardArray.get(y).get(x).getTypeIDL2();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    public void setBlock(int x,int y) { // does not do anything, just a failed attempt at creating the defend command
        try {
            boardArray.get(y).get(x);
        } catch (Exception e) {}
    }
    // attacks in every adjacent square
    public boolean attack (int pointX, int pointY, int block1, int block2, boolean turn) {
        System.out.println(turn + " " + block1 + " " + block2);
        try { // 8
            int targetX = pointX - 1;
            int targetY = pointY + 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            // if the cell type is not 1 (wall) then continue
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                //
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 != 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 != 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                        return true;
                    }
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX - 1;
            int targetY = pointY - 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX + 1;
            int targetY = pointY - 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX + 1;
            int targetY = pointY + 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 != 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }

                    } else {
                        if (block1 != 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX;
            int targetY = pointY - 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX;
            int targetY = pointY + 1;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX - 1;
            int targetY = pointY;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        try { // 8
            int targetX = pointX + 1;
            int targetY = pointY;
            System.out.println(this.getCellType(targetX, targetY, 0));
            if (this.getCellType(targetX, targetY, 0) != 1 ) {
                if (this.getCellType(targetX, targetY, 1) == 4 || this.getCellType(targetX,targetY,1) == 2) {
                    if (turn){
                        if (block2 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    } else {
                        if (block1 == 0) {
                            changeCell(targetX, targetY, "", "", 0, 1);
                        }
                    }
                    return true;
                } else {}
            }
        } catch (Exception e){}
        return false;
    }

    public void attackPoint (int x,int y, int block1, int block2) {
        try {
            if (this.getCellType(x, y, 1) == 2) { // player 1 here
                this.changeCell(x,y,"","",0,1);
            }
            if (this.getCellType(x, y, 1) == 4) { // player 2 here
                this.changeCell(x,y,"","",0,1);
            }
            if (this.getMine(x,y)[0] || this.getMine(x,y)[1]){ // mine here
                this.setMine(x,y,true,false);
                this.setMine(x,y,false,false);
            }
        } catch (Exception e) {}
    }

    public void displayRow (int row, boolean debug) {// unsused, but hopefully works

        for (Cell cell : boardArray.get(row)) {
            cell.display(debug);
        }
    }

    public void mine(boolean team, boolean v, int x, int y){// experimental
        Cell toChange = boardArray.get(y).get(x);
        toChange.setMine(team, v);
    }

    public void display (boolean debug){
        // System.out.println(boardArray);
        for (ArrayList<Cell> row : boardArray){
            for (Cell cell : row) {// for every cell, call display on it
                cell.display(debug);
            }
            System.out.print("\n");
        }
    }
}

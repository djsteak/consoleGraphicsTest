import java.util.ArrayList;

public class Cell {
    private String specs = "";
    private String character;
    private String specsL2 = "";
    private String characterL2 = "";
    private boolean blueMine = false;
    private boolean redMine = false;
    private int typeID = 0;
    private int typeIDL2 = 0;


    public Cell (String specsnew, String cha, int ty1, String specL2, String chaL2, int ty2) {// constructor with args
        this.specs = specsnew;
        this.character = cha;
        this.characterL2 = chaL2;
        this.specsL2 = specL2;
        this.typeID = ty1;
        this.typeIDL2 = ty2;
    }
    public Cell () {} // constructor with no args

    public String getChar (){
        return this.character;
    } // gets the character
    public String getCharL2 () {
        return this.characterL2;
    } // get the second layer character
    public String getSpecs (){
        return this.specs;
    } // gets the specifications
    public String getSpecsL2 () {
        return this.specsL2;
    } // gets the layer 2 specs
    public int getTypeID (){
        return this.typeID;
    }// gets typeid
    public int getTypeIDL2 () {
        return this.typeIDL2;
    } // gets layer 2 typeid

    public void setChar (String c, int l) {// sets the character on the specified layer
        if (l == 0) {
            this.character = c;
        } else if (l == 1){
            this.characterL2 = c;
        }

    }
    public void setTypeID (int c, int l) {// sets typeid for the specified layer
        if (l == 0) {
            this.typeID = c;
        } else if (l == 1){
            this.typeIDL2 = c;
        }

    }
    public void setSpecs (String specsnew,int l) {// sets specs for a layer
        if (l == 0) {
            this.specs = specsnew;
        } else if (l == 1){
            this.specsL2 = specsnew;
        }
    }
    public boolean isBlueMine () { // experimental
        return blueMine;
    }
    public boolean isRedMine () {
        return redMine;
    }// experiamental
    public void setMine (boolean team, boolean v){// experiamental
        if (v) {
            if (team) {
                blueMine = v;
            } else {
                redMine = v;
            }
        } else {
            blueMine = v;
            redMine = v;

        }
    }
    public void display (boolean debug){// if the typeidl2 = 0 then it will display typeid
        if (debug) {
            if (typeIDL2 == 0) {
                System.out.print(specs + " " + typeID + " " + funnyConsoleExtras.ANSI_RESET);
            } else {
                System.out.print(specs + " " + typeIDL2 + " " + funnyConsoleExtras.ANSI_RESET);
            }
        } else {
            if (typeIDL2 != 0) {
                System.out.print(CellTypes.types[typeIDL2]);
            } else {
                System.out.print(CellTypes.types[typeID]);
            }
        }
    }

    public String toString(){
        return specs + character + funnyConsoleExtras.ANSI_RESET;
    }// toString
}

public class CellTypes {
    /* typeids are as follows
    0 = null
    1 = wall
    2 = player
    3 = empty
    4 = enemy
    5 = mine (experimental, might not stay)
     */
    public static String[] types = {//cells will print what one applies to them unless they have a custom setting
            "",
            "███" + funnyConsoleExtras.ANSI_RESET,
            funnyConsoleExtras.ANSI_BLUE + " ◯ " + funnyConsoleExtras.ANSI_RESET,
            " # ",
            funnyConsoleExtras.ANSI_RED + " ◯ " + funnyConsoleExtras.ANSI_RESET,
            " # ",
    };
    public static final String hash = " # ";
    public static final String blankSpace = "    ";
    public static final String blankTab = "\t";
    public static final String wall = "███";
    public static final String baseSpec = funnyConsoleExtras.ANSI_BLUE;
    public static final String playerSpec = funnyConsoleExtras.ANSI_YELLOW + funnyConsoleExtras.ANSI_BOLD;

}

package game.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by:
 * @author Shannon Jian Hong Chia
 * Modified by:
 *
 */

/**
 * The Maps class contains static fields representing map layouts for different in-game locations.
 */
public class Maps {

    /**
     * Represents the layout of an abandoned village.
     */

    public static String abandonedVillage = "Abandoned Village";
    public static List<String> ABANDONED_VILLAGE = Arrays.asList(
            "...........................................................",
            "...#######.................................................",
            "...#__.........................................+++++.......",
            "...#..___#...................................++++++++......",
            "...###.###................#######.................++++.....",
            "..........................#_____#................+++.......",
            "........~~................#_____#..................+.......",
            ".........~~~..............###_###..................+++.....",
            "...~~~~~~~~................................................",
            "....~~~~~.................................###..##..........",
            "~~~~~~~...................................#___..#..........",
            "~~~~~~....................................#..___#..........",
            "~~~~~~~~~.................................#######..........");

    /**
     * Represents the layout of a burial ground.
     */
    public static String burialGround = "Burial Ground";
    public static List<String> BURIAL_GROUND = Arrays.asList(
            "...........+++++++........~~~~~~++....~~",
            "...........++++++.........~~~~~~+.....~~",
            "............++++...........~~~~~......++",
            "............+.+.............~~~.......++",
            "..........++~~~.......................++",
            ".........+++~~~....#######...........+++",
            ".........++++~.....#_____#.........+++++",
            "..........+++......#_____#........++++++",
            "..........+++......###_###.......~~+++++",
            "..........~~.....................~~...++",
            "..........~~~..................++.......",
            "...........~~....~~~~~.........++.......",
            "......~~....++..~~~~~~~~~~~......~......",
            "....+~~~~..++++++++~~~~~~~~~....~~~.....",
            "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

    public static String ancientWoods = "Ancient Woods";
    public static List<String> ANCIENT_WOOD = Arrays.asList(
            "....+++..............................+++++++++....~~~....~~~",
            "+...+++..............................++++++++.....~~~.....~~",
            "++...............#######..............++++.........~~.......",
            "++...............#_____#...........................~~~......",
            "+................#_____#............................~~......",
            ".................###_###............~...............~~.....~",
            "...............................~.+++~~..............~~....~~",
            ".....................~........~~+++++...............~~~...~~",
            "....................~~~.........++++............~~~~~~~...~~",
            "....................~~~~.~~~~..........~........~~~~~~.....~",
            "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
            "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

    /**
     * Represents the layout of the Abxervyer battle map.
     */

    public static String abxeryyerBattle = "Abxeryyer Battle";
    public static List<String> ABXERYYER_BATTLE = Arrays.asList(
        "~~~~.......+++......~+++++..............",
        "~~~~.......+++.......+++++..............",
        "~~~++......+++........++++..............",
        "~~~++......++...........+..............+",
        "~~~~~~...........+.......~~~++........++",
        "~~~~~~..........++++....~~~~++++......++",
        "~~~~~~...........+++++++~~~~.++++.....++",
        "~~~~~..............++++++~~...+++.....++",
        "......................+++......++.....++",
        ".......................+~~............++",
        ".......................~~~~...........++",
        "........................~~++...........+",
        ".....++++...............+++++...........",
        ".....++++~..............+++++...........",
        "......+++~~.............++++...........~",
        ".......++..++++.......................~~",
        "...........+++++......................~~",
        "...........++++++.....................~~",
        "..........~~+++++......................~",
        ".........~~~~++++..................~~..~");

    public static String overgrownSanctuary = "Overgrown Sanctuary";
    public static List<String> OVERGROWN_SANCTUARY =Arrays.asList(
        "++++.....++++........++++~~~~~.......~~~..........",
        "++++......++.........++++~~~~.........~...........",
        "+++..................+++++~~.......+++............",
        "....................++++++......++++++............",
        "...................++++........++++++~~...........",
        "...................+++.........+++..~~~...........",
        "..................+++..........++...~~~...........",
        "~~~...........................~~~..~~~~...........",
        "~~~~............+++..........~~~~~~~~~~...........",
        "~~~~............+++.........~~~~~~~~~~~~..........",
        "++~..............+++.......+~~........~~..........",
        "+++..............+++......+++..........~~.........",
        "+++..............+++......+++..........~~.........",
        "~~~..............+++......+++..........~~~........",
        "~~~~.............+++......+++..........~~~........");

}
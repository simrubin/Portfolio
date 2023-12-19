package game;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.entity.Blacksmith;
import game.entity.Traveller;
import game.entity.enemy.Abxervyer;
import game.entity.Player;
import game.grounds.*;
import game.grounds.Void;
import game.items.Bloodberry;
import game.items.HealingVial;
import game.items.OldKey;
import game.items.RefreshingFlask;
import game.rune.DroppedRuneManager;
import game.rune.Rune;
import game.spawner.EldentreeGuardianSpawner;
import game.spawner.ForestKeeperSpawner;
import game.spawner.HollowSoldierSpawner;
import game.spawner.LivingBranchSpawner;
import game.spawner.RedWolfSpawner;
import game.spawner.WanderingUndeadSpawner;
import game.utils.FancyMessage;
import game.utils.Maps;
import game.weapon.Broadsword;
import game.weather.WeatherControl;
import game.weapon.GiantHammer;
import game.weapon.GreatKnife;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    private static final GroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor(), new Puddle(), new Void());

    public static void main(String[] args) {

        World world = new World(new Display());

        // Create map
        GameMap AbandonedVillageMap = new GameMap(groundFactory, Maps.ABANDONED_VILLAGE);
        world.addGameMap(AbandonedVillageMap);

        GameMap BurialGroundMap = new GameMap(groundFactory, Maps.BURIAL_GROUND);
        world.addGameMap(BurialGroundMap);

        GameMap AncientWoodMap = new GameMap(groundFactory, Maps.ANCIENT_WOOD);
        world.addGameMap(AncientWoodMap);

        GameMap AbxervyerBattleMap = new GameMap(groundFactory, Maps.ABXERYYER_BATTLE);
        world.addGameMap(AbxervyerBattleMap);

        GameMap OvergrownSanctuaryMap = new GameMap(groundFactory, Maps.OVERGROWN_SANCTUARY);
        world.addGameMap(OvergrownSanctuaryMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Location playerSpawnLocation = AbandonedVillageMap.at(29,5);
        Player player = new Player("The Abstracted One", '@', 150,200,playerSpawnLocation);
        world.addPlayer(player, playerSpawnLocation);

        AbandonedVillageMap.at(28,9).setGround(new Gate(BurialGroundMap, Maps.burialGround,19,9));
        AbandonedVillageMap.at(30,10).setGround(new Graveyard(new WanderingUndeadSpawner()));
        AbandonedVillageMap.at(42,3).setGround(new Graveyard(new WanderingUndeadSpawner()));
        AbandonedVillageMap.at(27,5).addActor(new Blacksmith());


        BurialGroundMap.at(25,10).setGround(new Graveyard(new HollowSoldierSpawner()));
        BurialGroundMap.at(19,9).setGround(new Gate(AbandonedVillageMap,Maps.abandonedVillage,28,9));
        BurialGroundMap.at(22,10).setGround(new Gate(AncientWoodMap,Maps.ancientWoods,19,7));

        AncientWoodMap.at(19,7).setGround(new Gate(BurialGroundMap,Maps.burialGround,22,10));
        AncientWoodMap.at(10,8).setGround(new EmptyHut(new ForestKeeperSpawner()));
        AncientWoodMap.at(8,5).setGround(new Bush(new RedWolfSpawner()));
        AncientWoodMap.at(20,3).addActor(new Traveller());
        AncientWoodMap.at(17,11).setGround(new Gate(AbxervyerBattleMap,Maps.abxeryyerBattle,22,10));

        AbxervyerBattleMap.at(17,11).setGround(new EmptyHut(new ForestKeeperSpawner()));
        AbxervyerBattleMap.at(10,14).setGround(new EmptyHut(new ForestKeeperSpawner()));
        AbxervyerBattleMap.at(7,2).setGround(new EmptyHut(new ForestKeeperSpawner()));
        AbxervyerBattleMap.at(13,5).setGround(new EmptyHut(new ForestKeeperSpawner()));
        AbxervyerBattleMap.at(5,6).setGround(new Bush(new RedWolfSpawner()));
        AbxervyerBattleMap.at(10,10).setGround(new Bush(new RedWolfSpawner()));
        AbxervyerBattleMap.at(15,1).setGround(new Bush(new RedWolfSpawner()));
        AbxervyerBattleMap.at(19,8).addActor(new Abxervyer(new WeatherControl(),new ArrayList<>(
            Arrays.asList(AncientWoodMap,OvergrownSanctuaryMap))));

        OvergrownSanctuaryMap.at(10,8).setGround((new Gate(AbxervyerBattleMap,Maps.abxeryyerBattle,22,10)));
        OvergrownSanctuaryMap.at(17,11).setGround(new Graveyard(new HollowSoldierSpawner()));
        OvergrownSanctuaryMap.at(10,14).setGround(new Graveyard(new HollowSoldierSpawner()));
        OvergrownSanctuaryMap.at(7,2).setGround(new EmptyHut(new EldentreeGuardianSpawner()));
        OvergrownSanctuaryMap.at(13,5).setGround(new EmptyHut(new EldentreeGuardianSpawner()));
        OvergrownSanctuaryMap.at(5,6).setGround(new Bush(new LivingBranchSpawner()));
        OvergrownSanctuaryMap.at(10,10).setGround(new Bush(new LivingBranchSpawner()));
        OvergrownSanctuaryMap.at(15,1).setGround(new Bush(new LivingBranchSpawner()));

        // For testing purpose
//        player.addItemToInventory(new Bloodberry());
//        player.addItemToInventory(new HealingVial());
//        player.addItemToInventory(new GiantHammer());
//        player.addBalance(10000);
//        gameMap.at(23, 10).addActor(new WanderingUndead());
//        gameMap.at(22,8).addActor(new HollowSoldier());
//        gameMap.at(29,7).addItem(new OldKey());
//        gameMap.at(29,8).addItem(new RefreshingFlask());
//        gameMap.at(29,9).addItem(new HealingVial());
//        gameMap.at(26, 8).addItem(new Bloodberry());

        // Test case for upgrade (healing vial)
//        player.hurt(149);
//        player.addItemToInventory(new HealingVial());
//        player.addItemToInventory(new HealingVial());
//        player.addBalance(1000);

        // Testing money despawn
//        Rune rune = new Rune(696);
//        AbandonedVillageMap.at(30,6).addItem(rune);
//        DroppedRuneManager.getInstance().register(rune,AbandonedVillageMap.at(30,6));
//        player.addItemToInventory(new Rune(9000));

        // Test case for selling stuff
//        world.addPlayer(player, AncientWoodMap.at(19, 3));
//        player.addItemToInventory(new HealingVial());
//        player.addItemToInventory(new Broadsword());
//        player.addItemToInventory(new RefreshingFlask());
//        player.addItemToInventory(new Bloodberry());
//        player.addItemToInventory(new Rune(6969));
//        player.addItemToInventory(new Rune(12));

//        player.addItemToInventory(new Broadsword());
//        player.addBalance(1000);

        // Test for req 4
//        world.addPlayer(player,AbxervyerBattleMap.at(18, 9));
//        GreatKnife greatKnife = new GreatKnife();
//        player.addItemToInventory(greatKnife);
//        player.addItemToInventory(new OldKey());
//        player.addItemToInventory(new GiantHammer());

        // Abxervyer monologue testing
//        AbandonedVillageMap.at(30,5).addActor(new Abxervyer(new WeatherControl(), new ArrayList<>(Arrays.asList(AncientWoodMap,OvergrownSanctuaryMap))));
//        player.addItemToInventory(new GiantHammer());


        world.run();
    }
}

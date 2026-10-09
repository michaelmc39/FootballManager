import java.util.*;

//set up the player class
class Player {
    String name;
    String position;
    int attack;
    int defense;

    Player(String name, String position, int attack, int defense) {
        this.name = name;
        this.position = position;
        this.attack = attack;
        this.defense = defense;


    }
}

class Team {
    String name;
    List<Player> players = new ArrayList<>();

    Team(String name) {
        this.name = name;
    }

    void addPlayer(Player p) {
        players.add(p);
    }


    double attackStrength() {
        if (players.isEmpty()) return 0;
        int total = 0;
        for (Player p : players) {
            total += p.attack;
        }
        return (double) total / players.size();
    }

    double defenceStrength() {
        if (players.isEmpty()) return 0;
        int total = 0;
        for (Player p : players) {
            total += p.defense;
        }
        return (double) total / players.size();
    }
}

class Match{
private static final Random RNG = new Random();

static int[] play(Team home, Team away) {
    int homeGoals = goals(home.attackStrength() + 3, away.defenceStrength());
    int awayGoals = goals(away.attackStrength(), home.defenceStrength());
    return new int[]{homeGoals, awayGoals};
}

private static int goals(double attack, double opposingDefense) {
    double chance = attack / (attack +opposingDefense) * 0.35;
    int goals = 0;
    for(int i = 0; i < 6; i++) {
        if (RNG.nextDouble() < chance) goals++;
    }
    return goals;
}
}

public class Main {
    public static void main(String[] args ) {
        Team city = new Team("Belfast City");
        city.addPlayer(new Player("Christopher Doyle", "ST", 80, 28));
        city.addPlayer(new Player("Tom McShane", "MF", 74, 70));
        city.addPlayer(new Player("Lewis McGinn", "DF", 45, 80));

        Team town = new Team("Leatherhead Town");
        town.addPlayer(new Player("Paul Burns", "ST", 70, 30));
        town.addPlayer(new Player("Ben Shaw", "MF", 68, 66));
        town.addPlayer(new Player("Seamus Mallon", "DF", 40, 72));

        int[] result = Match.play(city, town);
        System.out.println(city.name + " " + result[0] + " - " + result[1] + " " + town.name);
    }
}
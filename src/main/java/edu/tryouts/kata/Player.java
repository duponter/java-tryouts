package edu.tryouts.kata;

import java.util.Arrays;
import java.util.List;

/**
 * Class Player.
 *
 * @author Erwin Dupont
 * @since 2014-05-21
 */
public class Player {
    public static final List<String> pointsDescription = Arrays.asList("love", "fifteen", "thirty", "forty");

    private int score;

    public int getScore() {
        return score;
    }

    String name;

    public String getName() {
        return name;
    }

    public void winBall() {
        this.score = this.score + 1;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getScoreDescription() {
        return pointsDescription.get(score);
    }
}

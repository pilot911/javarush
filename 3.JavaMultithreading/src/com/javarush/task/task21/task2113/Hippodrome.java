package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException {

        List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("a", 3, 0));
        horses.add(new Horse("b", 3, 0));
        horses.add(new Horse("c", 3, 0));

        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public Horse getWinner() {

        Horse winner = horses.get(0);

        for (Horse hors : horses) {
            if (winner.getDistance() < hors.getDistance()) {
                winner = hors;
            }
        }

        return winner;
    }

    public void run() throws InterruptedException {
        for (int i = 1; i < 101; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse hors : horses) {
            hors.move();
        }
    }

    public void print() {
        for (Horse hors : horses) {
            hors.print();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}

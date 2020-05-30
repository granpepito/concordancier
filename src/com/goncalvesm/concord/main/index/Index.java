package com.goncalvesm.concord.main.index;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class Index {

    private String mot;
    private CopyOnWriteArrayList<Integer> positions;

    public Index(String mot) {
        this.mot = mot;
        this.positions = new CopyOnWriteArrayList<>();
    }

    public synchronized void add(Integer position) {
        this.positions.add(position);
    }

    public synchronized int getOccurences() {
        return this.positions.size();
    }

    public ArrayList<Integer> getPositions() {
        return new ArrayList<>(positions);
    }

}

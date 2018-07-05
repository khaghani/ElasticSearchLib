/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amir
 */
public class Hits {

    private int total;
    private double max_score;
    public List<HitsObject> hits = new ArrayList<>();
    /////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    ///////////////////////

    public double getMax_score() {
        return max_score;
    }

    public void setMax_score(double max_score) {
        this.max_score = max_score;
    }

}

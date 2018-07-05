/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author amir
 */
public class Shards {

    private int total;
    private int successful;
    private int skipped;
    private int failed;
    /////////////////////////////////////////////////////////////////////

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    ///////////////////////

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }
    ///////////////////////

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }
    ///////////////////////

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

}

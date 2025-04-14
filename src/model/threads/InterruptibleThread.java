package model.threads;

public abstract class InterruptibleThread extends Thread {
    protected boolean isRunning = true;

    public void pause() {
        isRunning = false;
    }

    public void play() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

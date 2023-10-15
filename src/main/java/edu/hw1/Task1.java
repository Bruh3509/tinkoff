package edu.hw1;

public final class Task1 {
    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String video) {
        String[] videoArr = video.split(":");
        int minutes = Integer.parseInt(videoArr[0]);
        int seconds = Integer.parseInt(videoArr[1]);

        if (seconds >= 60 || seconds < 0) {
            return -1;
        }
        if (minutes < 0) {
            return -1;
        }

        return minutes * 60 + seconds;
    }

    private Task1() {
    }
}

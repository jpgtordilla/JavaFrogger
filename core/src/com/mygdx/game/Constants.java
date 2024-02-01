package com.mygdx.game;

public class Constants {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 669;
    public static final int ROAD_Y = 25;
    public static final int ROAD_HEIGHT = 369;
    public static final int ROAD_MARGIN = 74;
    public static final int RIVER_Y = 400;
    public static final int RIVER_HEIGHT = 200;
    public static final int FROG_WIDTH = 60;
    public static final int FROG_HEIGHT = 74;
    public static final int FROG_JUMP = 50;
    public static final int FROG_START_Y = 10;
    public static final int CAR_WIDTH = 150;
    public static final int CAR_HEIGHT = 66;
    public static final int CAR_SPEED = 200;
    public static final int TOP_CAR_OFFSET = ROAD_MARGIN * 3;
    public static final int BOT_CAR_OFFSET = ROAD_MARGIN * 2;
    public static final int BOAT_WIDTH = 200;
    public static final int BOAT_HEIGHT = 70;
    public static final int BOAT_SPEED = 200;
    public static final long MOVE_INTERVAL = 350000000;
    public static final long SPAWN_INTERVAL = 2100000000;
    public static int[] returnDimensions(String vehicle) {
        int[] dimensionArr;
        switch(vehicle) {
            case "car":
                dimensionArr = new int[]{CAR_WIDTH, CAR_HEIGHT};
                return dimensionArr;
            case "boat":
                dimensionArr = new int[]{BOAT_WIDTH, BOAT_HEIGHT};
                return dimensionArr;
            default:
                return new int[]{0, 0};
        }
    }
}

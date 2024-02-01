package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Vehicle {

    public Texture vehicleImage;
    public Rectangle vehicleRectangle;
    public static Array<Vehicle> vehicleArray = new Array<>();
    private String vehicleType;
    private String vehicleOrientation; // which way the vehicle is FACING ("right" or "left")
    public static long lastSpawnTime;
    public int vehicleSpeed;

    public Vehicle(String type) {
        vehicleType = type;
    }

    public static void createVehicles() {
        // generate a car and a boat at the beginning
        Vehicle car = new Vehicle("car");
        Vehicle boat = new Vehicle("boat");
        car.createVehicle();
        boat.createVehicle();
    }
    private void createVehicle() {
        if (vehicleType.equals("car")) {
            // two car textures, representing cars coming from the left or the right
            Texture carOneImage = new Texture(Gdx.files.local("carOne.png"));
            Texture carTwoImage = new Texture(Gdx.files.local("carTwo.png"));
            Texture[] arr = {carOneImage, carTwoImage};
            // randomly generate a right facing or left facing car
            int randInd = getRandom(arr);
            // simple code to assign instance variables (since only two options)
            if (randInd == 0) {
                vehicleOrientation = "right";
                vehicleImage = carOneImage;
            } else {
                vehicleOrientation = "left";
                vehicleImage = carTwoImage;
            }
            vehicleSpeed = Constants.CAR_SPEED;
            spawnVehicle(this);
        } else {
            Texture boatImage = new Texture(Gdx.files.local("boat.png"));
            vehicleOrientation = "right";
            vehicleImage = boatImage;
            vehicleSpeed = Constants.BOAT_SPEED;
            spawnVehicle(this);
        }
    }

    private void spawnVehicle(Vehicle vehicleToSpawn) {
        vehicleToSpawn.vehicleRectangle = new Rectangle();
        Rectangle currRect = vehicleToSpawn.vehicleRectangle; // current vehicle rectangle
        // get dimensions
        int[] dims = Constants.returnDimensions(vehicleType);
        currRect.width = dims[0];
        currRect.height = dims[1];
        // set x spawn
        if (vehicleOrientation.equals("right")) {
            currRect.x = -currRect.width;
        } else {
            currRect.x = Constants.SCREEN_WIDTH;
        }
        // set y spawn
        // if is boat: random within RIVER_Y and RIVER_Y + RIVER_HEIGHT
        // if is car: random within ROAD_Y + ROAD_MARGIN and ROAD_Y + ROAD_HEIGHT - ROAD_MARGIN
        if (vehicleType.equals("boat")) {
            currRect.y = MathUtils.random(Constants.RIVER_Y, Constants.RIVER_Y +
                    Constants.RIVER_HEIGHT - currRect.height);
        }
        else {
            if (vehicleOrientation.equals("left")) {
                // replace with final int
                currRect.y = MathUtils.random(Constants.ROAD_Y + Constants.TOP_CAR_OFFSET, Constants.ROAD_Y +
                        Constants.ROAD_HEIGHT - Constants.ROAD_MARGIN - currRect.height);
            } else {
                // replace with final int
                currRect.y = MathUtils.random(Constants.ROAD_Y + Constants.ROAD_MARGIN, Constants.ROAD_Y +
                        Constants.BOT_CAR_OFFSET - currRect.height);
            }
        }
        // keep spawning simple: two random vehicles are spawned every time interval
        vehicleArray.add(vehicleToSpawn);
        lastSpawnTime = TimeUtils.nanoTime();
    }

    public static void moveVehicles() {
        // Iterator use from: https://libgdx.com/wiki/start/a-simple-game
        for (Iterator<Vehicle> iter = vehicleArray.iterator(); iter.hasNext();) {
            Vehicle vehicle = iter.next(); // current vehicle
            Rectangle currRect = vehicle.vehicleRectangle; // current vehicle rectangle
            if (vehicle.vehicleOrientation.equals("right")) {
                if (vehicle.vehicleType.equals("boat")) {
                    // move right at boat speed
                    currRect.x += Constants.BOAT_SPEED * Gdx.graphics.getDeltaTime();
                } else {
                    currRect.x += Constants.CAR_SPEED * Gdx.graphics.getDeltaTime();
                }
            } else {
                currRect.x -= Constants.CAR_SPEED * Gdx.graphics.getDeltaTime(); // fix code duplication
            }
            // handle removal
            if (vehicle.vehicleOrientation.equals("right") &&
                    currRect.x > Constants.SCREEN_WIDTH) {
                // if facing right and farther than the right screen bound
                iter.remove();
            } else if (vehicle.vehicleOrientation.equals("left") &&
                    currRect.x < -currRect.width) {
                // if facing left and farther than the left screen bound
                iter.remove();
            }
            // COLLISION
            if (currRect.overlaps(Frog.frog)) {
                // end the game
                Gdx.app.exit();
            }
        }

    }

    public static int getRandom(Texture[] arr) {
        return MathUtils.random(0, 1);
    }
}

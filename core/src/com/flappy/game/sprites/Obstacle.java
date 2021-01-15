package com.flappy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {


    public static final int UFO_WIDTH=52;
    public static final int ALIEN_WIDTH=52;
//    private static final int FLUCTUATION=130;
   private static final int UFO_GAP=400;
    private static final int ALIEN_GAP=300;
//    private static final int LOWEST_OPENING =120;
    private Rectangle boundsUFO, boundsAlien;
    private Texture alien,UFO;
    private Vector2 posAlien,posUFO;
    private Random rand;



    public Obstacle(float x)
    {
        UFO = new Texture("UFO.png");
        alien = new Texture("alient.png");
        rand = new Random();

        posUFO = new Vector2(UFO_GAP +x ,rand.nextInt(50)+10);//top tube
        posAlien = new Vector2(ALIEN_GAP+ x,rand.nextInt(50)+10);//bottom tube

        boundsUFO = new Rectangle(posUFO.x,posUFO.y,UFO.getWidth(),UFO.getHeight());
        boundsAlien = new Rectangle(posAlien.x,posAlien.y,alien.getWidth(),alien.getHeight());
    }

    public void reposition(float x){
        posUFO.set(UFO_GAP + x,rand.nextInt(50)+10);
        posAlien.set(ALIEN_GAP + x,rand.nextInt(50)+10);
        boundsUFO.setPosition(posUFO.x,posUFO.y);
        boundsAlien.setPosition(posAlien.x,posAlien.y);

    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(boundsUFO) || player.overlaps(boundsAlien);
    }

    public Texture getAlien() {
        return alien;
    }

    public Texture getUFO() {
        return UFO;
    }

    public Vector2 getPosAlien() {
        return posAlien;
    }

    public Vector2 getPosUFO() {
        return posUFO;
    }

    public void dispose()
    {
        alien.dispose();
        UFO.dispose();
    }
}

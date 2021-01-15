package com.flappy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Rider {
    public Vector3 getPosition() {
        return position;
    }

    public Texture getRider() {
        return rider;
    }

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 200;
    private  Vector3 position;
    private Vector3 velocity;
    private Texture rider;
    private Rectangle bounds;

    public Rider(int x, int y)
    {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        rider = new Texture("motor.png");
        bounds = new Rectangle(x,y,rider.getWidth(),rider.getHeight());
    }

    public void update(float dt)
    {   if(position.y >0)
            velocity.add(0,GRAVITY,0);
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT*dt,velocity.y,0);
            if(position.y <0)
                position.y=0;
            if(position.y>300)
                position.y=250;
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public void jump()
    {
        velocity.y = 500;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose()
    {
        rider.dispose();
    }
}

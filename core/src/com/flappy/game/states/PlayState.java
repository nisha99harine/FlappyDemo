package com.flappy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappy.game.FlappyDemo;
import com.flappy.game.sprites.Obstacle;
import com.flappy.game.sprites.Rider;

public class PlayState extends State
{   private static final int GROUND_Y_OFFSET = -85;
    private static final int UFO_SPACING =700;
    private static final int Alien_SPACING =400;
    private static final int UFO_COUNT =2;
    private static final int ALIEN_COUNT =2;

    private Rider rider;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private Array<Obstacle> obstacle;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        rider = new Rider(10,25);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        bg = new Texture("bg.png");

        obstacle = new Array<Obstacle>();
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        for(int i=1;i<=UFO_COUNT;i++){
            obstacle.add(new Obstacle(i * (UFO_SPACING + Obstacle.UFO_WIDTH)));
        }

        for(int i=1;i<=ALIEN_COUNT;i++){
            obstacle.add(new Obstacle(i * (Alien_SPACING + Obstacle.ALIEN_WIDTH)));
        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            rider.jump();
        }
    }
    @Override
    public void update(float dt) {
        handleInput();
        updateGround();

        rider.update(dt);
        cam.position.x = rider.getPosition().x+80;
        for(int i = 0; i < obstacle.size; i++)
        {   Obstacle obs = obstacle.get(i);
            if(cam.position.x-(cam.viewportWidth/2)> obs.getPosUFO().x+ obs.getUFO().getWidth())
            {
                obs.reposition((obs.getPosUFO().x)+((Obstacle.UFO_WIDTH+UFO_SPACING)* UFO_COUNT));
            }

            if(cam.position.x-(cam.viewportWidth/2)> obs.getPosAlien().x+ obs.getAlien().getWidth())
            {
                obs.reposition((obs.getPosAlien().x)+((Obstacle.ALIEN_WIDTH+Alien_SPACING)* ALIEN_COUNT));
            }

            if(obs.collides(rider.getBounds()))
                gsm.set((new PlayState(gsm)));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
    sb.begin();
    sb.draw(bg,cam.position.x - (cam.viewportWidth/2),0);
    sb.draw(rider.getRider(), rider.getPosition().x,rider.getPosition().y);

        for (Obstacle obstacle: obstacle) {
            sb.draw(obstacle.getAlien(), obstacle.getPosAlien().x, obstacle.getPosAlien().y);
            sb.draw(obstacle.getUFO(), obstacle.getPosUFO().x, obstacle.getPosUFO().y);
        }
    sb.draw(ground, groundPos1.x, groundPos1.y);
    sb.draw(ground, groundPos2.x, groundPos2.y);
    sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        rider.dispose();
        for(Obstacle obstacle: obstacle)
            obstacle.dispose();
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
//we have to change the obstacle position and at play state..
// it is in random we have to maintain it in a single position
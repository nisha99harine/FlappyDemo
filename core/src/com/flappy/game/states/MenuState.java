package com.flappy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappy.game.FlappyDemo;

public class MenuState  extends State{

    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("Space-Backgrounds.png");
        playBtn = new Texture("green-87.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.setCursorPosition();)
        {
        gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, FlappyDemo.WIDTH,FlappyDemo.HEIGHT);
        sb.draw(playBtn,(FlappyDemo.WIDTH/2) - (playBtn.getWidth()/2),FlappyDemo.HEIGHT /2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}

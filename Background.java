package ru.foxit.grayfox;

import java.awt.*;

//////new//////
public class Background implements GameObject {
    private Color color;
    private float time;
    private static final float AMPLITUDE = 255f / 2f;

    @Override
    public void render(GameCanvas gameCanvas, Graphics g){
        gameCanvas.setBackground(color);
    }

    @Override
    public void update(GameCanvas gameCanvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3));
        color = new Color(red, green, blue);
    }
}
//////new//////
package ru.vsu.cs.Shemenev;

import java.awt.*;


public class Rounds {
    public static void paintRounds(Graphics2D g2d, int width, int height, int levelCount) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(color);
        paintRoundLevel(g2d, 0, 0, width, height, levelCount - 1);
    }
    private static void paintRoundLevel(Graphics2D g2d, int x,int y, int width, int height, int level){
        if(level>0){
            paintRoundLevel(g2d, x+3*width/8, y, width/4, height/4,level-1);
            paintRoundLevel(g2d, x, y+3*height/8, width/4, height/4,level-1);
            paintRoundLevel(g2d, x+3*width/4, y+3*height/8, width/4, height/4,level-1);
            paintRoundLevel(g2d, x+3*width/8, y+3*height/4, width/4, height/4,level-1);
        }
        else {
            g2d.drawOval(x+3*width/8,y+3*height/8,width/4,height/4);
            g2d.drawOval(x+14*width/32,y,width/8,height/8);
            g2d.drawOval(x+7*width/8,y+15*height/32,width/8,height/8);
            g2d.drawOval(x+14*width/32,y+7*height/8,width/8,height/8);
            g2d.drawOval(x,y+15*height/32,width/8,height/8);
        }
        g2d.drawOval(x+3*width/8,y+3*height/8,width/4,height/4);
    }
}

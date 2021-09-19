package esoterum.world.blocks.binary;

import arc.graphics.g2d.*;
import esoterum.content.*;
import mindustry.graphics.*;

public class ConfigOutputWire extends ConfigWire{
    public ConfigOutputWire(String name){
        super(name);

        emitAllDirections = true;
        letters[1] = "F";
    }

    public class ConfigOutputWireBuild extends ConfigWireBuild{
        public boolean signal(){
            return getSignal(nb[0]);
        }

        @Override
        public boolean signalFront(){
            return lastSignal && sides[1];
        }

        @Override
        public boolean signalLeft(){
            return lastSignal && sides[0];
        }

        @Override
        public boolean signalRight(){
            return lastSignal && sides[2];
        }

        @Override
        public void drawSelect(){
            for(int i = 1; i < 4; i++){
                int j = i == 1 ? 0 : i == 3 ? 1 : i; //Why is the order of nb [back, left, right, front]
                if(nb[i] != null && sides[j]) Drawf.arrow(x, y, nb[i].x, nb[i].y, 2f, 2f, Pal.accent);
            }
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, lastSignal ? 1f : 0f);
            for(int i = 0; i < 3; i++){
                if(!sides[i])continue;
                float r = i == 1 ? 180 : 0;
                Draw.rect(connectionRegion, x, y, (90f + 90f * i) + rotdeg() + r);
            }
            Draw.rect(connectionRegion, x, y, rotdeg() + 180);
            Draw.rect(topRegion, x, y, rotdeg());
        }
    }
}

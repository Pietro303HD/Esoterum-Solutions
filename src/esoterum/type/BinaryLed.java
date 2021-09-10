package esoterum.type;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import mindustry.graphics.*;

public class BinaryLed extends BinaryBlock {
    public BinaryLed(String name){
        super(name);
        rotate = true;
    }

    public class BinaryLedBuild extends BinaryBuild {

        @Override
        public void updateTile(){
            super.updateTile();
            lastSignal = nextSignal;
            nextSignal = signal();
            signalOverride = false;
            // sBack() | sLeft() | sRight()
        }

        @Override
        public boolean signal(){
            return false;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            for(int i = 0; i < 3; i++){
                if(!inputs[i])continue;
                Draw.color(Color.white, Color.green, lastSignal ? 1f : 0f);
                Draw.rect(connectionRegion, x, y, (90f + 90f * i) + rotdeg() );
            }
            Draw.color(getColor());
            Draw.rect(topRegion, x, y, rotdeg());
        }

        public Color getColor(){
            return Color.red
        }

    }
}

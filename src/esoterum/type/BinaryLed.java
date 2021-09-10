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
            Draw.color(Color.red);
            Draw.rect(topRegion, x, y, rotdeg());
            Draw.rect(connectionRegion, x, y, rotdeg() );
        }

    }
}

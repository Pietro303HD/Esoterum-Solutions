package esoterum.type;

import mindustry.graphics.*;

public class Led extends BinaryBlock {
    public BinaryAcceptor(String name){
        super(name);
        rotate = true;
    }

    public class LedBuild extends BinaryBuild {

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
            return false
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(Color.white);
            Draw.rect(topRegion, x, y, rotdeg());
            Draw.rect(connectionRegion, x, y, rotdeg() );
        }

    }
}

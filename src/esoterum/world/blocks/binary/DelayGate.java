package esoterum.world.blocks.binary;

import arc.graphics.g2d.*;
import arc.util.*;
import esoterum.content.*;

public class DelayGate extends TimedBufferGate{
    public DelayGate(String name){
        super(name);
    }

    public class WaitGateBuild extends DelayGateBuild{
        @Override
        public void updateTile(){
            boolean s = signal();
            nextSignal = s;
            Time.run(delay(), () -> {
                if(!isValid()) return;
                lastSignal = s;
            });
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, lastSignal ? 1f : 0f);
            Draw.rect(connectionRegion, x, y, rotdeg());
            Draw.rect(topRegion, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, nextSignal ? 1f : 0f);
            Draw.rect(clock, x, y);
        }
    }
}

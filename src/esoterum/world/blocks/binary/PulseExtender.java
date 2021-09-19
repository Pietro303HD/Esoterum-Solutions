package esoterum.world.blocks.binary;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import esoterum.content.*;

public class PulseExtender extends TimedBufferGate{
    public PulseExtender(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();

        connectionRegion = Core.atlas.find("eso-input-connection");
    }

    public class PulseExtenderBuild extends DelayGateBuild{
        @Override
        public void updateTile(){
            lastSignal = delayTimer > 0f || signal();
            nextSignal = signal();

            if(signal()){
                delayTimer = delay();
            }else{
                delayTimer -= Time.delta;
            }
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, nextSignal ? 1f : 0f);
            Draw.rect(connectionRegion, x, y, rotdeg());
            Draw.rect(topRegion, x, y, rotdeg());
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, lastSignal ? 1f : 0f);
            Draw.rect(timerRegion, x, y, rotdeg());
        }
    }
}

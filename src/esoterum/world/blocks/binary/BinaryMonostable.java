package esoterum.world.blocks.binary;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import esoterum.content.*;

public class BinaryMonostable extends TimedBufferGate{
    public TextureRegion sig, bar;

    public BinaryMonostable(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        bar = Core.atlas.find(name + "-bar");
    }

    @Override
    protected TextureRegion[] icons(){
        return new TextureRegion[]{
            region,
            topRegion,
            timerRegion,
            bar,
            connectionRegion
        };
    }

    public class BinaryMonostableBuild extends DelayGateBuild{
        @Override
        public void updateTile(){
            lastSignal = delayTimer > 0 && nextSignal;
            nextSignal = signal();

            if(signal()){
                delayTimer -= Time.delta;
            }else{
                delayTimer = delay();
            }
        }

        @Override
        public void draw(){
            super.draw();
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, (delayTimer > 0 && signal()) || !signal() ? 0f : 1f);
            Draw.rect(bar, x, y, rotdeg());
        }
    }
}

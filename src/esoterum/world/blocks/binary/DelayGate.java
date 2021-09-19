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
    }
}

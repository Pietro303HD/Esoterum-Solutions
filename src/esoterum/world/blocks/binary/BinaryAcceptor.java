package esoterum.world.blocks.binary;

import arc.graphics.g2d.*;
import esoterum.content.*;
import mindustry.graphics.*;

public class BinaryAcceptor extends BinaryBlock{
    public BinaryAcceptor(String name){
        super(name);
        rotate = true;
        drawArrow = true;
        emits = true;
    }

    public class BinaryAcceptorBuild extends BinaryBuild{

        @Override
        public void updateTile(){
            lastSignal = nextSignal | getSignal(nb[0]);
            nextSignal = signal();
        }

        @Override
        public void drawSelect(){
            if(rotate && nb[3] != null)Drawf.arrow(x, y, nb[3].x, nb[3].y, 2f, 2f, Pal.accent);
        }

        @Override
        public boolean signal(){
            return getSignal(nb[1]) | getSignal(nb[2]);
        }

        @Override
        public boolean signalFront(){
            return (nb[0] != null ? nb[0].rotation == rotation || !nb[0].block.rotate ? getSignal(nb[0]) : lastSignal : lastSignal) | nextSignal;
        }

        @Override
        // this hurts me even more
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, lastSignal ? 1f : 0f);
            if(drawConnection){
                for(BinaryBuild b: nb){
                    if(b == null || b.team != team) continue;
                    if(!b.block.rotate || (b.block instanceof BinaryAcceptor a && a.emitAllDirections) || (b.front() == this || b.back() == this) || front() == b){
                        if(!(b.back() == this && front() != b) || !b.block.rotate){
                            Draw.rect(connectionRegion, x, y, relativeTo(b) * 90);
                        }
                    }
                }
            }
            Draw.rect(topRegion, x, y, rotdeg());
        }
    }
}

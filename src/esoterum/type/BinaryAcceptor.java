package esoterum.type;

import mindustry.graphics.*;

public class BinaryAcceptor extends BinaryBlock {
    public BinaryAcceptor(String name){
        super(name);
        rotate = true;
        drawArrow = true;
        emits = true;
    }

    public class BinaryAcceptorBuild extends BinaryBuild {

        @Override
        public void updateTile(){
            lastSignal = nextSignal | getSignal(nb[0]);
            nextSignal = signal();
        }

        @Override
        public void drawSelect() {
            if(rotate){
                if(front() != null){
                    Drawf.arrow(x, y, front().x, front().y, 2f, 2f, Pal.accent);
                }
            }
        }

        @Override
        public boolean signal(){
            return getSignal(nb[1]) | getSignal(nb[2]);
        }

        @Override
        public boolean signalFront() {
            return (nb[0] != null ? nb[0].rotation == rotation || !nb[0].block.rotate ? getSignal(nb[0]) : lastSignal : lastSignal) | nextSignal;
        }
    }
}

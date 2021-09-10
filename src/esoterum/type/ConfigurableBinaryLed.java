package esoterum.type;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.graphics.*;

public class ConfigurableBinaryLed extends BinaryLed {
    public boolean[] inputs = {false, true, false};
    public ConfigurableBinaryLed(String name){
        super(name);
        rotate = true;
        emits = false;
        drawArrow = false;
    }

    @Override
    protected TextureRegion[] icons() {
        return new TextureRegion[]{
            region,
            topRegion,
            Core.atlas.find("eso-gate-connections")
        };
    }

    public class ConfigurableBinaryLedBuild extends BinaryLedBuild {

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
            for(int i = 0; i < 1; i++){
                if(!inputs[i])continue;
                Draw.color(Color.white);
                Draw.rect(connectionRegion, x, y, (90f + 90f * i) + rotdeg());
            }
            Draw.color(getColor());
            Draw.rect(topRegion, x, y, rotdeg());
        }

        public Color getColor(){
            int a = sBack() ? 1 : 0;
            return new Color(1, 0, 0, a);
        }

    }
}

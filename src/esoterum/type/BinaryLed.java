package esoterum.type;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.*;
import arc.util.io.*;
import arc.struct.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.input.*;
import static mindustry.Vars.*;

public class BinaryLed extends BinaryAcceptor {
    public boolean[] inputs = {true, true, true};
    public BinaryLed(String name){
        super(name);
        rotate = true;
        drawArrow = false;
        emits = false;
        drawConnection = false;
        configurable = true;

        config(Color.class, (BinaryLedBuild tile, Color value) -> tile.color = value);
    }

    @Override
    protected TextureRegion[] icons() {
        return new TextureRegion[]{
            region,
            topRegion,
            Core.atlas.find("eso-gate-connections")
        };
    }

    public class BinaryLedBuild extends BinaryAcceptorBuild {
        public Color color = Color.white;

        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.pencil, () -> {
                ui.picker.show(Tmp.c1.set(color), false, res -> configure(res.rgba()));
                deselect();
            }).size(40f);
        }

        @Override
        public void updateTile(){
            super.updateTile();
            lastSignal = nextSignal;
            nextSignal = signal();
        }

        @Override
        public boolean signal(){
            return false;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(new Color(getSignal(nb[0]) ? color.r : 0f, getSignal(nb[1]) ? color.g : 0f, getSignal(nb[2]) ? color.b : 0f));
            Draw.rect(topRegion, x, y, rotdeg());
        }

        @Override
        public Color config(){
            return color;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.i(color);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            color = read.i();
        }

    }
}

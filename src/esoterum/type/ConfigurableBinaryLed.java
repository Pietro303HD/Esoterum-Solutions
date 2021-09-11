package esoterum.type;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.input.*;
import static mindustry.Vars.*;

import esoterum.content.EsoVars;

public class ConfigurableBinaryLed extends BinaryLed {
    public boolean[] inputs = {false, true, false};
    public ConfigurableBinaryLed(String name){
        super(name);
        rotate = true;
        emits = false;
        drawArrow = false;
        configurable = true;

        config(Integer.class, (ConfigurableBinaryLedBuild tile, Integer value) -> tile.color = value);
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
        public int color = EsoVars.connectionColor.rgba();

        @Override
        public void updateTile(){
            super.updateTile();
            lastSignal = nextSignal;
            nextSignal = signal();
            signalOverride = false;
            // sBack() | sLeft() | sRight()
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.pencil, () -> {
                ui.picker.show(Tmp.c1.set(color), false, res -> configure(res.rgba()));
                deselect();
            }).size(40f);
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

        public Color getColor() {
            return Tmp.c1.set(color);
        }

        @Override
        public Integer config(){
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

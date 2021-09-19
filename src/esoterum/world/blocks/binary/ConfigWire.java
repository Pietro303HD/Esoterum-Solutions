package esoterum.world.blocks.binary;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.io.*;
import esoterum.content.*;
import mindustry.gen.*;
import mindustry.ui.*;

public class ConfigWire extends BinaryAcceptor{
    protected String[] letters = {"L", "B", "R"};

    public ConfigWire(String name){
        super(name);
        configurable = true;
        saveConfig = true;

        config(byte[].class, (ConfigWireBuild b, byte[] i) -> {
           b.sides = new boolean[]{
               i[0] == 1,
               i[1] == 1,
               i[2] == 1
           };
        });

        config(Integer.class, (ConfigWireBuild b, Integer i) -> {
            b.sides[i] = !b.sides[i];
        });
    }

    @Override
    public void load(){
        super.load();
        connectionRegion = Core.atlas.find("eso-connection-large");
    }

    @Override
    protected TextureRegion[] icons(){
        return new TextureRegion[]{
            region,
            topRegion,
            Core.atlas.find("eso-connection-across")
        };
    }

    public class ConfigWireBuild extends BinaryAcceptorBuild{
        public boolean[] sides = new boolean[]{false, true, false};

        @Override
        public void updateTile(){
            lastSignal = signal();
            nextSignal = signal();
        }

        @Override
        public boolean signal(){
            boolean
                left = sides[0] && getSignal(nb[1]),
                back = sides[1] && getSignal(nb[0]),
                right = sides[2] && getSignal(nb[2]);
            return left || back || right;
        }

        @Override
        public boolean signalFront(){
            return lastSignal;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(EsoVars.connectionOffColor, EsoVars.connectionColor, lastSignal ? 1f : 0f);
            for(int i = 0; i < 3; i++){
                if(!sides[i])continue;
                Draw.rect(connectionRegion, x, y, (90f + 90f * i) + rotdeg());
            }
            Draw.rect(connectionRegion, x, y, rotdeg());
            Draw.rect(topRegion, x, y, rotdeg());
        }

        @Override
        public void buildConfiguration(Table table){
            super.buildConfiguration(table);
            table.setBackground(Styles.black5);
            for(int i = 0; i < 3; i++){
                int ii = i;
                TextButton button = table.button(letters[i], () -> configure(ii)).size(40).get();
                button.getStyle().checked = Tex.buttonOver;
                button.update(() -> button.setChecked(sides[ii]));
            }
        }

        @Override
        public byte[] config(){
            return new byte[]{
                (byte)(sides[0] ? 1 : 0),
                (byte)(sides[1] ? 1 : 0),
                (byte)(sides[2] ? 1 : 0)
            };
        }
    }
}

package esoterum.content;

import arc.*;
import arc.graphics.g2d.*;
import esoterum.world.blocks.binary.*;
import esoterum.world.blocks.environment.*;
import mindustry.ctype.*;
import mindustry.world.*;
import mindustry.world.meta.*;

public class EsoBlocks implements ContentList{
    public static Block
        // environment
        esoPanel, esoPanel1, esoPanel2, esoPanel3,
        esoPanelFlat, esoPanelOpen, esoPanelE, esoPanelS, esoPanelO,

        // binary
        esoBlock, esoWire, esoConfigWire, esoConfigOutputWire,
        esoJunction, esoNode, esoRouter,
        esoSwitch, esoButton, esoClock,
        esoAnd, esoAndB, esoAndC,
        esoXor, esoXorB, esoXorC,
        esoNot,
        esoBuffer, esoTimedBufer, esoDelayGate, esoPulseExtender,
        esoLatch, esoMonostable, esoBarrier,
        esoLed, esoNoteBlock;

    public void load(){
        // region environment
        esoPanel = new nonBlendFloor("chamber-panel", 0);
        esoPanelFlat = new nonBlendFloor("chamber-panel-flat", 0);
        esoPanel1 = new nonBlendFloor("chamber-panel-1", 0);
        esoPanel2 = new nonBlendFloor("chamber-panel-2", 0);
        esoPanel3 = new nonBlendFloor("chamber-panel-3", 0);

        esoPanelOpen = new nonBlendFloor("chamber-panel-open", 3);

        esoPanelE = new nonBlendFloor("chamber-panel-e", 0);
        esoPanelS = new nonBlendFloor("chamber-panel-s", 0);
        esoPanelO = new nonBlendFloor("chamber-panel-o", 0);
        // endregion environment

        // region binary
        esoBlock = new BinaryBlock("test-binary-block"){{
            buildVisibility = BuildVisibility.hidden;
        }};

        esoWire = new BinaryAcceptor("binary-wire"){
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
                    connectionRegion
                };
            }
        };

        esoConfigWire = new ConfigWire("binary-set-wire");

        esoConfigOutputWire = new ConfigOutputWire("binary-output-wire");

        esoJunction = new BinaryJunction("binary-junction");

        esoNode = new BinaryNode("binary-node"){{
            range = 48f;
        }};

        esoRouter = new BinaryRouter("binary-router");

        esoSwitch = new BinarySwitch("binary-switch");

        esoButton = new BinaryButton("binary-button");

        esoClock = new BinaryClock("binary-clock");

        // LOGIC GATES
        // Skipping OR because they're basically just two wires side by side

        esoAnd = new BinaryGate("binary-AND"){{
            variant = "A";
        }};

        esoAndB = new BinaryGate("binary-AND-B"){
            {
                variant = "B";
                connections = "eso-gate-connections1";
                inputs = new boolean[]{false, true, true};
            }

            @Override
            public boolean operation(boolean[] in){
                return in[1] && in[2];
            }
        };

        esoAndC = new BinaryGate("binary-AND-C"){
            {
                variant = "C";
                connections = "eso-gate-connections2";
                inputs = new boolean[]{true, true, false};
            }

            @Override
            public boolean operation(boolean[] in){
                return in[1] && in[0];
            }
        };

        esoXor = new BinaryGate("binary-XOR"){
            {
                variant = "A";
            }

            @Override
            public boolean operation(boolean[] in){
                return in[0] ^ in[2];
            }
        };

        esoXorB = new BinaryGate("binary-XOR-B"){
            {
                variant = "B";
                connections = "eso-gate-connections1";
                inputs = new boolean[]{false, true, true};
            }

            @Override
            public void load(){
                super.load();
                topRegion = Core.atlas.find("eso-binary-XOR-top");
            }

            @Override
            public boolean operation(boolean[] in){
                return in[1] ^ in[2];
            }
        };

        esoXorC = new BinaryGate("binary-XOR-C"){
            {
                variant = "C";
                connections = "eso-gate-connections2";
                inputs = new boolean[]{true, true, false};
            }

            @Override
            public void load(){
                super.load();
                topRegion = Core.atlas.find("eso-binary-XOR-top");
            }

            @Override
            public boolean operation(boolean[] in){
                return in[0] ^ in[1];
            }
        };

        esoNot = new BinaryGate("binary-NOT"){
            {
                connections = "eso-not-connections";
                inputs = new boolean[]{false, true, false};
            }

            @Override
            public boolean operation(boolean[] in){
                return !in[1];
            }
        };

        esoBuffer = new BinaryGate("binary-buffer"){
            {
                connections = "eso-not-connections";
                inputs = new boolean[]{false, true, false};
            }

            @Override
            public boolean operation(boolean[] in){
                return in[1];
            }
        };

        esoTimedBufer = new TimedBufferGate("binary-delay-gate");

        esoDelayGate = new DelayGate("binary-wait-gate");

        esoPulseExtender = new PulseExtender("binary-pulse-extender");

        esoLatch = new BinaryLatch("binary-latch");

        esoMonostable = new BinaryMonostable("binary-monostable");

        esoBarrier = new BinaryGate("binary-barrier"){
            {
                inputs = new boolean[]{true, true, true};
            }

            @Override
            public boolean operation(boolean[] in){
                return in[1] && !(in[0] || in[2]);
            }

            @Override
            protected TextureRegion[] icons(){
                return new TextureRegion[]{
                    region,
                    topRegion,
                    Core.atlas.find("eso-full-connections"),
                    sideRegions[0],
                    sideRegions[2]
                };
            }
        };

        esoLed = new BinaryLed("binary-led");

        esoNoteBlock = new NoteBlock("binary-note-block");
        //endregion binary
    }
}
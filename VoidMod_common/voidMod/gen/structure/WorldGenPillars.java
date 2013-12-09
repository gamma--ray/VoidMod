package voidMod.gen.structure;

import java.util.Random;

import voidMod.VoidMod;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

//just a side note i wrote this myself (completly)

public class WorldGenPillars extends WorldGenerator {

    @Override
    public boolean generate(World world, Random random, int i, int k, int j) {
        // TODO Auto-generated method stub

        if (world.getBlockId(i, k, j) != VoidMod.hardenSpaceTimeStructure.blockID) {
            return false;
        }

        // size Generator

        int aid = random.nextInt(3) + 2;
        int size = aid * 2 - random.nextInt(2) + 1;

        // offset generator

        int Xoff = random.nextInt(aid + 1);
        int Zoff = random.nextInt(aid + 1);
        int Yoff = random.nextInt(size * 2 - 1) + 1;

        // direction generator

        int Xdir = random.nextInt(2) * 2 - 1;
        int Zdir = random.nextInt(2) * 2 - 1;

        int i1 = 0;
        int j1 = 0;

        int Ci = 0;
        int Cj = 0;

        for (int k1 = 0; k1 <= 120; k1++) {

            for (i1 = Ci; i1 < Math.ceil((double) 120 / Yoff) * Xoff
                    + (size - Xoff); i1++) {

                for (j1 = Cj; j1 < Math.ceil((double) 120 / Yoff) * Zoff
                        + (size - Zoff); j1++) {

                    if ((Math.ceil(k1 / Yoff) - 1) * Xoff == i1
                            && (Math.ceil(k1 / Yoff) - 1) * Zoff == j1) {

                        Ci = i1 - 1;
                        Cj = j1 - 1;

                        for (int i2 = i1; i2 < size + i1; i2++) {

                            for (int j2 = j1; j2 < size + j1; j2++) {

                                world.setBlock(
                                        i + i2 * Xdir,
                                        k1,
                                        j + j2 * Zdir,
                                        VoidMod.hardenSpaceTimeStructure.blockID);

                            }
                        }
                    }

                }

            }

        }

        return true;

    }

}

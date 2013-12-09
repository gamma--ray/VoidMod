package voidMod.world;

import voidMod.VoidMod;
import voidMod.gen.ChunkProviderVoidDim;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVoidDim extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(VoidMod.tutorialBiome,
                0.8F, 0.1F);
        this.dimensionId = VoidMod.dimensionId;
        this.isHellWorld = true;
        this.hasNoSky = true;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderVoidDim(worldObj, worldObj.getSeed(), true);
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public boolean canRespawnHere() {
        return false;
    }

    protected void generateLightBrightnessTable() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F)
                    * (1.0F - f) + f;
        }
    }

    // i have no idee how to use this it kind of worked
    public Vec3 getFogColor(float par1, float par2) {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(
                0.13333333333333333D, 0.0D, 0.1803921568627451D);
    }

    @Override
    public String getDimensionName() {
        return "The Void";
    }
}
package voidMod.gen;

import java.util.List;
import java.util.Random;

import voidMod.VoidMod;
import voidMod.gen.structure.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderVoidDim implements IChunkProvider {
    private Random hellRNG;

    /** A NoiseGeneratorOctaves used in generating nether terrain */
    private NoiseGeneratorOctaves voidNoiseGen1;
    private NoiseGeneratorOctaves voidNoiseGen2;
    private NoiseGeneratorOctaves voidNoiseGen3;

    /**
     * Determines whether something other than nettherack can be generated at a
     * location
     */
    private NoiseGeneratorOctaves voidFabricExculsivityNoiseGen;
    public NoiseGeneratorOctaves voidNoiseGen6;
    public NoiseGeneratorOctaves voidNoiseGen7;
    public NoiseGeneratorOctaves slowsandGravelNoiseGen;

    /** Is the world that the nether is getting generated. */
    private World worldObj;
    private double[] noiseField;

    /**
     * Holds the noise used to determine whether slowsand can be generated at a
     * location
     */
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];

    /**
     * Holds the noise used to determine whether something other than netherrack
     * can be generated at a location
     */
    private double[] voidFabricExclusivityNoise = new double[256];

    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;

    public ChunkProviderVoidDim(World par1World, long par2, boolean b) {
        this.worldObj = par1World;
        this.hellRNG = new Random(par2);
        this.voidNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.voidNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.voidNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);

        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.voidFabricExculsivityNoiseGen = new NoiseGeneratorOctaves(
                this.hellRNG, 4);
        this.voidNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
        this.voidNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);

        NoiseGeneratorOctaves[] noiseGens = { voidNoiseGen1, voidNoiseGen2,
                voidNoiseGen3, slowsandGravelNoiseGen,
                voidFabricExculsivityNoiseGen, voidNoiseGen6, voidNoiseGen7 };
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World,
                this.hellRNG, noiseGens);
        this.voidNoiseGen1 = noiseGens[0];
        this.voidNoiseGen2 = noiseGens[1];
        this.voidNoiseGen3 = noiseGens[2];
        this.slowsandGravelNoiseGen = noiseGens[3];
        this.voidFabricExculsivityNoiseGen = noiseGens[4];
        this.voidNoiseGen6 = noiseGens[5];
        this.voidNoiseGen7 = noiseGens[6];
    }

    // DONE
    /**
     * Generates the shape of the terrain in the void.
     */
    public void generateNetherTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
        byte b0 = 4;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;

        this.noiseField = this.initializeNoiseField(this.noiseField, par1 * b0,
                0, par2 * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2
                            + k1 + 0];
                    double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2
                            + k1 + 0];
                    double d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2
                            + k1 + 0];
                    double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2
                            + k1 + 0];
                    double d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2
                            + k1 + 1] - d1)
                            * d0;
                    double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2
                            + k1 + 1] - d2)
                            * d0;
                    double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2
                            + k1 + 1] - d3)
                            * d0;
                    double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2
                            + k1 + 1] - d4)
                            * d0;

                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1
                                    * 8 + l1;
                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                int l2 = 0;

                                if (d15 > 50.0D
                                        && (k1 * 8 + l1 < 25 || k1 * 8 + l1 > 95)) {
                                    l2 = VoidMod.voidFabric.blockID;
                                }

                                par3ArrayOfByte[j2] = (byte) l2;
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    // DONE
    /**
     * name based on ChunkProviderGenerate
     */
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte) {

        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(
                this, par1, par2, par3ArrayOfByte, null);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) {
            return;
        }

        double d0 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(
                this.slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0, d0,
                1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(
                this.gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, d0,
                1.0D, d0);
        this.voidFabricExclusivityNoise = this.voidFabricExculsivityNoiseGen
                .generateNoiseOctaves(this.voidFabricExclusivityNoise,
                        par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D,
                        d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 < 127 - this.hellRNG.nextInt(5)
                            && k1 > 0 + this.hellRNG.nextInt(5)) {

                        if (k1 < 121 - this.hellRNG.nextInt(5)
                                && k1 > 11 + this.hellRNG.nextInt(3)) {

                            // TODO ? add random patches of blocks

                        }

                        else {
                            par3ArrayOfByte[l1] = (byte) VoidMod.hardenSpaceTimeStructure.blockID;
                        }

                    } else {
                        par3ArrayOfByte[l1] = (byte) Block.bedrock.blockID;
                    }
                }
            }
        }

    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it
     * will generates all the blocks for the specified chunk from the map seed
     * and chunk seed
     */
    public Chunk provideChunk(int par1, int par2) {
        this.hellRNG.setSeed(par1 * 341873128712L + par2
                * 132897987541L);
        byte[] abyte = new byte[32768];
        this.generateNetherTerrain(par1, par2, abyte);

        this.replaceBlocksForBiome(par1, par2, abyte);
        Chunk chunk = new Chunk(this.worldObj, abyte, par1, par2);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager()
                .loadBlockGeneratorData((BiomeGenBase[]) null, par1 * 16,
                        par2 * 16, 16, 16);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte) abiomegenbase[k].biomeID;
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the
     * [empty] noise array, the position, and the size.
     */
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2,
            int par3, int par4, int par5, int par6, int par7) {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(
                this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) {
            return event.noisefield;
        }
        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.voidNoiseGen6.generateNoiseOctaves(
                this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D,
                1.0D);
        this.noiseData5 = this.voidNoiseGen7.generateNoiseOctaves(
                this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D,
                100.0D);
        this.noiseData1 = this.voidNoiseGen3.generateNoiseOctaves(
                this.noiseData1, par2, par3, par4, par5, par6, par7,
                d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noiseData2 = this.voidNoiseGen1
                .generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5,
                        par6, par7, d0, d1, d0);
        this.noiseData3 = this.voidNoiseGen2
                .generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5,
                        par6, par7, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[par6];
        int i2;

        for (i2 = 0; i2 < par6; ++i2) {
            adouble1[i2] = Math.cos(i2 * Math.PI * 6.0D
                    / par6) * 2.0D;
            double d2 = i2;

            if (i2 > par6 / 2) {
                d2 = par6 - 1 - i2;
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < par5; ++i2) {
            for (int j2 = 0; j2 < par7; ++j2) {
                double d3 = (this.noiseData4[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.noiseData5[l1] / 8000.0D;

                if (d5 < 0.0D) {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;

                if (d5 < 0.0D) {
                    d5 /= 2.0D;

                    if (d5 < -1.0D) {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                } else {
                    if (d5 > 1.0D) {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * par6 / 16.0D;
                ++l1;

                for (int k2 = 0; k2 < par6; ++k2) {
                    double d6 = 0.0D;
                    double d7 = adouble1[k2];
                    double d8 = this.noiseData2[k1] / 512.0D;
                    double d9 = this.noiseData3[k1] / 512.0D;
                    double d10 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D) {
                        d6 = d8;
                    } else if (d10 > 1.0D) {
                        d6 = d9;
                    } else {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (k2 > par6 - 4) {
                        d11 = (k2 - (par6 - 4)) / 3.0F;
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if (k2 < d4) {
                        d11 = (d4 - k2) / 4.0D;

                        if (d11 < 0.0D) {
                            d11 = 0.0D;
                        }

                        if (d11 > 1.0D) {
                            d11 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    par1ArrayOfDouble[k1] = d6;
                    ++k1;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        BlockSand.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(
                par1IChunkProvider, worldObj, hellRNG, par2, par3, false));

        int k = par2 * 16;
        int l = par3 * 16;
        int k1;
        int l1;
        int i2;
        
        WorldGenCrystals worldgencrystals = new WorldGenCrystals();
        WorldGenPillars worldgenpillars = new WorldGenPillars();

        if (this.hellRNG.nextInt(4) == 3) {

            int X = k + this.hellRNG.nextInt(16);
            int Z = l + this.hellRNG.nextInt(16);

            worldgenpillars.generate(worldObj, hellRNG, X, 10, Z);

        }
        
        if (this.hellRNG.nextInt(4) == 3){
            
            int X = k + this.hellRNG.nextInt(16);
            int Z = l + this.hellRNG.nextInt(16);
            int Y = 16 + hellRNG.nextInt(2 + hellRNG.nextInt(2 + hellRNG.nextInt(2 + hellRNG.nextInt(2))));
            worldgencrystals.generate(worldObj, hellRNG, X, Y, Z);
            
        }
        
        WorldGenMinable worldgenminable = new WorldGenMinable(
                Block.oreNetherQuartz.blockID, 5, Block.blockDiamond.blockID); // TODO
                                                                               // add
                                                                               // custom
                                                                               // ore
        int j2;

        for (k1 = 0; k1 < 16; ++k1) {
            l1 = k + this.hellRNG.nextInt(16);
            i2 = this.hellRNG.nextInt(108) + 10;
            j2 = l + this.hellRNG.nextInt(16);
            worldgenminable.generate(this.worldObj, this.hellRNG, l1, i2, j2);
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj,
                hellRNG, k, l));
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(
                par1IChunkProvider, worldObj, hellRNG, par2, par3, false));

        BlockSand.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go. If
     * passed false, save up to two chunks. Return true if all chunks have been
     * saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk. Not saved during autosave,
     * only during world unload. Currently unimplemented.
     */
    public void saveExtraData() {
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to
     * unload every such chunk.
     */
    public boolean unloadQueuedChunks() {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave() {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString() {
        return "HellRandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the
     * given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType,
            int par2, int par3, int par4) {

        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2,
                par4);
        return biomegenbase == null ? null : biomegenbase
                .getSpawnableList(par1EnumCreatureType);
    }

    /**
     * Returns the location of the closest structure of the specified type. If
     * not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str,
            int par3, int par4, int par5) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {

    }
}

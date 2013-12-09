package voidMod;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class VoidBiome extends BiomeGenBase {

    public VoidBiome(int id) {

        super(id);

        // sets name
        this.setBiomeName("'The Void'");

        // sets background color
        this.setColor(392556);

        // sets which monsters can spawn
        // TODO add custom mobs
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(
                EntityCaveSpider.class, 50, 4, 4));

    }

}
package voidMod;

import voidMod.blocks.BlockPortalTutorial;
import voidMod.blocks.HardendFabricOfTime;
import voidMod.blocks.VoidFabric;
import voidMod.world.WorldProviderVoidDim;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = VoidMod.modid, name = "The Void Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class VoidMod {

    // modName
    public static final String modid = "The_Void_mod";

    // new Biomes:
    public static final BiomeGenBase tutorialBiome = new VoidBiome(23);

    // new Blocks:
    public static Block voidFabric;
    public static Block portalTutorialBlock;
    public static Block hardenSpaceTimeStructure;

    // new Items:
    public static Item tutorialItem;

    // new Dims:
    public static int dimensionId = 8;

    
    

    @EventHandler
    public void load(FMLInitializationEvent event) {
        
        
        
        // adding blocks
        voidFabric = new VoidFabric(250).setUnlocalizedName("voidFabric");
        portalTutorialBlock = new BlockPortalTutorial(501)
                .setUnlocalizedName("portalTutorialBlock");
        hardenSpaceTimeStructure = new HardendFabricOfTime(251)
                .setUnlocalizedName("hardenSpaceTimeStructure");

        GameRegistry.registerBlock(voidFabric,
                modid + voidFabric.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(portalTutorialBlock, modid
                + portalTutorialBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(hardenSpaceTimeStructure, modid
                + hardenSpaceTimeStructure.getUnlocalizedName().substring(5));

        LanguageRegistry.addName(voidFabric, "Void Fabric");
        LanguageRegistry.addName(portalTutorialBlock, "Portal Tutorial Block");
        LanguageRegistry.addName(hardenSpaceTimeStructure,
                "Hardend Space-Time Structure");
        // end of adding blocks

        // adding items
        tutorialItem = new ItemTutorial(5000)
                .setUnlocalizedName("tutorialItem");

        LanguageRegistry.addName(new ItemStack(tutorialItem, 1, 0),
                "Tutorial Item");
        LanguageRegistry.addName(new ItemStack(tutorialItem, 1, 1),
                "Second Tutorial Item");

        // recipes
        TutorialCrafting.loadRecipes();

        // new world stuff
        GameRegistry.addBiome(tutorialBiome);

        DimensionManager.registerProviderType(VoidMod.dimensionId,
                WorldProviderVoidDim.class, false);
        DimensionManager.registerDimension(VoidMod.dimensionId,
                VoidMod.dimensionId);
    }
}
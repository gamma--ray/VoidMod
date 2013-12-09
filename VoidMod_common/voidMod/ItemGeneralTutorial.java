package voidMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGeneralTutorial extends Item {
    public ItemGeneralTutorial(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(VoidMod.modid + ":"
                + this.getUnlocalizedName().substring(5));
    }
}
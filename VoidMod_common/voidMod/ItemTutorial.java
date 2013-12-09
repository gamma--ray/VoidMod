package voidMod;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTutorial extends Item {
    public ItemTutorial(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        icons = new Icon[2];

        for (int i = 0; i < icons.length; i++) {
            icons[i] = par1IconRegister.registerIcon(VoidMod.modid + ":"
                    + this.getUnlocalizedName().substring(5) + i);
        }
    }

    public static final String[] names = new String[] { "first", "second" };

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + names[i];
    }

    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
            List par3List) {
        for (int x = 0; x < 2; x++) {
            par3List.add(new ItemStack(this, 1, x));
        }
    }
}
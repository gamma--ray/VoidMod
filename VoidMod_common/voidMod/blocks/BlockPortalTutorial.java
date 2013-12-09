package voidMod.blocks;

import voidMod.TeleporterVoid;
import voidMod.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class BlockPortalTutorial extends BlockPortal {
    public BlockPortalTutorial(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
            int par4, Entity par5Entity) {
        if (par5Entity.ridingEntity == null
                && par5Entity.riddenByEntity == null
                && par5Entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) par5Entity;
            ModLoader.getMinecraftServerInstance();
            MinecraftServer mServer = MinecraftServer.getServer();

            if (player.timeUntilPortal > 0) {
                player.timeUntilPortal = 10;
            } else if (player.dimension != VoidMod.dimensionId) {
                player.timeUntilPortal = 10;

                player.mcServer
                        .getConfigurationManager()
                        .transferPlayerToDimension(
                                player,
                                VoidMod.dimensionId,
                                new TeleporterVoid(
                                        mServer.worldServerForDimension(VoidMod.dimensionId)));
            } else {
                player.timeUntilPortal = 10;
                player.mcServer.getConfigurationManager()
                        .transferPlayerToDimension(
                                player,
                                0,
                                new TeleporterVoid(mServer
                                        .worldServerForDimension(1)));
            }
        }
    }

    @Override
    public boolean tryToCreatePortal(World par1World, int par2, int par3,
            int par4) {
        byte b0 = 0;
        byte b1 = 0;

        if (par1World.getBlockId(par2 - 1, par3, par4) == Block.sandStone.blockID
                || par1World.getBlockId(par2 + 1, par3, par4) == Block.sandStone.blockID) {
            b0 = 1;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == Block.sandStone.blockID
                || par1World.getBlockId(par2, par3, par4 + 1) == Block.sandStone.blockID) {
            b1 = 1;
        }

        if (b0 == b1) {
            return false;
        } else {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1)) {
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l) {
                for (i1 = -1; i1 <= 3; ++i1) {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                        int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1,
                                par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(
                                par2 + b0 * l, par3 + i1, par4 + b1 * l);

                        if (flag) {
                            if (j1 != Block.sandStone.blockID) {
                                return false;
                            }
                        } else if (!isAirBlock && j1 != Block.fire.blockID) {
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l) {
                for (i1 = 0; i1 < 3; ++i1) {
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l,
                            VoidMod.portalTutorialBlock.blockID, 0, 2);
                }
            }

            return true;
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
            int par4, int par5) {
        byte b0 = 0;
        byte b1 = 1;

        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID
                || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID) {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1) {
            ;
        }

        if (par1World.getBlockId(par2, i1 - 1, par4) != Block.sandStone.blockID) {
            par1World.setBlockToAir(par2, par3, par4);
        } else {
            int j1;

            for (j1 = 1; j1 < 4
                    && par1World.getBlockId(par2, i1 + j1, par4) == this.blockID; ++j1) {
                ;
            }

            if (j1 == 3
                    && par1World.getBlockId(par2, i1 + j1, par4) == Block.sandStone.blockID) {
                boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID
                        || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
                boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID
                        || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

                if (flag && flag1) {
                    par1World.setBlockToAir(par2, par3, par4);
                } else {
                    if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != Block.sandStone.blockID || par1World
                            .getBlockId(par2 - b0, par3, par4 - b1) != this.blockID)
                            && (par1World
                                    .getBlockId(par2 - b0, par3, par4 - b1) != Block.sandStone.blockID || par1World
                                    .getBlockId(par2 + b0, par3, par4 + b1) != this.blockID)) {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
            } else {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }

}

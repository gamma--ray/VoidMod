package voidMod.gen.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import voidMod.*;

public class WorldGenCrystals extends WorldGenerator {

    @Override
    public boolean generate(World world, Random random, int i, int k ,int j) {
        
        // TODO checker + return
        
        boolean flag = true;
        
        int b = Block.ice.blockID;
        
        int i1 = 0;
        int k1 = 0;
        int j1 = 0;
        
        int aid = 0;
    
        int Pdx = random.nextInt(2)*2 - 1;
        int Pdz = random.nextInt(2 - Pdx*Pdx)*2 - 1 + Pdx*Pdx; 
        
        int Pd = random.nextInt(2);
        
        int Mh = random.nextInt(6) + 11;
        int Ph1 = random.nextInt(2) + 3;
        int Ph2 = random.nextInt(2 + Pd) + 2;
        int Ph3 = random.nextInt(3) + 2;
        
        int Pa2 = 3 + (Pd - 1)*(random.nextInt(Ph2 - 1));
        int Pa3 = random.nextInt(2) + 2;
        
        if (world.getBlockId(i , k - 1, j ) != VoidMod.voidFabric.blockID){
            
            return false;
            
        }
        
        for (k1 = 0; k1 <= Mh + 5; k1++ ){
            
            for (i1 = 0; i1 <= 2; i1++ ){
                
                for (j1 = 0; j1 <= 2; j1++ ){
                    
                    if( world.getBlockId(i - i1 - 1, k +k1, j - j1 ) != 0){
                        
                        return false;
                        
                    }
                    
                }
                
            }
            
        }
        
        
        // 3 * 3 cube
        for (k1 = 0; k1 <= 2; k1++ ){
            
            for (i1 = -1; i1 <= 1; i1++ ){
                
                for (j1 = -1; j1 <= 1; j1++ ){
                    
                    world.setBlock(i - i1, k +k1, j - j1 , b);
                    
                }
                
            }
            
        }
        
        //+ shape
        for (k1 = 0; k1 <= Mh; k1++ ){
            
            for (i1 = -1; i1 <= 1; i1= i1 + 2 ){
                
                for (j1 = -1; j1 <= 1; j1= j1 + 2 ){
                    
                    world.setBlock(i - i1, k + k1 + 3, j, b);
                    world.setBlock(i, k + k1 + 3, j - j1, b);
                
                }
                
            }
            
            world.setBlock(i,k + k1 + 3, j, b);
            
        }
        
        world.setBlock(i,k + k1 + 3, j, b);
        world.setBlock(i,k + k1 + 4, j, b);
        
        //random pillar1
        for(aid = 0;( aid < 3) && (flag) ; aid++ ){
            
            for(k1 = 0;( k1 < 3) && (flag); k1 ++ ){               
               
                
                if( (world.getBlockId(i - Pdx*(aid + 1) , k + k1, j - Pdz*(aid + 1)) == b)||(world.getBlockId(i - Pdx*(aid + 1) , k + k1, j - Pdz*(aid + 1) ) == 0) ){
                    
                    for (i1 = -1; i1 <= 1; i1= i1 + 2 ){
                        
                        for (j1 = -1; j1 <= 1; j1= j1 + 2 ){
                            
                            world.setBlock(i - Pdx*(aid + 1) - i1, k1 + k + aid *3, j - Pdz*(aid + 1), b);
                            world.setBlock(i - Pdx*(aid + 1), k + k1 + aid*3, j - j1 - Pdz*(aid + 1), b);
                      
                            System.out.println(Pdx);
                            System.out.println(Pdz);
                            System.out.println("================================");
                            
                        }
                        
                    }
                    
                    world.setBlock(i - Pdx*(aid + 1) , k + k1 + aid*3 , j - Pdz*(aid + 1) , b);
                 
                    
                    
                    
                } else {
                    
                    flag = false;
                    
                }
             
                
                
            }
            
            world.setBlock(i - Pdx*(aid + 1) , k + k1 + aid*3 , j - Pdz*(aid + 1) , b);
            world.setBlock(i - Pdx*(aid + 1) , k + k1 + aid*3 + 1 , j - Pdz*(aid + 1) , b);
            
        }
        
      
        
        
        aid = 0;
        k1 = 0;
        
        return true;
        
    }

    

    
}

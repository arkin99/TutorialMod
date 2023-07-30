package arkin.tutorial.blockEntities;

import arkin.tutorial.TutorialMod;
import arkin.tutorial.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<BlockEntityType<SuperBlockEntity>> SUPER_BLOCK_ENTITY =
            TILES.register("super_block_entity",
                    () -> BlockEntityType.Builder.of(SuperBlockEntity::new, ModBlocks.SUPER_BLOCK.get()).build(null));
}
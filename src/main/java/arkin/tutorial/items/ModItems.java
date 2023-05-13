package arkin.tutorial.items;

import arkin.tutorial.TutorialMod;
import arkin.tutorial.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

    public static final RegistryObject<Item> ITEM = ITEMS.register("hh", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEMM = ITEMS.register("hhh", BB::new);

    public static final RegistryObject<Item> TUT_BLOCK = ITEMS.register("gg", () -> new BlockItem(ModBlocks.TUT_BLOCK.get(), new Item.Properties()));
}
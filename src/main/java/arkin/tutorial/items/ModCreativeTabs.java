package arkin.tutorial.items;

import arkin.tutorial.TutorialMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {
    public static CreativeModeTab tab;

    @SubscribeEvent
    public static void regTab(CreativeModeTabEvent.Register e){
        tab = e.registerCreativeModeTab(new ResourceLocation(TutorialMod.MODID, "tutorial_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.ITEM.get())).
                        title(Component.translatable("creativemodetab.tab")));
    }
}
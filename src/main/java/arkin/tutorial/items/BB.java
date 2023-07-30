package arkin.tutorial.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class BB extends Item {
    public BB() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            for (int i = 0; i < player.getInventory().items.size(); i++) {
                if (player.getInventory().items.get(i).isEmpty())
                    player.getInventory().items.set(i, new ItemStack(Items.DIRT, 5));
                if (player.getInventory().items.get(i).is(Items.ACACIA_LOG))
                    player.getInventory().items.set(i, new Random().nextBoolean() ? new ItemStack(Items.ACACIA_BOAT) : new ItemStack(Items.ACACIA_BUTTON));
            }
        }
        return super.use(world, player, hand);
    }
}
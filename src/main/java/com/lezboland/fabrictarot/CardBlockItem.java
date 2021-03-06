package com.lezboland.fabrictarot;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class CardBlockItem extends BlockItem {
    public CardBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        // Prevent consuming item from inventory when placed
        ItemPlacementContext itemPlacementContext = this.getPlacementContext(context);
        ItemStack stack = itemPlacementContext.getStack();

        ActionResult result = super.place(context);

        if (result.isAccepted()) {
            stack.increment(1);
        }

        return result;
    }

    @Override
    public String getTranslationKey() {
        // Give item a separate name from block
        return Util.createTranslationKey("item", Registry.ITEM.getId(this));
    }
}

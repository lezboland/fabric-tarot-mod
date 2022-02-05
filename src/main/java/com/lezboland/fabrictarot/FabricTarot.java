package com.lezboland.fabrictarot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricTarot implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("fabrictarot");

	public static final Block CARD_BLOCK = new CardBlock(FabricBlockSettings.of(Material.CARPET).nonOpaque().breakInstantly().dropsNothing());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Let's get gay and witchy (dykes rule)");

		Registry.register(Registry.BLOCK, new Identifier("fabrictarot", "card_block"), CARD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("fabrictarot", "card_block"), new CardBlockItem(CARD_BLOCK, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
	}
}

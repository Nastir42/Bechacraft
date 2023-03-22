package bapt.bechacraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bapt.bechacraft.block.ModBlocks;
import bapt.bechacraft.block.entity.ModBlockEntities;
import bapt.bechacraft.item.ModItemGroup;
import bapt.bechacraft.item.ModItems;
import bapt.bechacraft.recipe.ModRecipes;
import bapt.bechacraft.screen.ModScreenHandlers;

public class Bechacraft implements ModInitializer {
	
	public static final String MOD_ID = "bechacraft";
	public static final String MOD_NAME = "Béchacraft";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {

		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();

		LOGGER.info(MOD_NAME + " has initialized succesfully !");
	}
}

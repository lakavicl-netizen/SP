package net.lointain.cosmos.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class CosmosModTabs {
   public static final DeferredRegister<CreativeModeTab> REGISTRY;
   public static final RegistryObject<CreativeModeTab> PLANET_ITMES;
   public static final RegistryObject<CreativeModeTab> MOON_BLOCKS;
   public static final RegistryObject<CreativeModeTab> SPACESTRIXBLOCKS;
   public static final RegistryObject<CreativeModeTab> COSMOS_ITEMS;
   public static final RegistryObject<CreativeModeTab> PLANET_BLOCKS;
   public static final RegistryObject<CreativeModeTab> MOON_ITEMS;

   @SubscribeEvent
   public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
      if (tabData.getTabKey() == CreativeModeTabs.f_256797_) {
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_HELMET.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_CHESTPLATE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_LEGGINGS.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_BOOTS.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_HELMET.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_CHESTPLATE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_LEGGINGS.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_BOOTS.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_HELMET.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_CHESTPLATE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_LEGGINGS.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_BOOTS.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.f_256731_) {
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_ROVER_SPAWN_EGG.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_ROVER_SPAWN_EGG.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_ROVER_SPAWN_EGG.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.f_256869_) {
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SHOWEL.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMPICKAXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_HOE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SHOVEL.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_PICKAXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.STEEL_HOE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SWORD.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_AXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SHOVEL.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_PICKAXE.get());
         tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_HOE.get());
      }

   }

   static {
      REGISTRY = DeferredRegister.create(Registries.f_279569_, "cosmos");
      PLANET_ITMES = REGISTRY.register("planet_itmes", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.planet_itmes")).m_257737_(() -> new ItemStack((ItemLike)CosmosModItems.SULPHER_DUST.get())).m_257501_((parameters, tabData) -> {
            tabData.m_246326_((ItemLike)CosmosModItems.SULPHER_DUST.get());
            tabData.m_246326_((ItemLike)CosmosModItems.ASHED_SULPHER_DUST.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKLE_INGOT.get());
            tabData.m_246326_((ItemLike)CosmosModItems.RAW_NICKLE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.SULPHURIC_ACID_BUCKET.get());
         }).m_257652_());
      MOON_BLOCKS = REGISTRY.register("moon_blocks", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.moon_blocks")).m_257737_(() -> new ItemStack((ItemLike)CosmosModBlocks.MOON_CLAYDUST.get())).m_257501_((parameters, tabData) -> {
            tabData.m_246326_(((Block)CosmosModBlocks.MOON_CLAYDUST.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.HIGHLY_COMPRESSED_ICE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.COMPRESSED_FROZEN_MOON_CLAYDUST.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.RAW_TITANIUM.get());
            tabData.m_246326_(((Block)CosmosModBlocks.FROZEN_TITANIUMORE.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_INGOT.get());
            tabData.m_246326_(((Block)CosmosModBlocks.TITANIUM_BLOCK.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.RAWSILICON.get());
            tabData.m_246326_(((Block)CosmosModBlocks.SILICONEDMOONCLAY.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.SILICONCRYSTAL.get());
            tabData.m_246326_(((Block)CosmosModBlocks.CHAMBERED_SUPER_SMELTERBLOCK.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.SILICONCHIP.get());
            tabData.m_246326_(((Block)CosmosModBlocks.STONE_CORE_BLOCK.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.RAW_NICKEL_STONE.get()).m_5456_());
         }).m_257652_());
      SPACESTRIXBLOCKS = REGISTRY.register("spacestrixblocks", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.spacestrixblocks")).m_257737_(() -> new ItemStack((ItemLike)CosmosModBlocks.LARGESOLARPANEL.get())).m_257501_((parameters, tabData) -> {
            tabData.m_246326_(((Block)CosmosModBlocks.LARGESOLARPANEL.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.SMALLSOLARPANEL.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.CABLE_N.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.ENERGY_METER.get());
            tabData.m_246326_(((Block)CosmosModBlocks.STEEL_BATTERY.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.STEEL_BLOCK.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.EXTINGUISHED_LANTERN.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.STEEL_RESTONE_RECIEVER_OFF.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.STEEL_LANDING_PAD_OFF.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.PROJECTOR_TABLE.get()).m_5456_());
         }).m_257652_());
      COSMOS_ITEMS = REGISTRY.register("cosmos_items", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.cosmos_items")).m_257737_(() -> new ItemStack((ItemLike)CosmosModItems.T_3_THRUSTER.get())).m_257501_((parameters, tabData) -> {
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMARMOUR_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SWORD.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_AXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SHOWEL.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMPICKAXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_HOE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.T_2_THRUSTER.get());
            tabData.m_246326_((ItemLike)CosmosModItems.T_3_THRUSTER.get());
            tabData.m_246326_((ItemLike)CosmosModItems.T_4_THRUSTER.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_NUGGET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_INGOT.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_PLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKLE_PLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_PLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEELENGINE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_FUELTANK.get());
            tabData.m_246326_((ItemLike)CosmosModItems.DEADCOMPASS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.SPACECOMPASS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TORCHEXTINGUISHED.get());
            tabData.m_246326_((ItemLike)CosmosModItems.LANTERNEXTINGUISHED.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SUIT_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SUIT_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SUIT_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SUIT_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SUIT_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SUIT_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SUIT_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SUIT_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SUIT_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SUIT_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SUIT_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SUIT_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SPACE_NODULE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_SPACE_NODULE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SPACE_NODULE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SWORD.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_AXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_SHOVEL.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_PICKAXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_HOE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SWORD.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_AXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_SHOVEL.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_PICKAXE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_HOE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEELARMOUR_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_HELMET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_CHESTPLATE.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_LEGGINGS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKELARMOUR_BOOTS.get());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_ROVER_SPAWN_EGG.get());
            tabData.m_246326_((ItemLike)CosmosModItems.TITANIUM_ROVER_SPAWN_EGG.get());
            tabData.m_246326_((ItemLike)CosmosModItems.NICKEL_ROVER_SPAWN_EGG.get());
            tabData.m_246326_((ItemLike)CosmosModItems.CRUDE_OIL_BUCKET.get());
            tabData.m_246326_((ItemLike)CosmosModItems.DETONATION_CONTROLER.get());
            tabData.m_246326_(((Block)CosmosModBlocks.DETONATOR_TARGET.get()).m_5456_());
            tabData.m_246326_((ItemLike)CosmosModItems.STEEL_FLASH_HELMET_ON_HELMET.get());
         }).m_257652_());
      PLANET_BLOCKS = REGISTRY.register("planet_blocks", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.planet_blocks")).m_257737_(() -> new ItemStack((ItemLike)CosmosModBlocks.SULPHER_STONE.get())).m_257501_((parameters, tabData) -> {
            tabData.m_246326_(((Block)CosmosModBlocks.SULPHURIC_SAND.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.CONCENTRATEDSULPHERORE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.SULPHER_STONE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.SULPHER_TNT.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.ASHEDSULPHURIC_TNT.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.NICKLE_BLOCK.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.MATIAN_SAND.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.COMPRESSED_STONE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.NITROGEN.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.ARGON.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.METHANE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.AMMONIA.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.HYDROGEN.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.HELIUM.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.VAPOUR.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.FLOURINE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.BORON.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.ASHED_SULPHER_STONE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.CHISELED_MARTIAN_STONE.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.MARTIAN_STONE_TILES.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.MARTIAN_STONE_BRICKS.get()).m_5456_());
            tabData.m_246326_(((Block)CosmosModBlocks.POLISHED_MARTIAN_STONE.get()).m_5456_());
         }).m_257652_());
      MOON_ITEMS = REGISTRY.register("moon_items", () -> CreativeModeTab.builder().m_257941_(Component.m_237115_("item_group.cosmos.moon_items")).m_257737_(() -> new ItemStack((ItemLike)CosmosModItems.TITANIUM_INGOT.get())).m_257501_((parameters, tabData) -> tabData.m_246326_((ItemLike)CosmosModItems.TITANIUMSPAKER.get())).m_257652_());
   }
}

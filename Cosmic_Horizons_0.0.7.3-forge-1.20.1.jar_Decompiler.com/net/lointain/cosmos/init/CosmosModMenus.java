package net.lointain.cosmos.init;

import net.lointain.cosmos.world.inventory.AntenaNamerMenu;
import net.lointain.cosmos.world.inventory.CHamberedsupersmelterMenu;
import net.lointain.cosmos.world.inventory.LandingSelectorMenu;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.lointain.cosmos.world.inventory.ProjectionSelectorMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CosmosModMenus {
   public static final DeferredRegister<MenuType<?>> REGISTRY;
   public static final RegistryObject<MenuType<CHamberedsupersmelterMenu>> C_HAMBEREDSUPERSMELTER;
   public static final RegistryObject<MenuType<PlanetSelectorMenu>> PLANET_SELECTOR;
   public static final RegistryObject<MenuType<AntenaNamerMenu>> ANTENA_NAMER;
   public static final RegistryObject<MenuType<LandingSelectorMenu>> LANDING_SELECTOR;
   public static final RegistryObject<MenuType<ProjectionSelectorMenu>> PROJECTION_SELECTOR;

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "cosmos");
      C_HAMBEREDSUPERSMELTER = REGISTRY.register("c_hamberedsupersmelter", () -> IForgeMenuType.create(CHamberedsupersmelterMenu::new));
      PLANET_SELECTOR = REGISTRY.register("planet_selector", () -> IForgeMenuType.create(PlanetSelectorMenu::new));
      ANTENA_NAMER = REGISTRY.register("antena_namer", () -> IForgeMenuType.create(AntenaNamerMenu::new));
      LANDING_SELECTOR = REGISTRY.register("landing_selector", () -> IForgeMenuType.create(LandingSelectorMenu::new));
      PROJECTION_SELECTOR = REGISTRY.register("projection_selector", () -> IForgeMenuType.create(ProjectionSelectorMenu::new));
   }
}

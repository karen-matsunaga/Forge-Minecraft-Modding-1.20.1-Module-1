package net.karen.mccourse;

import com.mojang.logging.LogUtils;
import net.karen.mccourse.block.ModBlocks;
import net.karen.mccourse.item.ModCreativeModeTabs;
import net.karen.mccourse.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MCCourseMod.MOD_ID)
public class MCCourseMod {

    public static final String MOD_ID = "mccourse";

    public static final Logger LOGGER = LogUtils.getLogger();

    public MCCourseMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register Creative Mode Tab
        ModCreativeModeTabs.register(modEventBus);

        // Register items
        ModItems.register(modEventBus);

        // Register blocks
        ModBlocks.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    // To show all items in creative mode tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            // First item
            event.accept(ModItems.ALEXANDRITE);
            // Second item
            event.accept(ModItems.RAW_ALEXANDRITE);

        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {

            // Blocks
            // First block
            event.accept(ModBlocks.ALEXANDRITE_BLOCK);

            // Second block
            event.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);

            // Custom Advanced Block
            event.accept(ModBlocks.SOUND_BLOCK);

            // Ores
            event.accept(ModBlocks.ALEXANDRITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
            event.accept(ModBlocks.END_STONE_ALEXANDRITE_ORE);
            event.accept(ModBlocks.NETHER_ALEXANDRITE_ORE);
        }

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

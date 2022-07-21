//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dev.wiji.Zephyr.v1_8_8.PacketEnums;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public enum LegacyEntityType {
    DROPPED_ITEM("Item", Item.class, 1, false),
    EXPERIENCE_ORB("XPOrb", EntityExperienceOrb.class, 2),
    LEASH_HITCH("LeashKnot", EntityLeash.class, 8),
    PAINTING("Painting", EntityPainting.class, 9),
    ARROW("Arrow", EntityArrow.class, 10),
    SNOWBALL("Snowball", EntitySnowball.class, 11),
    FIREBALL("Fireball", EntityLargeFireball.class, 12),
    SMALL_FIREBALL("SmallFireball", EntitySmallFireball.class, 13),
    ENDER_PEARL("ThrownEnderpearl", EntityEnderPearl.class, 14),
    ENDER_SIGNAL("EyeOfEnderSignal", EntityEnderSignal.class, 15),
    THROWN_EXP_BOTTLE("ThrownExpBottle", EntityThrownExpBottle.class, 17),
    ITEM_FRAME("ItemFrame", EntityItemFrame.class, 18),
    WITHER_SKULL("WitherSkull", EntityWitherSkull.class, 19),
    PRIMED_TNT("PrimedTnt", EntityTNTPrimed.class, 20),
    FALLING_BLOCK("FallingSand", EntityFallingBlock.class, 21, false),
    FIREWORK("FireworksRocketEntity", EntityFireworks.class, 22, false),
    ARMOR_STAND("ArmorStand", EntityArmorStand.class, 30, false),
    MINECART_COMMAND("MinecartCommandBlock", EntityMinecartCommandBlock.class, 40),
    BOAT("Boat", EntityBoat.class, 41),
    MINECART("MinecartRideable", EntityMinecartRideable.class, 42),
    MINECART_CHEST("MinecartChest", EntityMinecartChest.class, 43),
    MINECART_FURNACE("MinecartFurnace", EntityMinecartFurnace.class, 44),
    MINECART_TNT("MinecartTNT", EntityMinecartTNT.class, 45),
    MINECART_HOPPER("MinecartHopper", EntityMinecartHopper.class, 46),
    MINECART_MOB_SPAWNER("MinecartMobSpawner", EntityMinecartMobSpawner.class, 47),
    CREEPER("Creeper", EntityCreeper.class, 50),
    SKELETON("Skeleton", EntitySkeleton.class, 51),
    SPIDER("Spider", EntitySpider.class, 52),
    GIANT("Giant", EntityGiantZombie.class, 53),
    ZOMBIE("Zombie", EntityZombie.class, 54),
    SLIME("Slime", EntitySlime.class, 55),
    GHAST("Ghast", EntityGhast.class, 56),
    PIG_ZOMBIE("PigZombie", EntityPigZombie.class, 57),
    ENDERMAN("Enderman", EntityEnderman.class, 58),
    CAVE_SPIDER("CaveSpider", EntityCaveSpider.class, 59),
    SILVERFISH("Silverfish", EntitySilverfish.class, 60),
    BLAZE("Blaze", EntityBlaze.class, 61),
    MAGMA_CUBE("LavaSlime", EntityMagmaCube.class, 62),
    ENDER_DRAGON("EnderDragon", EntityEnderDragon.class, 63),
    WITHER("WitherBoss", EntityWither.class, 64),
    BAT("Bat", EntityBat.class, 65),
    WITCH("Witch", EntityWitch.class, 66),
    ENDERMITE("Endermite", EntityEndermite.class, 67),
    GUARDIAN("Guardian", EntityGuardian.class, 68),
    PIG("Pig", EntityPig.class, 90),
    SHEEP("Sheep", EntitySheep.class, 91),
    COW("Cow", EntityCow.class, 92),
    CHICKEN("Chicken", EntityChicken.class, 93),
    SQUID("Squid", EntitySquid.class, 94),
    WOLF("Wolf", EntityWolf.class, 95),
    MUSHROOM_COW("MushroomCow", EntityMushroomCow.class, 96),
    SNOWMAN("SnowMan", EntitySnowman.class, 97),
    OCELOT("Ozelot", EntityOcelot.class, 98),
    IRON_GOLEM("VillagerGolem", EntityIronGolem.class, 99),
    HORSE("EntityHorse", EntityHorse.class, 100),
    RABBIT("Rabbit", EntityRabbit.class, 101),
    VILLAGER("Villager", EntityVillager.class, 120),
    ENDER_CRYSTAL("EnderCrystal", EntityEnderCrystal.class, 200),
    SPLASH_POTION((String)null, EntityPotion.class, -1, false),
    EGG((String)null, EntityEgg.class, -1, false),
    FISHING_HOOK((String)null, EntityFishingHook.class, -1, false),
    LIGHTNING((String)null, EntityLightning.class, -1, false),
    WEATHER((String)null, EntityWeather.class, -1, false),
    PLAYER((String)null, EntityPlayer.class, -1, false),
    COMPLEX_PART((String)null, EntityComplexPart.class, -1, false),
    FAKE_PLAYER((String)null, EntityFakePlayer.class, -1, false),
    UNKNOWN((String)null, (Class)null, -1, false);

    private String name;
    private Class<? extends Entity> clazz;
    private short typeId;
    private boolean independent;
    private boolean living;
    private static final Map<String, LegacyEntityType> NAME_MAP = new HashMap();
    private static final Map<Short, LegacyEntityType> ID_MAP = new HashMap();

    static {
        LegacyEntityType[] var0;
        int var1 = (var0 = values()).length;

        for(int var2 = 0; var2 < var1; ++var2) {
            LegacyEntityType type = var0[var2];
            if (type.name != null) {
                NAME_MAP.put(type.name.toLowerCase(), type);
            }

            if (type.typeId > 0) {
                ID_MAP.put(type.typeId, type);
            }
        }

    }

    private LegacyEntityType(String name, Class clazz, int typeId) {
        this(name, clazz, typeId, true);
    }

    private LegacyEntityType(String name, Class clazz, int typeId, boolean independent) {
        this.name = name;
        this.clazz = clazz;
        this.typeId = (short)typeId;
        this.independent = independent;
        if (clazz != null) {
            this.living = LivingEntity.class.isAssignableFrom(clazz);
        }

    }

    @Deprecated
    public String getName() {
        return this.name;
    }

    public Class<?> getEntityClass() {
        return this.clazz;
    }

    @Deprecated
    public short getTypeId() {
        return this.typeId;
    }

    @Deprecated
    public static LegacyEntityType fromName(String name) {
        return name == null ? null : NAME_MAP.get(name.toLowerCase());
    }

    @Deprecated
    public static LegacyEntityType fromId(int id) {
        return id > 32767 ? null :  ID_MAP.get((short)id);
    }

    public boolean isSpawnable() {
        return this.independent;
    }

    public boolean isAlive() {
        return this.living;
    }
}

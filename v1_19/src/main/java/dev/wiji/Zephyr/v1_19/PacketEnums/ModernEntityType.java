package dev.wiji.Zephyr.v1_19.PacketEnums;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.util.datafix.fixes.EntityGoatMissingStateFix;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.ambient.EntityBat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderCrystal;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.entity.decoration.EntityItemFrame;
import net.minecraft.world.entity.decoration.EntityLeash;
import net.minecraft.world.entity.decoration.EntityPainting;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.npc.EntityVillagerTrader;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.entity.vehicle.ChestBoat;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftGlowSquid;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ModernEntityType implements Keyed {

    // These strings MUST match the strings in nms.EntityTypes and are case sensitive.
    /**
     * An item resting on the ground.
     * <p>
     * Spawn with {@link World#dropItem(Location, ItemStack)} or {@link
     * World#dropItemNaturally(Location, ItemStack)}
     */
    DROPPED_ITEM("item", EntityItem.class, 1, false),
    /**
     * An experience orb.
     */
    EXPERIENCE_ORB("experience_orb", EntityExperienceOrb.class, 2),
    /**
     * @see AreaEffectCloud
     */
    AREA_EFFECT_CLOUD("area_effect_cloud", EntityAreaEffectCloud.class, 3),
    /**
     * @see ElderGuardian
     */
    ELDER_GUARDIAN("elder_guardian", EntityGuardianElder.class, 4),
    /**
     * @see WitherSkeleton
     */
    WITHER_SKELETON("wither_skeleton", EntitySkeletonWither.class, 5),
    /**
     * @see Stray
     */
    STRAY("stray", EntitySkeletonStray.class, 6),
    /**
     * A flying chicken egg.
     */
    EGG("egg", EntityEgg.class, 7),
    /**
     * A leash attached to a fencepost.
     */
    LEASH_HITCH("leash_knot", EntityLeash.class, 8),
    /**
     * A painting on a wall.
     */
    PAINTING("painting", EntityPainting.class, 9),
    /**
     * An arrow projectile; may get stuck in the ground.
     */
    ARROW("arrow", EntityArrow.class, 10),
    /**
     * A flying snowball.
     */
    SNOWBALL("snowball", EntitySnowball.class, 11),
    /**
     * A flying large fireball, as thrown by a Ghast for example.
     */
    FIREBALL("fireball", EntityLargeFireball.class, 12),
    /**
     * A flying small fireball, such as thrown by a Blaze or player.
     */
    SMALL_FIREBALL("small_fireball", EntitySmallFireball.class, 13),
    /**
     * A flying ender pearl.
     */
    ENDER_PEARL("ender_pearl", EntityEnderPearl.class, 14),
    /**
     * An ender eye signal.
     */
    ENDER_SIGNAL("eye_of_ender", EntityEnderSignal.class, 15),
    /**
     * A flying splash potion.
     */
    SPLASH_POTION("potion", EntityPotion.class, 16, false),
    /**
     * A flying experience bottle.
     */
    THROWN_EXP_BOTTLE("experience_bottle", EntityThrownExpBottle.class, 17),
    /**
     * An item frame on a wall.
     */
    ITEM_FRAME("item_frame", EntityItemFrame.class, 18),
    /**
     * A flying wither skull projectile.
     */
    WITHER_SKULL("wither_skull", EntityWitherSkull.class, 19),
    /**
     * Primed TNT that is about to explode.
     */
    PRIMED_TNT("tnt", EntityTNTPrimed.class, 20),
    /**
     * A block that is going to or is about to fall.
     */
    FALLING_BLOCK("falling_block", EntityFallingBlock.class, 21, false),
    /**
     * Internal representation of a Firework once it has been launched.
     */
    FIREWORK("firework_rocket", EntityFireworks.class, 22, false),
    /**
     * @see Husk
     */
    HUSK("husk", EntityZombieHusk.class, 23),
    /**
     * Like {@link #ARROW} but causes the {@link PotionEffectType#GLOWING} effect on all team members.
     */
    SPECTRAL_ARROW("spectral_arrow", EntitySpectralArrow.class, 24),
    /**
     * Bullet fired by {@link #SHULKER}.
     */
    SHULKER_BULLET("shulker_bullet", EntityShulkerBullet.class, 25),
    /**
     * Like {@link #FIREBALL} but with added effects.
     */
    DRAGON_FIREBALL("dragon_fireball", EntityDragonFireball.class, 26),
    /**
     * @see ZombieVillager
     */
    ZOMBIE_VILLAGER("zombie_villager", EntityZombieVillager.class, 27),
    /**
     * @see SkeletonHorse
     */
    SKELETON_HORSE("skeleton_horse", EntityHorseSkeleton.class, 28),
    /**
     * @see ZombieHorse
     */
    ZOMBIE_HORSE("zombie_horse", EntityHorseZombie.class, 29),
    /**
     * Mechanical entity with an inventory for placing weapons / armor into.
     */
    ARMOR_STAND("armor_stand", EntityArmorStand.class, 30),
    /**
     * @see Donkey
     */
    DONKEY("donkey", EntityHorseDonkey.class, 31),
    /**
     * @see Mule
     */
    MULE("mule", EntityHorseMule.class, 32),
    /**
     * @see EvokerFangs
     */
    EVOKER_FANGS("evoker_fangs", EntityEvokerFangs.class, 33),
    /**
     * @see Evoker
     */
    EVOKER("evoker", EntityEvoker.class, 34),
    /**
     * @see Vex
     */
    VEX("vex", EntityVex.class, 35),
    /**
     * @see Vindicator
     */
    VINDICATOR("vindicator", EntityVindicator.class, 36),
    /**
     * @see Illusioner
     */
    ILLUSIONER("illusioner", EntityIllagerIllusioner.class, 37),
    /**
     * @see CommandMinecart
     */
    MINECART_COMMAND("command_block_minecart", EntityMinecartCommandBlock.class, 40),
    /**
     * A placed boat.
     */
    BOAT("boat", EntityBoat.class, 41),
    /**
     * @see RideableMinecart
     */
    MINECART("minecart", EntityMinecartRideable.class, 42),
    /**
     * @see StorageMinecart
     */
    MINECART_CHEST("chest_minecart", EntityMinecartChest.class, 43),
    /**
     * @see PoweredMinecart
     */
    MINECART_FURNACE("furnace_minecart", EntityMinecartFurnace.class, 44),
    /**
     * @see ExplosiveMinecart
     */
    MINECART_TNT("tnt_minecart", EntityMinecartTNT.class, 45),
    /**
     * @see HopperMinecart
     */
    MINECART_HOPPER("hopper_minecart", EntityMinecartHopper.class, 46),
    /**
     * @see SpawnerMinecart
     */
    MINECART_MOB_SPAWNER("spawner_minecart", EntityMinecartMobSpawner.class, 47),
    CREEPER("creeper", EntityCreeper.class, 50),
    SKELETON("skeleton", EntitySkeleton.class, 51),
    SPIDER("spider", EntitySpider.class, 52),
    GIANT("giant", EntityGiantZombie.class, 53),
    ZOMBIE("zombie", EntityZombie.class, 54),
    SLIME("slime", EntitySlime.class, 55),
    GHAST("ghast", EntityGhast.class, 56),
    ZOMBIFIED_PIGLIN("zombified_piglin", EntityPigZombie.class, 57),
    ENDERMAN("enderman", EntityEnderman.class, 58),
    CAVE_SPIDER("cave_spider", EntityCaveSpider.class, 59),
    SILVERFISH("silverfish", EntitySilverfish.class, 60),
    BLAZE("blaze", EntityBlaze.class, 61),
    MAGMA_CUBE("magma_cube", EntityMagmaCube.class, 62),
    ENDER_DRAGON("ender_dragon", EntityEnderDragon.class, 63),
    WITHER("wither", EntityWither.class, 64),
    BAT("bat", EntityBat.class, 65),
    WITCH("witch", EntityWitch.class, 66),
    ENDERMITE("endermite", EntityEndermite.class, 67),
    GUARDIAN("guardian", EntityGuardian.class, 68),
    SHULKER("shulker", EntityShulker.class, 69),
    PIG("pig", EntityPig.class, 90),
    SHEEP("sheep", EntitySheep.class, 91),
    COW("cow", EntityCow.class, 92),
    CHICKEN("chicken", EntityChicken.class, 93),
    SQUID("squid", EntitySquid.class, 94),
    WOLF("wolf", EntityWolf.class, 95),
    MUSHROOM_COW("mooshroom", EntityMushroomCow.class, 96),
    SNOWMAN("snow_golem", EntitySnowman.class, 97),
    OCELOT("ocelot", EntityOcelot.class, 98),
    IRON_GOLEM("iron_golem", EntityIronGolem.class, 99),
    HORSE("horse", EntityHorse.class, 100),
    RABBIT("rabbit", EntityRabbit.class, 101),
    POLAR_BEAR("polar_bear", EntityPolarBear.class, 102),
    LLAMA("llama", EntityLlama.class, 103),
    LLAMA_SPIT("llama_spit", EntityLlamaSpit.class, 104),
    PARROT("parrot", EntityParrot.class, 105),
    VILLAGER("villager", EntityVillager.class, 120),
    ENDER_CRYSTAL("end_crystal", EntityEnderCrystal.class, 200),
    TURTLE("turtle", EntityTurtle.class, -1),
    PHANTOM("phantom", EntityPhantom.class, -1),
    TRIDENT("trident", EntityThrownTrident.class, -1),
    COD("cod", EntityCod.class, -1),
    SALMON("salmon", EntitySalmon.class, -1),
    PUFFERFISH("pufferfish", EntityPufferFish.class, -1),
    TROPICAL_FISH("tropical_fish", EntityTropicalFish.class, -1),
    DROWNED("drowned", EntityDrowned.class, -1),
    DOLPHIN("dolphin", EntityDolphin.class, -1),
    CAT("cat", EntityCat.class, -1),
    PANDA("panda", EntityPanda.class, -1),
    PILLAGER("pillager", EntityPillager.class, -1),
    RAVAGER("ravager", EntityRavager.class, -1),
    TRADER_LLAMA("trader_llama", EntityLlamaTrader.class, -1),
    WANDERING_TRADER("wandering_trader", EntityVillagerTrader.class, -1),
    FOX("fox", EntityFox.class, -1),
    BEE("bee", EntityBee.class, -1),
    HOGLIN("hoglin", EntityHoglin.class, -1),
    PIGLIN("piglin", EntityPiglin.class, -1),
    STRIDER("strider", EntityStrider.class, -1),
    ZOGLIN("zoglin", EntityZoglin.class, -1),
    PIGLIN_BRUTE("piglin_brute", EntityPiglinBrute.class, -1),
    AXOLOTL("axolotl", Axolotl.class, -1),
    GLOW_ITEM_FRAME("glow_item_frame", GlowItemFrame.class, -1),
    GLOW_SQUID("glow_squid", net.minecraft.world.entity.GlowSquid.class, -1),
    GOAT("goat", Goat.class, -1),
    MARKER("marker", Marker.class, -1),
    ALLAY("allay", Allay.class, -1),
    CHEST_BOAT("chest_boat", ChestBoat.class, -1),
    FROG("frog", net.minecraft.world.entity.animal.frog.Frog.class, -1),
    TADPOLE("tadpole", Tadpole.class, -1),
    WARDEN("warden", Warden.class, -1),
    /**
     * A fishing line and bobber.
     */
    FISHING_HOOK("fishing_bobber", EntityFishingHook.class, -1, false),
    /**
     * A bolt of lightning.
     * <p>
     * Spawn with {@link World#strikeLightning(Location)}.
     */
    LIGHTNING("lightning_bolt", EntityLightning.class, -1, false),
    PLAYER("player", EntityPlayer.class, -1, false),
    /**
     * An unknown entity without an Entity Class
     */
    UNKNOWN(null, null, -1, false);

    private final String name;
    private final Class<? extends Entity> clazz;
    private final short typeId;
    private final boolean independent, living;
    private final NamespacedKey key;

    private static final Map<String, ModernEntityType> NAME_MAP = new HashMap<>();
    private static final Map<Short, ModernEntityType> ID_MAP = new HashMap<>();

    static {
        for (ModernEntityType type : values()) {
            if (type.name != null) {
                NAME_MAP.put(type.name.toLowerCase(java.util.Locale.ENGLISH), type);
            }
            if (type.typeId > 0) {
                ID_MAP.put(type.typeId, type);
            }
        }

        // Add legacy names
        NAME_MAP.put("xp_orb", EXPERIENCE_ORB);
        NAME_MAP.put("eye_of_ender_signal", ENDER_SIGNAL);
        NAME_MAP.put("xp_bottle", THROWN_EXP_BOTTLE);
        NAME_MAP.put("fireworks_rocket", FIREWORK);
        NAME_MAP.put("evocation_fangs", EVOKER_FANGS);
        NAME_MAP.put("evocation_illager", EVOKER);
        NAME_MAP.put("vindication_illager", VINDICATOR);
        NAME_MAP.put("illusion_illager", ILLUSIONER);
        NAME_MAP.put("commandblock_minecart", MINECART_COMMAND);
        NAME_MAP.put("snowman", SNOWMAN);
        NAME_MAP.put("villager_golem", IRON_GOLEM);
        NAME_MAP.put("ender_crystal", ENDER_CRYSTAL);
        NAME_MAP.put("zombie_pigman", ZOMBIFIED_PIGLIN);
    }

    private ModernEntityType(/*@Nullable*/ String name, /*@Nullable*/ Class<? extends Entity> clazz, int typeId) {
        this(name, clazz, typeId, true);
    }

    private ModernEntityType(/*@Nullable*/ String name, /*@Nullable*/ Class<? extends Entity> clazz, int typeId, boolean independent) {
        this.name = name;
        this.clazz = clazz;
        this.typeId = (short) typeId;
        this.independent = independent;
        this.living = clazz != null && LivingEntity.class.isAssignableFrom(clazz);
        this.key = (name == null) ? null : NamespacedKey.minecraft(name);
    }

    /**
     * Gets the entity type name.
     *
     * @return the entity type's name
     * @deprecated Magic value
     */
    @Deprecated
    @Nullable
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        Preconditions.checkArgument(key != null, "EntityType doesn't have key! Is it UNKNOWN?");

        return key;
    }

    @Nullable
    public Class<? extends Entity> getEntityClass() {
        return clazz;
    }

    /**
     * Gets the entity type id.
     *
     * @return the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public short getTypeId() {
        return typeId;
    }

    /**
     * Gets an entity type from its name.
     *
     * @param name the entity type's name
     * @return the matching entity type or null
     * @deprecated Magic value
     */
    @Deprecated
    @Contract("null -> null")
    @Nullable
    public static ModernEntityType fromName(@Nullable String name) {
        if (name == null) {
            return null;
        }
        return NAME_MAP.get(name.toLowerCase(java.util.Locale.ENGLISH));
    }

    /**
     * Gets an entity from its id.
     *
     * @param id the raw type id
     * @return the matching entity type or null
     * @deprecated Magic value
     */
    @Deprecated
    @Nullable
    public static ModernEntityType fromId(int id) {
        if (id > Short.MAX_VALUE) {
            return null;
        }
        return ID_MAP.get((short) id);
    }

    /**
     * Some entities cannot be spawned using {@link
     * World#spawnEntity(Location, EntityType)} or {@link
     * World#spawn(Location, Class)}, usually because they require additional
     * information in order to spawn.
     *
     * @return False if the entity type cannot be spawned
     */
    public boolean isSpawnable() {
        return independent;
    }

    public boolean isAlive() {
        return living;
    }
}

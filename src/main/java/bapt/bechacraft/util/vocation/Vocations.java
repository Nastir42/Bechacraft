package bapt.bechacraft.util.vocation;

import java.util.Collection;
import java.util.HashMap;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;

import bapt.bechacraft.Bechacraft;
import net.minecraft.util.Identifier;

@Nullable
public class Vocations {

    private static final String pathPrefix = "textures/vocation/";
    private static HashMap<Identifier, Vocation> entries = Maps.newHashMap();
    
    public static final Vocation FARMER = createAndRegisterVocation("farmer", null);
    public static final Vocation MINER = createAndRegisterVocation("miner", null);
    public static final Vocation TRAVELER = createAndRegisterVocation("traveler", null);
    public static final Vocation BUILDER = createAndRegisterVocation("builder", null);
    public static final Vocation FIGHTER = createAndRegisterVocation("fighter", null);
    public static final Vocation MAGICIAN = createAndRegisterVocation("magician", null);
    public static final Vocation BREEDER = createAndRegisterVocation("breeder", FARMER);
    public static final Vocation FISHER = createAndRegisterVocation("fisher", FARMER);
    public static final Vocation LUMBERJACK = createAndRegisterVocation("lumberjack", FARMER);
    public static final Vocation TILLER = createAndRegisterVocation("tiller", FARMER);
    public static final Vocation GEOLOGIST = createAndRegisterVocation("geologist", MINER);
    public static final Vocation BLACKSMITH = createAndRegisterVocation("blacksmith", MINER);
    public static final Vocation EXPLORATOR = createAndRegisterVocation("explorator", TRAVELER);
    public static final Vocation MERCHANT = createAndRegisterVocation("merchant", TRAVELER);
    public static final Vocation ADVENTURER = createAndRegisterVocation("adventurer", TRAVELER);
    public static final Vocation SAILOR = createAndRegisterVocation("sailor", TRAVELER);
    public static final Vocation ENGINEER = createAndRegisterVocation("engineer", BUILDER);
    public static final Vocation CRAFTMAN = createAndRegisterVocation("craftman", BUILDER);
    public static final Vocation MARKSMAN = createAndRegisterVocation("marksman", FIGHTER);
    public static final Vocation WARRIOR = createAndRegisterVocation("warrior", FIGHTER);
    public static final Vocation HUNTER = createAndRegisterVocation("hunter", FIGHTER);
    public static final Vocation BRUTE = createAndRegisterVocation("brute", FIGHTER);
    public static final Vocation ASSASSIN = createAndRegisterVocation("assassin", FIGHTER);
    public static final Vocation WIZARD = createAndRegisterVocation("wizard", MAGICIAN);
    public static final Vocation CHARMER = createAndRegisterVocation("charmer", MAGICIAN);
    public static final Vocation ALCHEMIST = createAndRegisterVocation("alchemist", MAGICIAN);
    public static final Vocation SHEPHERD = createAndRegisterVocation("shepherd", BREEDER);
    public static final Vocation ANGLER = createAndRegisterVocation("angler", FISHER);
    public static final Vocation BOTANIST = createAndRegisterVocation("botanist", TILLER);
    public static final Vocation GATHERER = createAndRegisterVocation("gatherer", LUMBERJACK);
    public static final Vocation CRYSTALLOGRAPHER = createAndRegisterVocation("crystallographer", GEOLOGIST);
    public static final Vocation WEAPONSMITH = createAndRegisterVocation("weaponmaster", BLACKSMITH);
    public static final Vocation ARMORER = createAndRegisterVocation("armorer", BLACKSMITH);
    public static final Vocation MEDIC = createAndRegisterVocation("medic", WIZARD);
    public static final Vocation ATLANT = createAndRegisterVocation("sailor", SAILOR);
    public static final Vocation FAIRY = createAndRegisterVocation("fairy", CHARMER);
    public static final Vocation TRADER = createAndRegisterVocation("trader", MERCHANT);
    
    public static Vocation createAndRegisterVocation(String name, Vocation parent) {
        return registerVocation(name, new Vocation(
            name,
            new Identifier(Bechacraft.MOD_ID, pathPrefix + name + ".png"),
            parent
        ));
    }
    
    public static Vocation registerVocation(String name, Vocation vocation) {
        return entries.put(new Identifier(Bechacraft.MOD_ID, name),
            vocation);
    }

    public static Collection<Vocation> all() {
        return entries.values();
    }

    public static Vocation fromName(String name) {
        return entries.get(new Identifier(Bechacraft.MOD_ID, name));
    }
}

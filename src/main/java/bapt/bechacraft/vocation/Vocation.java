package bapt.bechacraft.vocation;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import bapt.bechacraft.Bechacraft;
import bapt.bechacraft.util.IEntityDataSaver;
import bapt.bechacraft.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Vocation {

    //private static final String ICON_PATH_PREFIX = "textures/vocation/";

    private String name;
    private final Identifier id;
    //private Identifier iconId;
    private Vocation parent;
    private String translationKey;

    public Vocation(String name, Vocation parent, Identifier id) {
        this.name = name;
        //this.iconId = new Identifier(Bechacraft.MOD_ID, ICON_PATH_PREFIX + name + ".png");
        this.parent = parent;
        this.id = id;
    }

    public void onStart(PlayerEntity player, Vocation oldVocation) {
        if(this != Vocations.NONE) {
            player.sendMessage(Text.translatable("msg.bechacraft.vocation_start").append(getDisplayName()));
        }
    }

    public void onStop(PlayerEntity player, Vocation newVocation) { }

    public boolean unlocked(PlayerEntity player) {
        return true;
    }

    public Vocation getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Text getDisplayName() {
        return Text.translatable(this.getTranslationKey());
    }

    public Identifier getId() {
        return id;
    }

    protected String getOrCreateTranslationKey() {
        if (this.translationKey == null) {
            this.translationKey = Util.createTranslationKey("vocation", new Identifier(Bechacraft.MOD_ID, this.getName()));
        }
        return this.translationKey;
    }

    public String getTranslationKey() {
        return this.getOrCreateTranslationKey();
    }

    public List<Vocation> getChildren() {
        List<Vocation> children = new ArrayList<Vocation>();

        for(Vocation voc : Vocations.all())
            if(voc.getParent() == this)
                children.add(voc);
        
        return children;
    }

    @Nullable
    public static Vocation fromName(String name) {
        return Vocations.fromName(name);
    }

    public static void set(PlayerEntity player, Vocation vocation) {

        Vocation lastVocation = VocationData.getVocation((IEntityDataSaver) player);
        lastVocation.onStop(player, vocation);

        VocationData.setVocation((IEntityDataSaver) player, vocation);
        vocation.onStart(player, lastVocation);
    }

    public static Vocation get(PlayerEntity player) {
        return VocationData.getVocation((IEntityDataSaver) player);
    }

    public static void reset(PlayerEntity player) {
        if(Vocation.get(player) == Vocations.NONE) {
            player.sendMessage(Text.translatable("msg.bechacraft.reset_vocation_fail"));
        } else {
            Vocation.set(player, Vocations.NONE);
            player.sendMessage(Text.translatable("msg.bechacraft.reset_vocation_success"));
        }
    }

    public static void sendInfo(PlayerEntity player, Vocation vocation) {

        if(vocation == Vocations.NONE)
            player.sendMessage(Text.translatable("msg.bechacraft.no_vocation_yet"));
        else
            player.sendMessage(Text.translatable("msg.becharcaft.vocation_info_name").append(" : " + vocation.getDisplayName()));
    }

    private class VocationData {

        private static String KEY = "vocation";
    
        public static Vocation setVocation(IEntityDataSaver player, Vocation vocation) {

            if(vocation == null) {
                Bechacraft.LOGGER.info("Vocation not set, resetting");
                return setVocation(player, Vocations.NONE);
            }

            NbtCompound nbt = player.getPersistentData();
            nbt.putString(KEY, vocation.getName());
            
            return vocation;
        }
    
        public static Vocation getVocation(IEntityDataSaver player) {
            NbtCompound nbt = player.getPersistentData();
            return setVocation(player, Vocation.fromName(nbt.getString(KEY)));
        }
    }
}
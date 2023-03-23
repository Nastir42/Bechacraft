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

    private String name;
    private Identifier iconId;
    private Vocation parent;
    @Nullable
    private String translationKey;

    public Vocation(String name, Identifier iconId, Vocation parent) {
        this.name = name;
        this.iconId = iconId;
        this.parent = parent;
    }

    public void onStart(PlayerEntity player) {
        player.sendMessage(Text.literal("msg.bechacraft.vocation_start").append(getDisplayName()));
    }

    public void onStop(PlayerEntity player) { }

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
        lastVocation.onStop(player);

        VocationData.setVocation((IEntityDataSaver) player, vocation);
        vocation.onStart(player);
    }

    public static Vocation get(PlayerEntity player) {
        return VocationData.getVocation((IEntityDataSaver) player);
    }

    private class VocationData {

        private static String KEY = "vocation";
    
        public static Vocation setVocation(IEntityDataSaver player, Vocation vocation) {
            NbtCompound nbt = player.getPersistentData();
    
            if(vocation != null)
                nbt.putString(KEY, vocation.getName());
            
            return vocation;
        }
    
        public static Vocation getVocation(IEntityDataSaver player) {
            NbtCompound nbt = player.getPersistentData();
            return Vocation.fromName(nbt.getString(KEY));
        }
    
    }
}

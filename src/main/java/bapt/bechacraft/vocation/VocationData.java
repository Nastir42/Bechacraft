package bapt.bechacraft.vocation;

import bapt.bechacraft.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;

public class VocationData {

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

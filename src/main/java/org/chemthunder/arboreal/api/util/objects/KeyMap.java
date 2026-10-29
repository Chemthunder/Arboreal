package org.chemthunder.arboreal.api.util.objects;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class KeyMap<Key, Val> {
    private final List<Entry<Key, Val>> mappings;

    public KeyMap() {
        this.mappings = new ArrayList<>();
    }

    public void put(Key key, Val value) {
        this.mappings.add(new Entry<>(key, value));
    }

    public void removeFromKey(Key key) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.key() == key) {
                this.mappings.remove(entry);
                break;
            }
        }
    }

    public void removeFromVal(Val value) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.value() == value) {
                this.mappings.remove(entry);
                break;
            }
        }
    }

    @Nullable
    public Val getFromKey(Key key) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.key() == key) {
                return entry.value();
            }
        }
        return null;
    }

    @Nullable
    public Key getFromVal(Val val) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.value() == val) {
                return entry.key();
            }
        }
        return null;
    }

    public boolean containsKey(Key key) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.key() == key) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Val value) {
        for (Entry<Key, Val> entry : this.mappings) {
            if (entry.value() == value) {
                return true;
            }
        }
        return false;
    }

    public List<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (Entry<Key, Val> entry : this.mappings) {
            list.add(entry.key());
        }
        return list;
    }

    public List<Val> values() {
        List<Val> list = new ArrayList<>();
        for (Entry<Key, Val> entry : this.mappings) {
            list.add(entry.value());
        }
        return list;
    }

    public List<Entry<Key, Val>> entries() {
        return new ArrayList<>(this.mappings);
    }

    public boolean isEmpty() {
        return this.mappings.isEmpty();
    }

    public int size() {
        return this.mappings.size();
    }

    public void forEach(BiConsumer<Key, Val> func) {
        this.mappings.forEach(keyValEntry ->
                func.accept(
                        keyValEntry.key(),
                        keyValEntry.value()
                )
        );
    }

    public record Entry<K, V>(K key, V value) {}
}

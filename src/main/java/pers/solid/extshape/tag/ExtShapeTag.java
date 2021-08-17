package pers.solid.extshape.tag;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ExtShapeTag<T> implements Iterable<T> {
    // 数据包内的标签
    public final Identifier identifier;
    public final List<TagEntry<T>> entryList;
    public boolean replace;

    public ExtShapeTag(List<T> list) {
        this(null, list);
    }

    public ExtShapeTag(Identifier identifier) {
        this(identifier, new ArrayList<>());
    }

    public ExtShapeTag(Identifier identifier, List<T> list) {
        this.identifier = identifier;
        this.entryList = new ArrayList<>();
        list.forEach(e -> this.entryList.add(new TagEntry<>(e)));
        replace = false;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public void add(T element) {
        if (this.directlyContains(element)) throw new RuntimeException(String.format("Cannot add a duplicate element " +
                "to a tag! %s already contains %s.", this, element));
        this.entryList.add(new TagEntry<>(element));
    }

    public void addTag(ExtShapeTag<T> element) {
        if (this.identifier != null && element.identifier == null)
            throw new IllegalStateException("Cannot add a tag without " +
                    "identifier" +
                    " to a tag with " +
                    "an identifier");
        if (element == this) throw new RuntimeException("Cannot add to a tag itself!");
        if (element.containsTag(this))
            throw new RuntimeException(String.format("Cannot add into this tag another tag that contains" +
                    " it! %s already contains %s.", element, this));
        if (this.directlyContainsTag(element))
            throw new RuntimeException(String.format("Cannot add a duplicate tag to a tag! %s " +
                    "already contains %s.", this, element));
        this.entryList.add(new TagEntry<>(element));
    }

    @SafeVarargs
    public final void addAll(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    @SafeVarargs
    public final void addAllTags(ExtShapeTag<T>... elements) {
        for (ExtShapeTag<T> element : elements) {
            addTag(element);
        }
    }

    public ExtShapeTag<T> addToTag(ExtShapeTag<T> tag) {
        tag.addTag(this);
        return this;
    }

    public boolean contains(T element) {
        for (TagEntry<T> entry : entryList) if (entry.contains(element)) return true;
        return false;
    }

    public boolean containsTag(ExtShapeTag<T> tag) {
        for (TagEntry<T> entry :
                entryList)
            if (entry.elementTag != null && (entry.elementTag == tag || entry.elementTag.containsTag(tag))) return true;
        return false;
    }

    public boolean directlyContains(T element) {
        for (TagEntry<T> entry : entryList) if (entry.element == element) return true;
        return false;
    }

    public boolean directlyContainsTag(ExtShapeTag<T> tag) {
        for (TagEntry<T> entry : entryList) if (entry.elementTag == tag) return true;
        return false;
    }

    public Identifier getIdentifierOf(T element) {
        return null;
    }

    public JsonObject generateJson() {
        JsonObject main = new JsonObject();
        main.addProperty("replace", replace);

        JsonArray values = new JsonArray();

        for (TagEntry<T> entry : this.entryList) {
            if (entry.isTag) values.add("#" + entry.elementTag.getIdentifier());
            else values.add(getIdentifierOf(entry.element).toString());
        }

        main.add("values", values);
        return main;
    }

    public String generateString() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        jsonWriter.setIndent("  ");
        try {
            Streams.write(this.generateJson(), jsonWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    public List<T> asList() {
        List<T> list = new ArrayList<>();
        for (final T element : this) list.add(element);
        return list;
    }

    public void rawForEach(Consumer<? super T> action) {
        for (TagEntry<T> entry : entryList) {
            if (entry.isTag) {
                entry.elementTag.rawForEach(action);
            } else {
                action.accept(entry.element);
            }
        }
    }

    public List<T> rawToList() {
        List<T> list = new ArrayList<>();
        this.rawForEach(list::add);
        return list;
    }

    public int size() {
        int size = 0;
        for (TagEntry<T> entry : this.entryList) size += entry.size();
        return size;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int entryListSize = entryList.size();
            private int cursor = -1;
            private @Nullable Iterator<T> iteratingEntryIterator = null;

            @Override
            public boolean hasNext() {
                if (cursor == entryListSize) return false;
                while (iteratingEntryIterator == null || !iteratingEntryIterator.hasNext()) {
                    cursor++;
                    if (cursor == entryListSize) return false;
                    iteratingEntryIterator = entryList.get(cursor).iterator();
                }
                return true;
            }

            @Override
            public T next() {
                while (iteratingEntryIterator == null || !iteratingEntryIterator.hasNext()) {
                    cursor++;
                    iteratingEntryIterator = entryList.get(cursor).iterator();
                }
                return iteratingEntryIterator.next();
            }
        };
    }

    @Override
    public String toString() {
        return "ExtShapeTag{" + identifier +
                '}';
    }

    public T getEntry(int index) {
        // 获取EntryList里面的项目。如果这个Entry为Tag，则返回这个Tag的第一个元素。
        return this.entryList.get(index).get(0);
    }

    protected static class TagEntry<T> implements Iterable<T> {
        final T element;
        final ExtShapeTag<T> elementTag;
        final boolean isTag;

        TagEntry(T element) {
            this.element = element;
            this.elementTag = null;
            this.isTag = false;
        }

        TagEntry(ExtShapeTag<T> elementTag) {
            this.elementTag = elementTag;
            this.element = null;
            this.isTag = true;
        }

        boolean contains(T element) {
            if (this.isTag) {
                assert this.elementTag != null;
                return this.elementTag.contains(element);
            } else {
                return this.element == element;
            }
        }

        public int size() {
            if (this.isTag) {
                assert this.elementTag != null;
                return this.elementTag.size();
            } else return 1;
        }

        public T get(int index) {
            if (this.isTag) {
                assert this.elementTag != null;
                return this.elementTag.entryList.get(index).get(0);
            } else if (index == 0) return this.element;
            else throw new IndexOutOfBoundsException("The single element entry has only index 0.");
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            return new Iterator<>() {

                int cursor = -1;

                @Override
                public boolean hasNext() {
                    if (!isTag) return cursor == -1;
                    else {
                        assert elementTagIterator != null;
                        return elementTagIterator.hasNext();
                    }
                }

                @Override
                public T next() {
                    cursor++;
                    if (!isTag) {
                        return element;
                    } else {
                        assert elementTagIterator != null;
                        return elementTagIterator.next();
                    }
                }

                @Nullable
                final Iterator<T> elementTagIterator = elementTag == null ? null : elementTag.iterator();


            };
        }
    }

}

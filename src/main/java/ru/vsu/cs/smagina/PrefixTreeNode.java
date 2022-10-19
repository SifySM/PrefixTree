package ru.vsu.cs.smagina;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class PrefixTreeNode {
    static final char EMPTY_KEY = '\0';
    private final char key;
    private final Map<Character, PrefixTreeNode> children;
    private boolean isEndOfString = false;

    private PrefixTreeNode(char key, @NotNull String string) {
        this.key = key;
        children = new HashMap<>();

        if (string.isEmpty()) {
            isEndOfString = true;
        } else {
            add(string);
        }
    }

    static PrefixTreeNode rootPrefixTreeNode() {
        return new PrefixTreeNode(EMPTY_KEY, "");
    }

    boolean add(@NotNull String string) {
        if (string.isEmpty()) {
            boolean contained = isEndOfString;
            isEndOfString = true;

            return !contained;
        }

        char nextKey = string.charAt(0);

        if (children.containsKey(nextKey)) {
            return children.get(nextKey).add(string.substring(1));
        }

        children.put(nextKey, new PrefixTreeNode(nextKey, string.substring(1)));
        return true;
    }

    boolean remove(@NotNull String string) {
        if (string.isEmpty()) {
            boolean contained = isEndOfString;

            isEndOfString = false;
            return contained;
        }

        char nextKey = string.charAt(0);
        if (!children.containsKey(nextKey))
            return false;

        return children.get(nextKey).remove(string.substring(1));
    }

    @Nullable PrefixTreeNode findChildByPrefix(@NotNull String prefix) {
        if (prefix.isEmpty())
            return this;

        char nextKey = prefix.charAt(0);

        if (!children.containsKey(nextKey))
            return null;

        return children.get(nextKey).findChildByPrefix(prefix.substring(1));
    }

    @NotNull Collection<String> getAllStringsForThisBranch(@NotNull Collection<String> strings, @NotNull StringBuilder prefix) {
        prefix.append(key);
        if (isEndOfString) {
            strings.add(prefix.toString());
        }

        for (PrefixTreeNode PrefixTreeNode : children.values()) {
            PrefixTreeNode.getAllStringsForThisBranch(strings, prefix);
        }
        prefix.deleteCharAt(prefix.length() - 1);

        return strings;
    }
}
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;

public class PrefixTree {
    private final PrefixTreeNode root;

    public PrefixTree() {
        this.root = PrefixTreeNode.rootPrefixTreeNode();
    }

    public boolean add(@NotNull String string) {
        if (string.isEmpty())
            return false;

        return root.add(string);
    }

    public boolean remove(@NotNull String string) {
        if (string.isEmpty())
            return true;

        return root.remove(string);
    }

    public boolean find(@NotNull String string) {
        return getPrefixTreeNodeByPrefix(string) != null;
    }

    @NotNull
    public Collection<String> findAll(@NotNull String prefix) {
        PrefixTreeNode prefixedPrefixTreeNode = getPrefixTreeNodeByPrefix(prefix);
        if (prefixedPrefixTreeNode == null || prefixedPrefixTreeNode == root)
            return new HashSet<>();

        return prefixedPrefixTreeNode.getAllStringsForThisBranch(new HashSet<>(), new StringBuilder(prefix.substring(0, prefix.length() - 1)));
    }

    @Nullable
    private PrefixTreeNode getPrefixTreeNodeByPrefix(@NotNull String prefix) {
        return root.findChildByPrefix(prefix);
    }
}

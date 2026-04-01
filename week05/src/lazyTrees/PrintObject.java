package lazyTrees;

public class PrintObject<E> extends Traverser<E> {
    @Override
    public void visit(E node) {
        System.out.println(node.toString());
    }
}

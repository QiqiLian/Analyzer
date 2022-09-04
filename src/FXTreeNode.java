//113187306
//Qiqi Lian
//Recitation 4
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * This is the class containing every aspect of the tree
 */
public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    private final int maxChildren = 10;


    /**
     * @param text This method contains the main componenents for the specific treenode
     * @param type
     * @param parent
     */
    public FXTreeNode(String text, ComponentType type, FXTreeNode parent) {
        this.text = text;
        this.type = type;
        this.parent = parent;
        this.children = new FXTreeNode[maxChildren];
    }


    /**
     * @param text This method sets the text for the nodes
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param type This method sets the type for the nodes
     */
    public void setType(ComponentType type) {
        this.type = type;
    }

    /**
     * @param parent This method sets the parent for the tree
     */
    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }

    /**
     * @param children This method sets the children for the tree
     */
    public void setChildren(FXTreeNode[] children) {

        this.children = children;
    }

    /**
     * @return This method is the getter for the text of the nodes
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return This method gets the component type of each node
     */
    public ComponentType getType() {
        return this.type;
    }

    /**
     * @return This method gets the parent for the tree
     */
    public FXTreeNode getParent() {
        return this.parent;
    }

    /**
     * @return This method gets the children for the tree
     */
    public FXTreeNode[] getChildren() {
        return this.children;

    }

    /**
     * @param num This method is used to print the tree recursively
     * @param cursor
     * @return
     */
    public FXTreeNode printTree(int num, FXTreeNode cursor) {
        String spaces = "";
        for (int i = 0; i < num; i++) {
            spaces = spaces + " ";
        }
        String newCursor;
        if (cursor == this) {
            newCursor = "-->";
        } else {
            newCursor = "--+";
        }
        System.out.println(spaces + newCursor + toString());
        int i = 0;

        while (children[i] != null) {
            children[i].printTree(num++, cursor);
            i++;
        }
        return null;


    }


    /**
     * @return This is the toString method showing where the cursor is pointing to
     */
    @Override
    public String toString() {
        return  type  + " : " + text  ;
    }
  }

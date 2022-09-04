//113187306
//Qiqi Lian
//Recitation 4
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This method declares the variable for root and cursor private
 */
public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;


    /**
     * This method automatically sets the firstline as Anchorpane and as the root
     * and also setting the current cursor location to the root
     */
    public FXComponentTree (){
        this.root = new FXTreeNode("", ComponentType.AnchorPane, null);
        this.cursor = this.root;
    }

    /**
     * @param cursor This method sets the cursor
     */
    public void setCursor(FXTreeNode cursor) {
        this.cursor = cursor;
    }

    /**
     * @param root This method sets the root
     */
    public void setRoot(FXTreeNode root) {
        this.root = root;
    }

    /**
     * @return This method gets the cursor
     */
    public FXTreeNode getCursor() {
        return this.cursor;
    }

    /**
     * @return this method gets the root
     */
    public FXTreeNode getRoot() {
        return this.root;
    }

    /**
     * This methods sets the cursor to the root
     */
    public void cursortoRoot(){
        if(root == null){
            throw new EmptyStackException();
        }
        else this.cursor = root;


    }

    /**
     * @param index This method deletes the child
     */
    public void deleteChild(int index){
        for (int i = (index); i < this.cursor.getChildren().length - 1; i++) {
            this.cursor.getChildren()[i] = this.cursor.getChildren()[i + 1];
        }

        cursor.getChildren()[index] = null;



    }


    /**
     * @param child This method adds a new child at the given index
     * @param index
     */
    public void addChild(FXTreeNode child, int index){
        int counter = 0;
        for (int i = 0; this.cursor.getChildren()[counter]!= null; i++){
            counter++;
        }
        if (this.cursor.getChildren()[counter] == null){
        for (int i = counter; i > 0; i--)
            this.cursor.getChildren()[i] = this.cursor.getChildren()[i - 1];
        }
        cursor.getChildren()[index] = child;


    }

    /**
     * @param text This method sets and edits the text for the child
     */
    public void setCursorAtText(String text){
        this.cursor.setText(text);
    }

    /**
     * @param index This method moves the cursor to the child at a given index
     */
    public void CursorToChild(int index){
        this.cursor = this.cursor.getChildren()[index-1];

    }

    /**
     * This method sets the cursor to the parent
     */
    public void CursorToParent(){
        this.cursor = this.cursor.getParent();

    }

    /**
     * @param file This method reads the text from the file given from userinput
     * @return
     */
    public static FXComponentTree readFromFile (String file) {
        try {
            File files = new File(file);
            Scanner stdin = new Scanner(files);
            stdin.nextLine();
            FXComponentTree componentTree = new FXComponentTree();

            while (stdin.hasNextLine()) {
                String line = stdin.nextLine();
                String[] strings = line.split(" ", 3);
                String index = strings[0];
                String[] roots = index.split("-");
                Integer[] indexs = new Integer[roots.length];
                for (int i = 0; i <= roots.length - 1; i++) {
                    indexs[i] = Integer.parseInt(roots[i]);
                }

                ComponentType componentType = ComponentType.valueOf(strings[1]);
                String text = "";
                if (strings.length == 3) {
                    text = strings[2];
                }

                FXTreeNode newCursor = componentTree.root;

                for (int j = 1; j < indexs.length - 1; j++) {
                    newCursor = newCursor.getChildren()[indexs[j]];
                }

                FXTreeNode[] tempChild = newCursor.getChildren();
                FXTreeNode addChild = new FXTreeNode(text, componentType, newCursor);
                tempChild[indexs[indexs.length - 1]] = addChild;
                addChild.setParent(newCursor);


                newCursor.setChildren(tempChild);

            }

            stdin.close();
            return componentTree;
        }


        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("File not found");
            return null;
        }
    }


    /**
     * @param tree This method writes the current text into a saved file
     * @param filename
     */
    public static void writeToFile (FXComponentTree tree, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            String returnString = tree.preorder(tree.root,"0",writer);
            String [] lines = returnString.split(System.lineSeparator());
            for(int i = 0; i< lines.length; i++){
                writer.println(lines[i]);
                writer.close();
            }



        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param node This method traverses the tree in a preorder formation
     * @param s
     * @param writer
     * @return
     */
    public String preorder (FXTreeNode node, String s, PrintWriter writer){
        if(node == null){
            return "";
        }
        int i = 0;
        String str = "";
        str += s +" " + node.getType() + " " + node.getText() + "\n";
        if(node.getChildren()==null)return str;
        while (node.getChildren()[i]!= null){
            str += preorder(node.getChildren()[i],s + "-" + i,writer);
            i++;
        }
        return str;
    }


    /**
     * @return This is the toString method
     */
    @Override
    public String toString() {
            return "FXComponentTree{" +
                    "root=" + root +
                    ", cursor=" + cursor +
                    '}';

    }
}

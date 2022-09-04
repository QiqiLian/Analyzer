//113187306
//Qiqi Lian
//Recitation 4
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
//when typing in the file, just type in the name, no need for the .txt; for example "text"
public class FXGUIMaker {
    public static void main (String[]args) {
        FXComponentTree fxComponentTree = new FXComponentTree();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Welcome to counterfeit SceneBuilder.\n" +
                "\n" +
                "\n" +
                "Menu:\n" +
                "\n" +
                "    L) Load from file\n" +
                "\n" +
                "    P) Print tree\n" +
                "\n" +
                "    C) Move cursor to a child node\n" +
                "\n" +
                "    R) Move cursor to root\n" +
                "\n" +
                "    A) Add a child\n" +
                "\n" +
                "    E) Edit text of cursor\n" +
                "\n" +
                "    D) Delete child\n" +
                "\n" +
                "    S) Save to file\n" +
                "\n" +
                "    X) Export FXML //Works the same as save, extra credit\n" +
                "\n" +
                "    Q) Quit");

        System.out.println();
        String a;
        do {
            System.out.println("Please select an option: ");

        a = stdin.nextLine().toUpperCase();
            switch (a) {
                case "L":
                    System.out.println("Please enter file name: ");
                    String x = stdin.nextLine();
                    fxComponentTree = FXComponentTree.readFromFile(x);
                    break;

                case "P":
                    fxComponentTree.getRoot().printTree(0, fxComponentTree.getCursor());
                    System.out.println(fxComponentTree.toString());
                    break;

                case "R":
                    fxComponentTree.cursortoRoot();
                    System.out.println("Cursor is at root");
                    break;


                case "C":
                    System.out.println("Please enter the number of the child (starting with 1): ");
                    int b = stdin.nextInt();
                    fxComponentTree.CursorToChild(b);
                    System.out.println("Cursor moved to " + fxComponentTree.toString());
                    break;

                case "A":
                    System.out.println("Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label): ");
                    char c = stdin.nextLine().charAt(0);
                    ComponentType componentType = null;
                    if (c == 'H') {
                        componentType = ComponentType.HBox;
                    }
                    if (c == 'V') {
                        componentType = ComponentType.VBox;
                    }
                    if (c == 'T') {
                        componentType = ComponentType.TextArea;
                    }
                    if (c == 'B') {
                        componentType = ComponentType.Button;
                    }
                    if (c == 'L') {
                        componentType = ComponentType.Label;
                    }

                    System.out.println("Please enter text: ");
                    String d = stdin.nextLine();


                    FXTreeNode treeNode = new FXTreeNode(d, componentType, fxComponentTree.getCursor());
                    System.out.println("Please enter the index: ");
                    int e = stdin.nextInt();
                    fxComponentTree.addChild(treeNode, e - 1);
                    break;


                case "D":
                    System.out.println("Please enter the number of the child (starting with 1): ");
                    int f = stdin.nextInt();
                    fxComponentTree.deleteChild(f + 1);
                    break;

                case "U":
                    fxComponentTree.CursorToParent();
                    System.out.println("Cursor moved: "+ fxComponentTree.toString());
                    System.out.println("Cursor moved to parent");
                    break;

                case "E":
                    System.out.println("Please enter new text: ");
                    String z = stdin.nextLine();
                    fxComponentTree.setCursorAtText(z);
                    break;

                case "S":
                    System.out.println("Please enter file name: ");
                    String p = stdin.nextLine();
                    FXComponentTree.writeToFile(fxComponentTree, p);
                    break;

                case "Q":
                    System.out.println("Make like a tree and leave!\n");
                    stdin.close();




            }
        }while (a != "Q") ;
        System.out.println("Make like a tree and leave!\n");
        stdin.close();

    }

}

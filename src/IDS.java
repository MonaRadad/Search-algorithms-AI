
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IDS {

    Visualizer visualizer = new Visualizer();

    public void search(Node intialNode) {
        //cutoff=0
        if (isGoal(intialNode)) {  result(intialNode);
            return;
        }

        int cutoff = 0;
        for (int j = 0; j <= 10000; j++) {
            Stack<Node> frontier = new Stack<Node>();
            Hashtable<String, Boolean> inFrontier = new Hashtable<>();
            Hashtable<String, Boolean> explored = new Hashtable<>();
            cutoff++;
            frontier.push(intialNode);
            inFrontier.put(intialNode.hash(), true);
            while (!frontier.isEmpty()) {
                Node temp = frontier.pop();
                inFrontier.remove(temp.hash());
                explored.put(temp.hash(), true);
                if (temp.depth < cutoff) {
                    ArrayList<Node> children = temp.successor();
                    for (int i = 0; i < children.size(); i++) {
                        if (!(inFrontier.containsKey(children.get(i).hash())) && !(explored.containsKey(children.get(i).hash()))) {
                            if (isGoal(children.get(i))) {
                                result(children.get(i));
                                return;
                            }
                            frontier.push(children.get(i));
                            inFrontier.put(children.get(i).hash(), true);

                        }
                    }
                }

            }
        }

    }

    public boolean isGoal(Node node) {
        if (node.map.at(node.player.i, node.player.j).name == 'C') {
            return true;
        } else {
            return false;
        }
    }

    public void result(Node node) {
        Stack<Node> nodes = new Stack<Node>();
        while (true) {
            nodes.push(node);
            if (node.parentNode == null) {
                break;
            } else {
                node = node.parentNode;
            }
        }
        nodes.pop();
        try {
            FileWriter myWriter = new FileWriter("IDSResult.txt");
            while (!nodes.empty()) {
                Node tempNode = nodes.pop();
                String action = tempNode.priviousAction + " " + tempNode.player.money + " " + tempNode.player.food;
                System.out.println(action);
                myWriter.write(action + "\n");
                //print visualized map for every movement
                visualizer.printMap(tempNode.map, tempNode.player);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IDAstar {
    Node goal;
    Node initialNode;
    Visualizer visualizer = new Visualizer();
    boolean achieve = false;
    int cutoff;


    public IDAstar(Node initialNode, Node goal) {
        this.goal = goal;
        this.initialNode = initialNode;
    }

    public int totalCost(Node node) {
        return h(node) + g(node);
    }

    public int h(Node node) {
        int i = Math.abs(goal.player.i - node.player.i);
        int j = Math.abs(goal.player.j - node.player.j);
        return i + j;
    }

    public int g(Node node) {
        int i = Math.abs(initialNode.player.i - node.player.i);
        int j = Math.abs(initialNode.player.j - node.player.j);
        return i + j;
    }

    public void search() {
        cutoff = totalCost(initialNode);
        while (cutoff != Integer.MAX_VALUE && this.achieve != true) {
            DFS(initialNode);
        }
    }

    public void DFS(Node node) {
        Stack<Node> frontier = new Stack<>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        if (isGoal(node)) {
            this.achieve = true;
            result(node);
            return;
        }
        frontier.push(node);
        inFrontier.put(node.hash(), true);
        while (!frontier.isEmpty()) {
            Node temp = frontier.peek();
            if (totalCost(temp) > cutoff) {
                cutoff = findMin(frontier);
            }
            temp = frontier.pop();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(), true);
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
                if (!(explored.containsKey(child.hash())) && !(inFrontier.containsKey(child.hash()))) {
                    if (isGoal(child)) {
                        this.achieve = true;
                        result(child);
                        return;
                    }
                    frontier.push(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }
    }

    public int findMin(Stack frontier) {
        int min = Integer.MAX_VALUE;
        Iterator iterator = frontier.iterator();
        while (iterator.hasNext()) {
            Node mapElement = (Node) iterator.next();
            int f = totalCost(mapElement);
            if (min > f) {
                min = f;
            }
        }
        return min;
    }

    public boolean isGoal(Node node) {
        return node.map.at(node.player.i, node.player.j).name == 'C';
    }

    public void result(Node node) {
        Stack<Node> nodes = new Stack<>();
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
            System.out.println("IDASTAR result: ");
            FileWriter myWriter = new FileWriter("IDAstarResult.txt");
            while (!nodes.empty()) {
                Node tempNode = nodes.pop();
                String action = tempNode.priviousAction + " " + tempNode.player.money + " " + tempNode.player.food;
                System.out.println(action);
                myWriter.write(action + "\n");
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

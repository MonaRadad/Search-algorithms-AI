import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.*;

public class Astar {
    Node goal;
    Node initialNode;
    Visualizer visualizer = new Visualizer();

    public Astar(Node initialNode,Node goal) {
        this.goal = goal;
        this.initialNode = initialNode;
    }

    public int g(Node node) {
        int i = Math.abs(initialNode.player.i - node.player.i);
        int j = Math.abs(initialNode.player.j - node.player.j);
        return i + j;
    }

    public int h(Node node) {
        int i = Math.abs(goal.player.i - node.player.i);
        int j = Math.abs(goal.player.j - node.player.j);
        return i + j;
    }

    public void search() {
        HashMap<Node, Integer> frontier = new HashMap<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        Queue<Node> route = new LinkedList<>();
        if (isGoal(this.initialNode)) {
            result(this.initialNode);
            return;
        }
        frontier.put(this.initialNode,g(this.initialNode)+ h(this.initialNode));
        Node best = findMin(frontier);
        route.add(best);
        while (!frontier.isEmpty()) {
            frontier.remove(best);
            explored.put(best.hash(), true);
            ArrayList<Node> children = best.successor();
            for (Node child : children) {
                if (!(frontier.containsKey(child)) && !(explored.containsKey(child.hash()))) {
                    if (isGoal(child)) {
                        result(child);
                        return;
                    }
                    frontier.put(child,g(child)+ h(child));
                }
            }
            best = findMin(frontier);
            route.add(best);
        }
    }

    public Node findMin(HashMap<Node, Integer> frontier) {
        Node best = null;
        int min = 10000;
        Iterator iterator = frontier.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) iterator.next();
            int g = (int) mapElement.getValue();
            if (min > g ) {
                min = g ;
                best = (Node) mapElement.getKey();
            }
        }
        return best;
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
            FileWriter myWriter = new FileWriter("AstarResult.txt");
            System.out.println("Astar result: ");
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

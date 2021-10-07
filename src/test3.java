public class test3  {
    public static void main(String[] args) {
//        Player player = new Player(0,1,100,100);
        Visualizer visualizer = new Visualizer();
        //indexes start from zero
//        Player player = new Player(2,5,100,100);
        Player player = new Player(0,4,5,105);
        System.out.println("player position: "+player.i+" "+player.j);
        //indexes start from zero
        Map map = new Map(5,5);
        //initialization of the map
        //G for normal ground
        //S for swamp
        //K for key
        //C for castle
        //L for Loot
        //B for bandit
        //W for wildAnimals
        //P for bridge
        map.addEntity(0,0,new BaseEntity('G'));
        map.addEntity(0,1,new BaseEntity('G'));
        map.addEntity(0,2,new BaseEntity('G'));
        map.addEntity(0,3,new Bandit(50));
        map.addEntity(0,4,new BaseEntity('C'));
        map.addEntity(1,0,new BaseEntity('S'));
        map.addEntity(1,1,new BaseEntity('K'));
        map.addEntity(1,2,new Bridge());
        map.addEntity(1,3,new BaseEntity('S'));
        map.addEntity(1,4,new Bandit(100));
        map.addEntity(2,0,new Loot(50,50));
        map.addEntity(2,1,new  WildAnimall(50));
        map.addEntity(2,2,new BaseEntity('G'));
        map.addEntity(2,3,new BaseEntity('G'));
        map.addEntity(2,4,new Bandit(100));
        map.addEntity(3,0,new WildAnimall(100));
        map.addEntity(3,1,new BaseEntity('G'));
        map.addEntity(3,2,new BaseEntity('S'));
        map.addEntity(3,3,new BaseEntity('S'));
        map.addEntity(3,4,new BaseEntity('G'));
        map.addEntity(4,0,new BaseEntity('G'));
        map.addEntity(4,1,new Bridge());
        map.addEntity(4,2,new BaseEntity('G'));
        map.addEntity(4,3,new BaseEntity('G'));
        map.addEntity(4,4,new BaseEntity('G'));
        //print method prints the map
        map.print();
        visualizer.printMap(map, player);
        Node node = new Node(player,map,null,null);
        //BFS bfs = new BFS();
        //bfs.search(node);
        //DFS dfs = new DFS();
        //dfs.search(node);
        //IDS ids = new IDS();
        //ids.search(node);
        //Astar astar = new Astar(node, nodeG);
        //astar.search();
        //IDAstar idastar = new IDAstar(node, nodeG);
        //idastar.search();
    }
}


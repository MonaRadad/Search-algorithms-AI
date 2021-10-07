public class test5 {
    public static void main(String[] args){
        Player player = new Player(0,4,1,1);
        Player playerG = new Player(4,4,1,1);
        Visualizer visualizer = new Visualizer();
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
        map.addEntity(0,0,new BaseEntity('S'));
        map.addEntity(0,1,new WildAnimall(1000));
        map.addEntity(0,2,new BaseEntity('S'));
        map.addEntity(0,3,new Bridge());
        map.addEntity(0,4,new BaseEntity('G'));
        map.addEntity(1,0,new Loot(100,100));
        map.addEntity(1,1,new Loot(100,100));
        map.addEntity(1,2,new BaseEntity('G'));
        map.addEntity(1,3,new Loot(1000,1000));
        map.addEntity(1,4,new Bandit(50));
        map.addEntity(2,0,new WildAnimall(1000));
        map.addEntity(2,1,new BaseEntity('S'));
        map.addEntity(2,2,new Bandit(100));
        map.addEntity(2,3,new BaseEntity('S'));
        map.addEntity(2,4,new BaseEntity('K'));
        map.addEntity(3,0,new BaseEntity('G'));
        map.addEntity(3,1,new BaseEntity('S'));
        map.addEntity(3,2,new Bridge());
        map.addEntity(3,3,new BaseEntity('S'));
        map.addEntity(3,4,new BaseEntity('S'));
        map.addEntity(4,0,new BaseEntity('S'));
        map.addEntity(4,1,new BaseEntity('G'));
        map.addEntity(4,2,new BaseEntity('G'));
        map.addEntity(4,3,new WildAnimall(1000));
        map.addEntity(4,4,new BaseEntity('C'));
    }
}

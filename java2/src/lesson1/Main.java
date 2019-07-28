package lesson1;

import lesson1.maraphon.competitors.*;
import lesson1.maraphon.obstacles.*;

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors = {
                new Human("Bob"),
                new Cat("Barsik"),
                new Dog("Jack"),
                new Dog("Тузик")};
        Obstacle[] obstacles = {
                new Cross(80),
                new Wall(2),
                new Wall(1),
                new Water(10)};

        for (Competitor c : competitors) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isDistance()) break;
            }
        }

        for (Competitor c : competitors) {
            c.info();

        }
        Course course = new Course(obstacles);
        Team team = new Team("Winners", competitors);
        course.doIt(team);
        team.showResults();
        team.showMembersFinishedCourse();
    }
}

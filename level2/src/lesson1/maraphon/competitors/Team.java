package lesson1.maraphon.competitors;

public class Team {
    String teamName;
    Competitor[] teamMembers;


    public Team(String teamName, Competitor[] teamMembers){
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }

    public Competitor[] getMembers(){
        return teamMembers;
    }

    public void showResults(){
        for(Competitor c: teamMembers){
            c.showResult();
        }
    }

    public void showMembersFinishedCourse(){
        for(Competitor c: teamMembers){
            if(c.isOnDistance())
                c.showResult();
        }
    }
}
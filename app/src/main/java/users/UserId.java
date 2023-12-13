package users;
import app.src.main.java.userData.Id;
public class UserId extends Id{
    private static int ID_LENGTH = 7;
    
    public UserId(HashSet<UserId> otherIds){
        super(otherIds, ID_LENGTH);
    }

    public UserId(String id){
        this.id = id;
    }

}
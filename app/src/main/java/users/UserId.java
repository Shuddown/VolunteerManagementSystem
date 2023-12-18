package users;
import java.util.HashSet;

import common.Id;
public class UserId extends Id{
    private static int ID_LENGTH = 7;
    
    public UserId(){
        super(ID_LENGTH);
    }

    public static UserId getUniqueEventId(HashSet<UserId> existingIds){
        UserId candidateId;
        do{
            candidateId = new UserId();
        }while(existingIds.contains(candidateId));
        return candidateId;
    }

    public UserId(String id){
        super(id);
    }

}
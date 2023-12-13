package app.src.main.java.userData;

import java.util.Base64;
import java.util.Random;

abstract public class Id {
    protected String id;
    
    protected static String generateRandomId(int length){
        Random random = ThreadLocalRandom.current();
        byte[] randomBytes = new byte[length * 8];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
    protected Id(){}

    protected Id(HashSet<? extends Id> otherIds, int length){
        do{
            id = generateRandomId(length);
        }while(otherIds.contains(id));
    }

    @Override
    public boolean equals(Id otherId){
        return id.equals(otherId);
    }

    @Override
    public String toString(){
        return this.id;
    }

    public static void main(String[] args){
        Id id = new Id(new HashSet<Id>(), 8);
        System.out.println(id);
    }

}
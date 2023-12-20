package common;

import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Id {
    protected String id;
    
    protected static String generateRandomId(int length){
        Random random = ThreadLocalRandom.current();
        byte[] randomBytes = new byte[length/2];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
    protected Id(){}

    protected Id(String id){
        this.id = id;
    }

    protected Id(int length){
        id = generateRandomId(length);
    }
    
    @Override
    public String toString(){
        return this.id;
    }

    

}
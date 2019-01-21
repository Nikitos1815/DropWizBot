import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;

class UserDB {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FLAG = "flag";

    private MongoCollection<Document> collec;

    public UserDB(MongoDatabase db) {
        collec = db.getCollection("userlist");
    }

    public void registration(String username, Long useRid) {
        try {
            Document doublecheck = collec.find(eq(ID, useRid)).first();
            if (doublecheck == null) {
                Document doc = new Document()
                        .append(NAME, username).append(ID, useRid);
                collec.insertOne(doc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void query(String flag, Long useRid) {

        try {
            collec.updateOne(eq(ID, useRid), set(FLAG, flag));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flagDelete(Long useRid) {
        try {
            collec.updateOne(eq(ID, useRid), unset(FLAG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int flagCheck(Long useRid) {
        int check = 0;
        try {
            if (collec.find(and(Filters.eq(ID, useRid),Filters.eq(FLAG,"FIO"))).first() != null){ check = 1;}
            else if (collec.find(and(eq(ID, useRid), eq(FLAG, "Phone"))).first() != null)
            { check = 2;}
            else if (collec.find(and(eq(ID, useRid), eq(FLAG, "Address"))).first() != null)
            {check = 3;}
            else if (collec.find(and(eq(ID, useRid), eq(FLAG, "Mail"))).first() != null)
            {check = 4;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}

